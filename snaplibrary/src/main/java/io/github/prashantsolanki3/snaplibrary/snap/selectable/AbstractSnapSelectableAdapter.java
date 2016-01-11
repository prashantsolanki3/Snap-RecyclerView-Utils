package io.github.prashantsolanki3.snaplibrary.snap.selectable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.prashantsolanki3.snaplibrary.snap.AbstractSnapMultiAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.SnapLayoutWrapper;
import io.github.prashantsolanki3.snaplibrary.snap.SnapViewHolder;
import io.github.prashantsolanki3.snaplibrary.snap.recycler.SnapOnItemClickListener;
import io.github.prashantsolanki3.snaplibrary.snap.utils.UtilsLayoutWrapper;

/**
 * Package io.github.prashantsolanki3.snaplibrary.snap.selectable
 * <p>
 * Created by Prashant on 1/5/2016.
 * <p>
 * Email: solankisrp2@gmail.com
 * Github: @prashantsolanki3
 */
public abstract class AbstractSnapSelectableAdapter<T> extends AbstractSnapMultiAdapter<T> {

    public List<T> selectedItems;
    public final SelectionType selectionType;
    public boolean selectionEnabled = false;
    public int selectionLimit = Integer.MAX_VALUE;
    SelectionListener<T> selectionListener = null;

    /*
    *
    * Adapter Methods
    *
    * */

    /**
     * @param context Context.
     * @param wrapper SnapLayoutWrapper
     */
    public AbstractSnapSelectableAdapter(@NonNull Context context, @NonNull SnapSelectableLayoutWrapper wrapper, RecyclerView recyclerView, SelectionType selectionType) {
        super(context, new ArrayList<SnapLayoutWrapper>(Collections.singletonList(wrapper)), recyclerView);
        this.selectionType = selectionType;
        init();
    }

    public AbstractSnapSelectableAdapter(@NonNull Context context,
                                         ArrayList<SnapLayoutWrapper> layoutWrappers,
                                         RecyclerView recyclerView,
                                         SelectionType selectionType) {
        super(context, layoutWrappers, recyclerView);
        this.selectionType = selectionType;
        init();
    }

    private void init() {
        selectedItems = new ArrayList<>();

        switch (selectionType) {
            case SINGLE:
                selectionEnabled = true;
                setSelectionLimit(1);
                break;
            case MULTIPLE:
                selectionEnabled = true;
                break;
            case MULTIPLE_ON_LONG_PRESS:
                selectionEnabled = false;
                break;
            default:
                throw new IllegalArgumentException("Selection type not Supported");
        }
        longPressHandler();
    }

    @Override
    public SnapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) throws RuntimeException {
        SnapLayoutWrapper wrapper = UtilsLayoutWrapper.getWrapperFromType(getLayoutWrappers(), viewType);

        final ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(wrapper.getLayoutId(), parent, false);

        try {
            Constructor e = wrapper.getViewHolder().getConstructor(View.class, Context.class, AbstractSnapSelectableAdapter.class);
            //noinspection unchecked
            return (SnapViewHolder) e.newInstance(view, getContext(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final SnapViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        SnapSelectableViewHolder<T> viewHolder = (SnapSelectableViewHolder<T>) holder;

        final boolean isVhSelectable = UtilsLayoutWrapper.isViewHolderSelectable(getLayoutWrappers(), viewHolder);

        if (selectionEnabled && isVhSelectable) {

            viewHolder.onSelectionEnabled(viewHolder, viewHolder.getItemData(), position);

            if (isSelected(viewHolder.getItemData()))
                viewHolder.onSelected(viewHolder, viewHolder.getItemData(), position);
            else
                viewHolder.onDeselected(viewHolder, viewHolder.getItemData(), position);

        } else if (isVhSelectable) {

            viewHolder.onSelectionDisabled(viewHolder, viewHolder.getItemData(), position);

        }
    }

    /*
    *
    * Selection Methods
    *
    */

    /**
     * Toggle GalleryItems Selection
     * returns True if Item is added.
     * and False if item is removed.
     */
    public boolean toggleSelection(T selection) {
        int pos = indexOf(selection);
        if (selectedItems.contains(selection)) {
            deselectItem(selection);
            return false;
        } else {
            selectItem(selection);
            return true;
        }
    }

    /**
     * Toggle GalleryItems Selection
     * returns True if Item is added.
     * and False if item is removed.
     */
    public boolean toggleSelection(int pos) {
        T selection = getItem(pos);
        if (selectedItems.contains(selection)) {
            deselectItem(selection);
            return false;
        } else {
            selectItem(selection);
            return true;
        }
    }

    /**
     * @return true if the item is selected and false is item is not selected or the selection limit is reached.
     */
    public boolean selectItem(T selection) {
        return internalSelectItem(selection, Integer.MIN_VALUE);
    }

    /**
     * @return true if the item is selected and false is item is not selected or the selection limit is reached.
     */
    public boolean selectItem(int pos) {
        return internalSelectItem(null, pos);
    }

    @SuppressWarnings("unchecked")
    private boolean internalSelectItem(T selection, int pos) {

        if (selection == null && pos == Integer.MIN_VALUE)
            return false;

        if (!isSelectionEnabled())
            return false;

        if (selectionType == SelectionType.SINGLE && !selectedItems.isEmpty()) {
            T i = selectedItems.get(0);
            deselectItem(i);
        }

        if (selectedItems.size() + 1 >= getSelectionLimit()) {
            selectionListener.onSelectionLimitReached();
            if (selectedItems.size() >= getSelectionLimit()) {
                selectionListener.onSelectionLimitExceeding();
                return false;
            }
        }

        if (selection == null)
            selection = getItem(pos);
        else if (pos == Integer.MIN_VALUE)
            pos = indexOf(selection);

        selectedItems.add(selection);
        selectionListener.onItemSelected(selection, pos);
        notifyItemChanged(pos);

        return true;
    }

    public boolean deselectItem(T selection) {
        return internalDeselectItem(selection, Integer.MIN_VALUE);
    }

    public boolean deselectItem(int pos) {
        return internalDeselectItem(null, pos);
    }

    @SuppressWarnings("unchecked")
    private boolean internalDeselectItem(T selection, int pos) {

        if (selection == null && pos == Integer.MIN_VALUE)
            return false;

        if (!isSelectionEnabled())
            return false;

        if (selection == null)
            selection = getItem(pos);
        else if (pos == Integer.MIN_VALUE)
            pos = indexOf(selection);
        selectedItems.remove(selection);
        notifyItemChanged(pos);
        selectionListener.onItemDeselected(selection, pos);

        if (selectedItems.isEmpty())
            selectionListener.onNoneSelected();

        if (selectionType == SelectionType.MULTIPLE_ON_LONG_PRESS && selectedItems.isEmpty()) {
            setSelectionEnabled(false);
            return false;
        }

        return true;
    }

    public boolean selectAll() {
        if (!isSelectionEnabled())
            return false;
        //Clear selected items to remove repetition.
        this.selectedItems.clear();
        //Select only the selectable items.
        for (int i = 0; i < getItemCount(); i++) {
            T t = getItem(i);
            if (((SnapSelectableLayoutWrapper) UtilsLayoutWrapper
                    .getWrapperFromModel(getLayoutWrappers(),
                            t.getClass()))
                    .isSelectable()) {
                this.selectedItems.add(t);
                notifyItemChanged(i);
            }
        }

        return true;
    }

    /**
     * Deselects all selected items
     */
    public boolean deselectAll() {
        if (!isSelectionEnabled())
            return false;
        this.selectedItems.clear();
        notifyDataSetChanged();
        return true;
    }

    /**
     * Deselects all selected items.
     *
     * @param disableSelection to disable or keep the selection mode
     */
    public boolean deselectAll(boolean disableSelection) {
        if (!isSelectionEnabled())
            return false;
        this.selectedItems.clear();
        notifyDataSetChanged();
        setSelectionEnabled(!disableSelection);
        return true;
    }

    void longPressHandler() {
        setOnItemClickListener(snapOnItemClickListener);
    }

    /**
     * Handle Contextual ActionBar
     */
    public void setOnSelectionListener(SelectionListener<T> selectionListener) {
        this.selectionListener = selectionListener;
    }

    /*
    *
    * Getters and Setters
    *
    * */

    public void setSelectionEnabled(boolean selectionEnabled) {
        if (selectionListener != null)
            if (selectionEnabled)
                selectionListener.onSelectionModeEnabled(selectionType);
            else {
                selectionListener.onSelectionModeDisabled(selectionType);
                deselectAll();
            }
        this.selectionEnabled = selectionEnabled;
        notifyDataSetChanged();
    }

    public boolean isSelectionEnabled() {
        return selectionEnabled;
    }

    public boolean isSelected(T item) {
        return selectedItems.contains(item);
    }

    public int getSelectionLimit() {
        return selectionLimit;
    }

    public void setSelectionLimit(int selectionLimit) {
        this.selectionLimit = selectionLimit;
    }

    public List<T> getSelectedItems() {
        return selectedItems;
    }

    /*
    *
    * Enum and listeners
    *
    */

    public enum SelectionType {
        MULTIPLE, MULTIPLE_ON_LONG_PRESS, SINGLE
    }


    //TODO: Implement onItemClickListener to support the working of ViewHolder onClick listeners
    // TODO: and Long press to activate multiple selections. Also complete the selection listener.
    //TODO: Remove Selected item if it has  been removed from the list.

    SnapOnItemClickListener snapOnItemClickListener = new SnapOnItemClickListener() {
        @Override
        public void onItemClick(SnapViewHolder viewHolder, View view, int position) {
            if (isSelectionEnabled() && UtilsLayoutWrapper.isViewHolderSelectable(getLayoutWrappers(), (SnapSelectableViewHolder) viewHolder)) {
                toggleSelection(position);
            }
        }

        @Override
        public void onItemLongPress(SnapViewHolder viewHolder, View view, int position) {
            if (!isSelectionEnabled() &&
                    UtilsLayoutWrapper.isViewHolderSelectable(getLayoutWrappers(), (SnapSelectableViewHolder) viewHolder) &&
                    selectionType == SelectionType.MULTIPLE_ON_LONG_PRESS) {
                setSelectionEnabled(true);
                toggleSelection(position);
            }
        }
    };




}
