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

/**
 * Created by Prashant Solanki.
 * <p/>
 * Github: prashantsolanki3
 * Email: prs.solanki@live.com
 */
public abstract class AbstractSnapMultiAdapter<T> extends RecyclerView.Adapter<SnapViewHolder> {

    private final Context context;
    private final ArrayList<T> mData;
    private int lastPosition = -1;
    ArrayList<SnapMultiViewWrapper> multiViewWrappers;

    public AbstractSnapMultiAdapter(@NonNull Context context,
                                    ArrayList<SnapMultiViewWrapper> multiViewWrappers) {
        this.context = context;
        mData = new ArrayList<>();
        this.multiViewWrappers=multiViewWrappers;
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
        SnapMultiViewWrapper wrapper = getWrapperFromType(viewType);

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

        for (SnapMultiViewWrapper wrapper:multiViewWrappers) {
            if(wrapper.getModel().getName().equals(mData.get(position).getClass().getName()))
                return wrapper.getLayoutType();
        }

        throw new RuntimeException("Please Check the SnapMultiViewWrapper and the input Data set Classes");
    }

    public SnapMultiViewWrapper getWrapperFromType(int type){

        for (SnapMultiViewWrapper wrapper:multiViewWrappers)
                if(wrapper.getLayoutType().equals(type))
                    return wrapper;
        return null;

    }

    public final Object getItem(int pos) {
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
        this.notifyDataSetChanged();
    }

    public void remove(@NonNull Object item) {
        int pos = this.mData.indexOf(item);
        this.mData.remove(pos);
        this.notifyItemRemoved(pos);
    }

    public void clear() {
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

}