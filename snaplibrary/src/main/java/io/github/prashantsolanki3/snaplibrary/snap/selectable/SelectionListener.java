package io.github.prashantsolanki3.snaplibrary.snap.selectable;

public interface SelectionListener {

    void onSelectionModeEnabled(AbstractSnapSelectableAdapter.SelectionType selectionType);

    void onSelectionModeDisabled(AbstractSnapSelectableAdapter.SelectionType selectionType);

    void onItemSelected(SnapSelectableViewHolder holder, int pos);

    void onItemDeselected(SnapSelectableViewHolder holder, int pos);

    void onSelectionLimitReached();

}