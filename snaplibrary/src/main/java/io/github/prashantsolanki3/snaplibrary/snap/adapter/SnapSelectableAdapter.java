package io.github.prashantsolanki3.snaplibrary.snap.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.github.prashantsolanki3.snaplibrary.snap.layout.wrapper.SnapLayoutWrapper;
import io.github.prashantsolanki3.snaplibrary.snap.layout.wrapper.SnapSelectableLayoutWrapper;

/**
 * Created by Prashant on 1/8/2016.
 *
 */
public class SnapSelectableAdapter<T> extends AbstractSnapSelectableAdapter<T> {

    public SnapSelectableAdapter(@NonNull Context context,
                                 @NonNull SnapSelectableLayoutWrapper wrapper,
                                 RecyclerView recyclerView,
                                 SelectionType selectionType) {
        super(context, wrapper, recyclerView, selectionType);

    }

    public SnapSelectableAdapter(@NonNull Context context,
                                 ArrayList<SnapLayoutWrapper> layoutWrappers,
                                 RecyclerView recyclerView,
                                 SelectionType selectionType) {
        super(context, layoutWrappers, recyclerView, selectionType);
    }

    /**
     * @param context       Context.
     * @param wrapper       SnapLayoutWrapper
     * @param recyclerView
     * @param alternateView
     * @param selectionType
     */
    public SnapSelectableAdapter(@NonNull Context context,
                                 @NonNull SnapSelectableLayoutWrapper wrapper,
                                 RecyclerView recyclerView,
                                 ViewGroup alternateView,
                                 SelectionType selectionType) {
        super(context, wrapper, recyclerView, alternateView, selectionType);
    }

    public SnapSelectableAdapter(@NonNull Context context,
                                 ArrayList<SnapLayoutWrapper> layoutWrappers,
                                 RecyclerView recyclerView,
                                 ViewGroup alternateView,
                                 SelectionType selectionType) {
        super(context, layoutWrappers, recyclerView, alternateView, selectionType);
    }


    //TODO: Builder to make adapters

}
