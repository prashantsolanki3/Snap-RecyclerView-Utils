package io.github.prashantsolanki3.snaplibrary.snap;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import io.github.prashantsolanki3.snaplibrary.snap.endless.EndlessLoader;
import io.github.prashantsolanki3.snaplibrary.snap.endless.EndlessRecyclerOnScrollListener;

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

    public AbstractSnapMultiAdapter(@NonNull Context context,
                                    ArrayList<SnapLayoutWrapper> layoutWrappers) {
        this.context = context;
        mData = new ArrayList<>();
        this.layoutWrappers = layoutWrappers;
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
        SnapLayoutWrapper wrapper = getWrapperFromType(viewType);

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
        T t = mData.get(position);
        this.populateViewHolderItem(holder, t, position);
        this.setAnimation(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        //TODO: Find a better way to compare classes.
        if(layoutWrappers.size()>1) {
            for (SnapLayoutWrapper wrapper : layoutWrappers) {
                if (wrapper.getModel().getName().equals(mData.get(position).getClass().getName()))
                    return wrapper.getLayoutType();
            }

            throw new RuntimeException("Please Check the SnapLayoutWrapper and the input Dataset Classes");
        }
        return layoutWrappers.get(0).getLayoutType();

        }

    public SnapLayoutWrapper getWrapperFromType(int type) {

        for (SnapLayoutWrapper wrapper : layoutWrappers)
                if(wrapper.getLayoutType().equals(type))
                    return wrapper;
        return null;

    }

    public final T getItem(int pos) {
        return mData.get(pos);
    }

    public ArrayList<T> getAll() {
        return mData;
    }

    public void add(@Nullable T cardBase) {
        if (cardBase == null) return;
        this.mData.add(cardBase);
        notifyItemInserted(this.mData.size() - 1);
    }

    public void addAll(@Nullable ArrayList<T> list) {
        if (list == null) return;
        final int prevSize = this.mData.size() - 1;
        this.mData.addAll(list);
        this.notifyItemRangeInserted(prevSize, this.mData.size() - 1);
    }

    public void set(@Nullable ArrayList<T> data) {
        clear();
        if (data != null)
            mData.addAll(data);
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
        return mData.size();
    }

    public void setEndlessLoader(final RecyclerView recyclerView,int thresholdLimit, final EndlessLoader<T> endlessLoader){
        this.endlessLoader = endlessLoader;

        endlessRecyclerOnScrollListener.setVisibleThreshold(thresholdLimit);

        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
    }

    EndlessLoader<T> endlessLoader = null;
    final AbstractSnapMultiAdapter<T> adapter = this;

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

}