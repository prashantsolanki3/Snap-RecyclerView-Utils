package io.github.prashantsolanki3.snaplibrary.snap;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
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
public class SnapAdapter<T, VH extends SnapViewHolder> extends RecyclerView.Adapter<VH> {

    private final Context context;
    private final Class<VH> viewHolderClass;
    private final ArrayList<T> mData;

    @LayoutRes
    protected int itemLayout;
    private int lastPosition = -1;

    public SnapAdapter(@NonNull Context context, @LayoutRes int itemLayout, @NonNull Class<VH> viewHolderClass) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.viewHolderClass = viewHolderClass;
        mData = new ArrayList<>();
    }


    public final Context getContext() {
        return context;
    }

    private void setAnimation(VH vh, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            animateItems(vh, position);
            lastPosition = position;
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) throws RuntimeException {
        final ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(this.itemLayout, parent, false);
        try {
            Constructor e = this.viewHolderClass.getConstructor(View.class);
            //noinspection unchecked
            VH vh = (VH) e.newInstance(view);
            vh.setContext(context);
            return vh;
        } catch (Exception var5) {
            try {
                Constructor e = this.viewHolderClass.getConstructor(View.class, Context.class);
                //noinspection unchecked
                return (VH) e.newInstance(view, context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        T t = mData.get(position);
        this.populateViewHolderItem(holder, t, position);
        this.setAnimation(holder, position);
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
        this.notifyDataSetChanged();
    }

    public void remove(@NonNull T item) {
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
    public void populateViewHolderItem(VH viewHolder, T item, int position) {
        viewHolder.setItemData(item);
        viewHolder.populateViewHolder(item, position);
        viewHolder.attachOnClickListeners(viewHolder, item, position);
    }

    /**
     * Animate viewHolder.itemView in this method.
     * Position is not needed for simple animations.
     * Use position for complex animations.
     */
    public void animateItems(VH viewHolder, int pos) {
        viewHolder.animateViewHolder(viewHolder, pos);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}