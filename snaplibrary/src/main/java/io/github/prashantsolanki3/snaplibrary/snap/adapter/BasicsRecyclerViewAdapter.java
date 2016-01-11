package io.github.prashantsolanki3.snaplibrary.snap.adapter;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * It's a part of Utiloid Project.
 * Find Utiloid at http://github.com/prashantsolanki3/Utiloid
 * <p>
 * <p>
 * Created by Prashant on 1/11/2016.
 * <p>
 * Email: solankisrp2@gmail.com
 * Github: @prashantsolanki3
 */
public interface BasicsRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> {

    /**
     * Animate viewHolder.itemView in this method.
     * Position is not needed for simple animations.
     *
     * @param vh       viewHolder.
     * @param position for item specific animations.
     */
    void setAnimation(VH vh, int position);

    /**
     * @return Model at the specified position.
     */
    T getItem(@IntRange(from = 0, to = Integer.MAX_VALUE) int pos);

    /**
     * @return All data from adapter.
     */
    List<T> getAll();

    /**
     * @param item Item to be added in recycler
     */
    void add(@Nullable T item);

    /**
     * @param list List of Items to be added in recycler
     */
    void addAll(@Nullable List<T> list);

    /**
     * @param data List of Items to be added in recycler. This will clear the existing data of recycler.
     */
    void set(@Nullable List<T> data);

    /**
     * @param pos Remove the Item at position pos.
     */
    void remove(@IntRange(from = 0, to = Integer.MAX_VALUE) int pos);

    /**
     * @param item Remove the specified Item from recycler.
     */
    void remove(@NonNull T item);

    /**
     * Clears the recyclerView
     */
    void clear();

    /**
     * @param item Item in RecyclerView
     * @return index of item.
     */
    int indexOf(@NonNull T item);

}