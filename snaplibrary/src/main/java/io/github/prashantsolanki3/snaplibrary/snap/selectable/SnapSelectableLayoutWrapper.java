package io.github.prashantsolanki3.snaplibrary.snap.selectable;

import android.support.annotation.LayoutRes;

import io.github.prashantsolanki3.snaplibrary.snap.SnapLayoutWrapper;
import io.github.prashantsolanki3.snaplibrary.snap.SnapViewHolder;

/**
 * Package io.github.prashantsolanki3.snaplibrary.snap.layoutwrapper
 * <p>
 * Created by Prashant on 1/5/2016.
 * <p>
 * Email: solankisrp2@gmail.com
 * Github: @prashantsolanki3
 */
public class SnapSelectableLayoutWrapper extends SnapLayoutWrapper {

    private boolean isSelectable;

    public SnapSelectableLayoutWrapper(Class model,
                                       Class<? extends SnapViewHolder> viewHolder,
                                       @LayoutRes Integer layoutId,
                                       Integer layoutType,
                                       boolean isSelectable) {

        super(model, viewHolder, layoutId, layoutType);
        this.isSelectable = isSelectable;
    }

    public SnapSelectableLayoutWrapper(SnapLayoutWrapper snapLayoutWrapper, boolean isSelectable) {
        super(snapLayoutWrapper.getModel(), snapLayoutWrapper.getViewHolder(), snapLayoutWrapper.getLayoutId(), snapLayoutWrapper.getLayoutType());
        this.isSelectable = isSelectable;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setIsSelectable(boolean isSelectable) {
        this.isSelectable = isSelectable;
    }
}
