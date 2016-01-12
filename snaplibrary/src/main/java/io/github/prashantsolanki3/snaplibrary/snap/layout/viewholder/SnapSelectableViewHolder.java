package io.github.prashantsolanki3.snaplibrary.snap.layout.viewholder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

import io.github.prashantsolanki3.snaplibrary.snap.adapter.AbstractSnapSelectableAdapter;

/**
 * Package io.github.prashantsolanki3.snaplibrary.snap.selectable
 * <p>
 * Created by Prashant on 1/5/2016.
 * <p>
 * Email: solankisrp2@gmail.com
 * Github: @prashantsolanki3
 */
public abstract class SnapSelectableViewHolder<T> extends SnapViewHolder<T> {


    AbstractSnapSelectableAdapter adapter;

    public SnapSelectableViewHolder(View itemView, Context context, AbstractSnapSelectableAdapter adapter) {
        super(itemView, context);
        this.adapter = adapter;
    }

    public SnapSelectableViewHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Nullable
    public AbstractSnapSelectableAdapter getAdapter() {
        return adapter;
    }

    public abstract void onSelectionEnabled(SnapSelectableViewHolder viewHolder, T item, int pos);

    public abstract void onSelectionDisabled(SnapSelectableViewHolder viewHolder, T item, int pos);

    public abstract void onSelected(SnapSelectableViewHolder viewHolder, T item, int pos);

    public abstract void onDeselected(SnapSelectableViewHolder viewHolder, T item, int pos);

}
