package io.github.prashantsolanki3.snaplibrary.snap;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

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

    /**
     * @param context         Context.
     * @param modelClass      Model Class to be used by SnapAdapter.
     * @param itemLayout      Layout id of RecyclerView Item.
     * @param viewHolderClass ViewHolder Class to be used by SnapAdapter.
     * @param recyclerView    Corresponding RecyclerView
     */
    public SnapAdapter(@NonNull Context context, @NonNull Class<T> modelClass, @LayoutRes int itemLayout, @NonNull Class<VH> viewHolderClass, @NonNull RecyclerView recyclerView) {
        super(context,
                new ArrayList<>(Collections.singletonList(new SnapLayoutWrapper(modelClass,
                        viewHolderClass,
                        itemLayout,
                        0))), recyclerView);
    }

    /**
     * @param context         Context.
     * @param modelClass      Model Class to be used by SnapAdapter.
     * @param itemLayout      Layout id of RecyclerView Item.
     * @param viewHolderClass ViewHolder Class to be used by SnapAdapter.
     * @param recyclerView    Corresponding RecyclerView
     * @param alternateView   ViewGroup that will contain the alternative views like Empty Recycler View, Loading View, etc.
     */
    public SnapAdapter(@NonNull Context context,
                       @NonNull Class<T> modelClass,
                       @LayoutRes int itemLayout,
                       @NonNull Class<VH> viewHolderClass,
                       @NonNull RecyclerView recyclerView,
                       @NonNull ViewGroup alternateView) {
        super(context,
                new ArrayList<>(Collections.singletonList(new SnapLayoutWrapper(modelClass,
                        viewHolderClass,
                        itemLayout,
                        0))), recyclerView, alternateView);
    }

    /**
     * @param context         Context.
     * @param itemLayout      Layout id of RecyclerView Item.
     * @param viewHolderClass ViewHolder Class to be used by SnapAdapter.
     */
    @Deprecated
    public SnapAdapter(@NonNull Context context,
                       @LayoutRes int itemLayout,
                       @NonNull Class<VH> viewHolderClass) {
        super(context,
                new ArrayList<>(Collections.singletonList(new SnapLayoutWrapper(Object.class,
                        viewHolderClass,
                        itemLayout,
                        0))));
    }


    /**
     * @param context         Context.
     * @param wrapper SnapLayoutWrapper
     */
    public SnapAdapter(@NonNull Context context,
                       @NonNull SnapLayoutWrapper wrapper) {
        super(context, new ArrayList<>(Collections.singletonList(wrapper)));
    }

    /**
     * @param context      Context.
     * @param wrapper      SnapLayoutWrapper
     * @param recyclerView Corresponding RecyclerView
     */
    public SnapAdapter(@NonNull Context context,
                       @NonNull SnapLayoutWrapper wrapper,
                       @NonNull RecyclerView recyclerView) {
        super(context, new ArrayList<>(Collections.singletonList(wrapper)), recyclerView);
    }

    /**
     * @param context       Context.
     * @param wrapper       SnapLayoutWrapper
     * @param recyclerView  Corresponding RecyclerView
     * @param alternateView ViewGroup that will contain the alternative views like Empty Recycler View, Loading View, etc.
     */
    public SnapAdapter(@NonNull Context context,
                       @NonNull SnapLayoutWrapper wrapper,
                       @NonNull RecyclerView recyclerView,
                       @NonNull ViewGroup alternateView) {
        super(context, new ArrayList<>(Collections.singletonList(wrapper)), recyclerView, alternateView);
    }
}