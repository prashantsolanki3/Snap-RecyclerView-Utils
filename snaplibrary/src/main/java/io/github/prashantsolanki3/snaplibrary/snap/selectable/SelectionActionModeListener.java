package io.github.prashantsolanki3.snaplibrary.snap.selectable;

import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;


/**
 * Created by Prashant on 1/9/2016.
 *
 */
public abstract class SelectionActionModeListener<T> implements SelectionListener<T>, ActionMode.Callback {

    Toolbar toolbar = null;
    ActionMode actionMode = null;

    public SelectionActionModeListener(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    @Override
    public void onSelectionModeEnabled(AbstractSnapSelectableAdapter.SelectionType selectionType) {
        setActionMode(toolbar.startActionMode(this));
    }

    @Override
    public void onSelectionModeDisabled(AbstractSnapSelectableAdapter.SelectionType selectionType) {
        if (actionMode != null) {
            actionMode.finish();
        }
    }

    public ActionMode getActionMode() {
        return actionMode;
    }

    public void setActionMode(ActionMode actionMode) {
        this.actionMode = actionMode;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }
}
