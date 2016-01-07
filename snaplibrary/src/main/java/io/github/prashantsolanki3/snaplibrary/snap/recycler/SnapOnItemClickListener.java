package io.github.prashantsolanki3.snaplibrary.snap.recycler;

import android.view.View;

import io.github.prashantsolanki3.snaplibrary.snap.SnapViewHolder;

public interface SnapOnItemClickListener {
    void onItemClick(SnapViewHolder viewHolder, View view, int position);

    void onItemLongPress(SnapViewHolder viewHolder, View view, int position);

}