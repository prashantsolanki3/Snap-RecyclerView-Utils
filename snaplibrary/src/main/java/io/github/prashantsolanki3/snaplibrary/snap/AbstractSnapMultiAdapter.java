package io.github.prashantsolanki3.snaplibrary.snap;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import io.github.prashantsolanki3.snaplibrary.snap.endless.EndlessLoader;
import io.github.prashantsolanki3.snaplibrary.snap.endless.EndlessRecyclerOnScrollListener;
import io.github.prashantsolanki3.snaplibrary.snap.recycler.SnapOnItemClickListener;
import io.github.prashantsolanki3.snaplibrary.snap.recycler.SnapOnItemTouchListener;
import io.github.prashantsolanki3.snaplibrary.snap.utils.UtilsLayoutWrapper;

/**
 * Created by Prashant Solanki.
 * <p/>
 * Github: prashantsolanki3
 * Email: prs.solanki@live.com
 */
public abstract class AbstractSnapMultiAdapter<T> extends RecyclerView.Adapter<SnapViewHolder> {

    private final Context context;
    private final ArrayList<T> mData;
    ArrayList<SnapLayoutWrapper> layoutWrappers;
    private int lastPosition = -1;
    ViewGroup alternateViewContainer = null;
    RecyclerView recyclerView = null;
    EndlessLoader<T> endlessLoader = null;
    final AbstractSnapMultiAdapter<T> adapter = this;
    View emptyView = null;
    @LayoutRes
    int emptyLayoutId = 0;
    LayoutInflater inflater;
    boolean autoEmptyLayoutHandling = false;

    public AbstractSnapMultiAdapter(@NonNull Context context,
                                    ArrayList<SnapLayoutWrapper> layoutWrappers) {
        this.context = context;
        mData = new ArrayList<>();
        this.layoutWrappers = layoutWrappers;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public AbstractSnapMultiAdapter(@NonNull Context context,
                                    ArrayList<SnapLayoutWrapper> layoutWrappers,
                                    RecyclerView recyclerView) {
        this.context = context;
        mData = new ArrayList<>();
        this.layoutWrappers = layoutWrappers;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.recyclerView = recyclerView;
    }

    public AbstractSnapMultiAdapter(@NonNull Context context,
                                    ArrayList<SnapLayoutWrapper> layoutWrappers,
                                    RecyclerView recyclerView,
                                    ViewGroup alternateViewContainer) {
        this.context = context;
        mData = new ArrayList<>();
        this.layoutWrappers = layoutWrappers;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.recyclerView = recyclerView;
        this.alternateViewContainer = alternateViewContainer;
    }

    public final Context getContext() {
        return context;
    }

    private void setAnimation(SnapViewHolder vh, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            animateItems(vh, position);
            lastPosition = position;
        }
    }

    @Override
    public SnapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) throws RuntimeException {
        SnapLayoutWrapper wrapper = UtilsLayoutWrapper.getWrapperFromType(layoutWrappers, viewType);

        final ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(wrapper.getLayoutId(), parent, false);

        try {
            Constructor e = wrapper.getViewHolder().getConstructor(View.class, Context.class);
            //noinspection unchecked
            return (SnapViewHolder) e.newInstance(view, context);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onBindViewHolder(SnapViewHolder holder, int position) {
        T item = mData.get(position);
        this.populateViewHolderItem(holder, item, position);
        this.setAnimation(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        //TODO: Find a better way to compare classes.
        if(layoutWrappers.size()>1) {
            return UtilsLayoutWrapper.getWrapperFromModel(layoutWrappers,
                    mData.get(position).getClass())
                    .getLayoutType();
        }
        return layoutWrappers.get(0)
                .getLayoutType();

        }


    public final T getItem(@IntRange(from = 0, to = Integer.MAX_VALUE) int pos) {
        return mData.get(pos);
    }

    public ArrayList<T> getAll() {
        return mData;
    }

    public void add(@Nullable T cardBase) {
        if (cardBase == null) return;
        this.mData.add(cardBase);
        notifyItemInserted(this.mData.size() - 1);
        handleEmptyLayoutVisibility();
    }

    public void addAll(@Nullable ArrayList<T> list) {
        if (list == null) return;
        final int prevSize = this.mData.size() - 1;
        this.mData.addAll(list);
        this.notifyItemRangeInserted(prevSize, this.mData.size() - 1);
        handleEmptyLayoutVisibility();
    }

    public void set(@Nullable ArrayList<T> data) {
        clear();
        if (data != null)
            mData.addAll(data);
        handleEmptyLayoutVisibility();
    }

    public void remove(@IntRange(from = 0, to = Integer.MAX_VALUE) int pos) {
        this.mData.remove(pos);
        this.notifyItemRemoved(pos);
        endlessRecyclerOnScrollListener.itemRemoved(1);
        this.notifyDataSetChanged();
    }

    public void remove(@NonNull T item) {
        int pos = this.mData.indexOf(item);
        this.mData.remove(pos);
        endlessRecyclerOnScrollListener.itemRemoved(1);
        this.notifyItemRemoved(pos);
    }

    public void clear() {
        endlessRecyclerOnScrollListener.itemRemoved(mData.size());
        this.mData.clear();
        this.notifyDataSetChanged();
    }

    public ArrayList<SnapLayoutWrapper> getLayoutWrappers() {
        return layoutWrappers;
    }

    /**
     * Populate view of viewholder here.
     */
    public void populateViewHolderItem(SnapViewHolder viewHolder, T item, int position) {
        viewHolder.setItemData(item);
        viewHolder.populateViewHolder(item, position);
        viewHolder.attachOnClickListeners(viewHolder, item, position);
    }

    /**
     * Animate viewHolder.itemView in this method.
     * Position is not needed for simple animations.
     * Use position for complex animations.
     */
    public void animateItems(SnapViewHolder viewHolder, int pos) {
        viewHolder.animateViewHolder(viewHolder, pos);
    }


    @Override
    public int getItemCount() {
        int size = mData.size();
        Log.d("Size", size + "");

        if (autoEmptyLayoutHandling)
            if (alternateViewContainer != null && size == 0)
                showEmptyLayout();
            else if (isAlternateLayoutShowing())
                hideAlternateLayout();

        return size;
    }

    @Deprecated
    public void setEndlessLoader(final RecyclerView recyclerView,int thresholdLimit, final EndlessLoader<T> endlessLoader){
        this.endlessLoader = endlessLoader;
        endlessRecyclerOnScrollListener.setVisibleThreshold(thresholdLimit);
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
        notifyDataSetChanged();
    }

    public void setEndlessLoader(int thresholdLimit, final EndlessLoader<T> endlessLoader) {
        checkRecyclerViewInit();
        this.setEndlessLoader(recyclerView, thresholdLimit, endlessLoader);
    }

    EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(10) {

        @Override
        public void onLoadMore(int currentPage) {
            if (endlessLoader != null)
                endlessLoader.loadMore(adapter, currentPage);
        }

        @Override
        public void onScroll(RecyclerView recyclerView, int dx, int dy) {
            if (endlessLoader != null)
                endlessLoader.onScrolled(recyclerView, dx, dy);
        }
    };

    public void setAlternateViewContainer(ViewGroup alternateView) {
        this.alternateViewContainer = alternateView;
    }

    private void checkAlternateViewInit() {
        if (alternateViewContainer == null)
            throw new RuntimeException("You must set Alternate View before inflating layouts");
    }

    private void checkRecyclerViewInit() {
        if (recyclerView == null)
            throw new RuntimeException("You must set RecyclerView before setting endless loader");
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setEmptyView(@NonNull View emptyView) {
        this.emptyView = emptyView;
    }

    public void setEmptyLayoutId(@LayoutRes int emptyLayoutId) {
        this.emptyLayoutId = emptyLayoutId;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void showEmptyLayout() {
        if (emptyView != null)
            showAlternateLayout(emptyView);
        else if (emptyLayoutId != 0)
            showAlternateLayout(emptyLayoutId);
        else
            throw new RuntimeException("Must set emptyView or emptyLayoutId.");
    }

    public void showAlternateLayout(@LayoutRes int layoutId) {
        checkRecyclerViewInit();
        checkAlternateViewInit();
        alternateViewContainer.removeAllViews();
        recyclerView.setVisibility(View.GONE);
        View view = inflater.inflate(layoutId, null);
        alternateViewContainer.addView(view,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        alternateViewContainer.setVisibility(View.VISIBLE);
    }

    public void showAlternateLayout(@NonNull View view) {
        checkRecyclerViewInit();
        checkAlternateViewInit();

        if (alternateViewContainer.getChildCount() > 0)
            alternateViewContainer.removeAllViews();

        recyclerView.setVisibility(View.GONE);

        alternateViewContainer.addView(view,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

        alternateViewContainer.setVisibility(View.VISIBLE);

    }

    public boolean isAlternateLayoutShowing() {

        checkAlternateViewInit();
        return alternateViewContainer.getVisibility() == View.VISIBLE;

    }

    public void hideAlternateLayout() {
        checkRecyclerViewInit();
        checkAlternateViewInit();

        recyclerView.setVisibility(View.VISIBLE);
        alternateViewContainer.setVisibility(View.GONE);
        alternateViewContainer.removeAllViews();

    }

    public void handleEmptyLayoutVisibility() {
        if (autoEmptyLayoutHandling)
            if (alternateViewContainer != null && getItemCount() == 0)
                showEmptyLayout();
            else if (isAlternateLayoutShowing())
                hideAlternateLayout();
    }

    public void setAutoEmptyLayoutHandling(boolean autoEmptyLayoutHandling) {
        this.autoEmptyLayoutHandling = autoEmptyLayoutHandling;
    }

    public View getViewFromId(@LayoutRes int layoutId) {
        return inflater.inflate(layoutId, null);
    }

    public void setOnItemClickListener(@NonNull SnapOnItemClickListener clickListener) {
        checkRecyclerViewInit();
        recyclerView.addOnItemTouchListener(new SnapOnItemTouchListener(getContext(), clickListener));
    }

    public int getItemPosition(@NonNull T item) {
        return getAll().indexOf(item);
    }

}