package io.github.prashantsolanki3.snaplibrary.snap;

import android.support.annotation.LayoutRes;

/**
 * Created by Prashant on 12/12/2015.
 */
public class SnapLayoutWrapper {

    Class model;
    Class<? extends SnapViewHolder> viewHolder;
    @LayoutRes
    Integer layoutId;
    Integer layoutType;

    public SnapLayoutWrapper(Class model,
                             Class<? extends SnapViewHolder> viewHolder,
                             @LayoutRes Integer layoutId,
                             Integer layoutType) {
        this.model = model;
        this.viewHolder = viewHolder;
        this.layoutId = layoutId;
        this.layoutType = layoutType;
    }

    public Class getModel() {
        return model;
    }

    public Class<? extends SnapViewHolder> getViewHolder() {
        return viewHolder;
    }

    public Integer getLayoutId() {
        return layoutId;
    }

    public Integer getLayoutType() {
        return layoutType;
    }


}
