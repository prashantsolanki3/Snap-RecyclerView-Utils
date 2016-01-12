package io.github.prashantsolanki3.snaplibrary.snap.adapter;

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
import java.util.List;

import io.github.prashantsolanki3.snaplibrary.snap.layout.viewholder.SnapViewHolder;
import io.github.prashantsolanki3.snaplibrary.snap.layout.wrapper.SnapLayoutWrapper;
import io.github.prashantsolanki3.snaplibrary.snap.listeners.endless.EndlessLoader;
import io.github.prashantsolanki3.snaplibrary.snap.listeners.endless.EndlessRecyclerOnScrollListener;
import io.github.prashantsolanki3.snaplibrary.snap.listeners.touch.SnapOnItemClickListener;
import io.github.prashantsolanki3.snaplibrary.snap.listeners.touch.SnapOnItemTouchListener;
import io.github.prashantsolanki3.snaplibrary.snap.utils.UtilsLayoutWrapper;

/**
 * Created by Prashant Solanki.
 * <p/>
 * Github: prashantsolanki3
 * Email: prs.solanki@live.com
 */
public abstract class AbstractSnapMultiAdapter<T> extends RecyclerView.Adapter<SnapViewHolder> implements BasicsRecyclerViewAdapter<T, SnapViewHolder> {

    final AbstractSnapMultiAdapter<T> adapter = this;
    private final Context context;
    private final ArrayList<T> mData;
    ArrayList<SnapLayoutWrapper> layoutWrappers;
    ViewGroup alternateViewContainer = null;
    RecyclerView recyclerView = null;
    EndlessLoader<T> endlessLoader = null;
    View emptyView = null;
    @LayoutRes
    int emptyLayoutId = 0;
    LayoutInflater inflater;
    boolean autoEmptyLayoutHandling = false;
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
    private int lastPosition = -1;

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

    @Override
    public void setAnimation(SnapViewHolder vh, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            vh.animateViewHolder(vh, position);
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

    @Override
    public final T getItem(@IntRange(from = 0, to = Integer.MAX_VALUE) int pos) {
        return mData.get(pos);
    }

    @Override
    public ArrayList<T> getAll() {
        return mData;
    }

    @Override
    public void add(@Nullable T item) {
        if (item == null)
            return;
        this.mData.add(item);
        notifyItemInserted(this.mData.size() - 1);
        handleEmptyLayoutVisibility();
    }

    @Override
    public void addAll(@Nullable List<T> list) {
        if (list == null) return;
        final int prevSize = this.mData.size() - 1;
        this.mData.addAll(list);
        this.notifyItemRangeInserted(prevSize, this.mData.size() - 1);
        handleEmptyLayoutVisibility();
    }

    @Override
    public void set(@Nullable List<T> data) {
        clear();
        if (data != null)
            mData.addAll(data);
        handleEmptyLayoutVisibility();
    }

    @Override
    public void remove(@IntRange(from = 0, to = Integer.MAX_VALUE) int pos) {
        this.mData.remove(pos);
        this.notifyItemRemoved(pos);
        endlessRecyclerOnScrollListener.itemRemoved(1);
        this.notifyDataSetChanged();
    }

    @Override
    public void remove(@NonNull T item) {
        int pos = this.mData.indexOf(item);
        this.mData.remove(pos);
        endlessRecyclerOnScrollListener.itemRemoved(1);
        this.notifyItemRemoved(pos);
    }

    @Override
    public void clear() {
        endlessRecyclerOnScrollListener.itemRemoved(mData.size());
        this.mData.clear();
        this.notifyDataSetChanged();
    }

    public ArrayList<SnapLayoutWrapper> getLayoutWrappers() {
        return layoutWrappers;
    }

    /**
     * Add new Layouts to the adapter.
     */
    public void addLayoutWrapper(@NonNull SnapLayoutWrapper wrapper) {
        this.layoutWrappers.add(wrapper);
    }

    /**
     * Should not be used unless extremely important.
     * Any item of the type that is being removed must not be present in the adapter.
     */
    public void removeLayoutWrapper(@NonNull SnapLayoutWrapper wrapper) {
        this.layoutWrappers.remove(wrapper);
    }

    /**
     * Populate view of viewholder here.
     */
    public void populateViewHolderItem(SnapViewHolder viewHolder, T item, int position) {
        viewHolder.setItemData(item);
        viewHolder.populateViewHolder(item, position);
        viewHolder.attachOnClickListeners(viewHolder, item, position);
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

    public void setAlternateViewContainer(ViewGroup alternateView) {
        this.alternateViewContainer = alternateView;
    }

    private void checkAlternateViewInit() {
        if (alternateViewContainer == null)
            throw new RuntimeException("You must set Alternate View");
    }

    private void checkRecyclerViewInit() {
        if (recyclerView == null)
            throw new RuntimeException("You must set RecyclerView");
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

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
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

    @Override
    public int indexOf(@NonNull T item) {
        return getAll().indexOf(item);
    }

    public int getPosition(@NonNull T item) {
        return indexOf(item);
    }

}