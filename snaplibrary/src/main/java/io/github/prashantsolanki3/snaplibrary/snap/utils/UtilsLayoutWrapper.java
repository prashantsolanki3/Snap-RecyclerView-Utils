package io.github.prashantsolanki3.snaplibrary.snap.utils;

import java.util.List;

import io.github.prashantsolanki3.snaplibrary.snap.layout.viewholder.SnapSelectableViewHolder;
import io.github.prashantsolanki3.snaplibrary.snap.layout.wrapper.SnapLayoutWrapper;
import io.github.prashantsolanki3.snaplibrary.snap.layout.wrapper.SnapSelectableLayoutWrapper;

/**
 * Package io.github.prashantsolanki3.snaplibrary.snap.utils
 * <p>
 * Created by Prashant on 1/5/2016.
 * <p>
 * Email: solankisrp2@gmail.com
 * Github: @prashantsolanki3
 */
public class UtilsLayoutWrapper {

    public static SnapLayoutWrapper getWrapperFromType(List<SnapLayoutWrapper> layoutWrappers, int type) {
        for (SnapLayoutWrapper wrapper : layoutWrappers)
            if (wrapper.getLayoutType().equals(type))
                return wrapper;
        return null;
    }

    public static SnapLayoutWrapper getWrapperFromModel(List<SnapLayoutWrapper> layoutWrappers, Class<?> className) {
        for (SnapLayoutWrapper wrapper : layoutWrappers) {
            if (wrapper.getModel().getName().equals(className.getName()))
                return wrapper;
        }

        throw new RuntimeException("Please Check the SnapLayoutWrapper and the input Dataset Classes");
    }

    public static boolean isViewHolderSelectable(List<SnapLayoutWrapper> layoutWrappers, SnapSelectableViewHolder viewHolder) {
        for (SnapLayoutWrapper wrapper : layoutWrappers) {
            if (wrapper.getViewHolder().getName().equals(viewHolder.getClass().getName()))
                return ((SnapSelectableLayoutWrapper) wrapper).isSelectable();
        }
        throw new RuntimeException("Please Check the SnapSelectableLayoutWrapper and the input Dataset Classes");
    }

    public static boolean isViewHolderTypeSelectable(List<SnapLayoutWrapper> layoutWrappers, int type) {

        SnapSelectableLayoutWrapper wrapper = (SnapSelectableLayoutWrapper) getWrapperFromType(layoutWrappers, type);

        if (wrapper != null)
            return wrapper.isSelectable();

        throw new RuntimeException("Please Check the SnapSelectableLayoutWrapper and the input Dataset Classes");
    }

}
