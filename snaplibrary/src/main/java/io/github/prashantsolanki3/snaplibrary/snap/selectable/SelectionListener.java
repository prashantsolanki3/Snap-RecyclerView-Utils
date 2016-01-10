package io.github.prashantsolanki3.snaplibrary.snap.selectable;

public interface SelectionListener<T> {

    void onSelectionModeEnabled(AbstractSnapSelectableAdapter.SelectionType selectionType);

    void onSelectionModeDisabled(AbstractSnapSelectableAdapter.SelectionType selectionType);

    void onItemSelected(T item, int pos);

    void onItemDeselected(T item, int pos);

    void onSelectionLimitReached();

    void onNoneSelected();

}