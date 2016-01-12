package io.github.prashantsolanki3.snaplibrary.snap.listeners.touch;

import android.view.View;

import io.github.prashantsolanki3.snaplibrary.snap.layout.viewholder.SnapViewHolder;

public interface SnapOnItemClickListener {
    void onItemClick(SnapViewHolder viewHolder, View view, int position);

    void onItemLongPress(SnapViewHolder viewHolder, View view, int position);

}