package io.github.prashantsolanki3.snaplibrary.snap;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Prashant Solanki.
 * <p/>
 * Github: prashantsolanki3
 * Email: prs.solanki@live.com
 *
 * To Create a simple RecyclerView Adapter with Single Layout.
 */
public class SnapAdapter<T, VH extends SnapViewHolder> extends AbstractSnapMultiAdapter<T> {

    /**
     * @param context         Context.
     * @param modelClass      Model Class to be used by SnapAdapter.
     * @param itemLayout      Layout id of RecyclerView Item.
     * @param viewHolderClass ViewHolder Class to be used by SnapAdapter.
     */
    public SnapAdapter(@NonNull Context context, @NonNull Class<T> modelClass, @LayoutRes int itemLayout, @NonNull Class<VH> viewHolderClass) {
        super(context,
                new ArrayList<>(Collections.singletonList(new SnapLayoutWrapper(modelClass,
                        viewHolderClass,
                        itemLayout,
                        0))));
    }

    public SnapAdapter(@NonNull Context context, SnapLayoutWrapper wrapper) {
        super(context, new ArrayList<>(Collections.singletonList(wrapper)));
    }
}