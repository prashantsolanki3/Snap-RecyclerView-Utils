package io.github.prashantsolanki3.snaplibrary.snap.selectable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Prashant on 1/8/2016.
 */
public class SnapSelectableAdapter<T> extends AbstractSnapSelectableAdapter<T> {

    public SnapSelectableAdapter(@NonNull Context context,
                                 @NonNull SnapSelectableLayoutWrapper wrapper,
                                 SelectionType selectionType,
                                 RecyclerView recyclerView) {
        super(context, wrapper, selectionType, recyclerView);
    }

    //TODO: Builder to make adapters

}
