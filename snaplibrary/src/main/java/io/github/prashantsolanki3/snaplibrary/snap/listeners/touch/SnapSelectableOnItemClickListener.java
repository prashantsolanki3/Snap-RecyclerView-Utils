package io.github.prashantsolanki3.snaplibrary.snap.listeners.touch;

import android.view.View;

import io.github.prashantsolanki3.snaplibrary.snap.adapter.AbstractSnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.layout.viewholder.SnapSelectableViewHolder;
import io.github.prashantsolanki3.snaplibrary.snap.layout.viewholder.SnapViewHolder;
import io.github.prashantsolanki3.snaplibrary.snap.utils.UtilsLayoutWrapper;

public abstract class SnapSelectableOnItemClickListener implements SnapOnItemClickListener{

        AbstractSnapSelectableAdapter adapter;

        public SnapSelectableOnItemClickListener(AbstractSnapSelectableAdapter adapter) {
            this.adapter = adapter;
        }

        public abstract void onItemClick(SnapSelectableViewHolder viewHolder, View view, int position);
        public abstract void onItemLongPress(SnapSelectableViewHolder viewHolder, View view, int position);

        @Override
        public void onItemClick(SnapViewHolder viewHolder, View view, int position) {
            if (adapter.isSelectionEnabled() && UtilsLayoutWrapper.isViewHolderSelectable(adapter.getLayoutWrappers(), (SnapSelectableViewHolder) viewHolder)) {
                adapter.toggleSelection(position);
            }

            onItemClick((SnapSelectableViewHolder) viewHolder,view,position);
        }

        @Override
        public void onItemLongPress(SnapViewHolder viewHolder, View view, int position) {
            if (!adapter.isSelectionEnabled() &&
                    UtilsLayoutWrapper.isViewHolderSelectable(adapter.getLayoutWrappers(), (SnapSelectableViewHolder) viewHolder) &&
                    adapter.selectionType == AbstractSnapSelectableAdapter.SelectionType.MULTIPLE_ON_LONG_PRESS) {
                adapter.setSelectionEnabled(true);
                adapter.toggleSelection(position);
            }

            onItemLongPress((SnapSelectableViewHolder) viewHolder,view,position);
        }

    }