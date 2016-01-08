package io.github.prashantsolanki3.snaplibrary.snap;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Prashant Solanki.
 * <p/>
 * Github: prashantsolanki3
 * Email: prs.solanki@live.com
 *
 * SnapMultiAdapter has been deprecated,use SnapAdapter class.
 * It includes new Constructors similar to SnapMultiAdapter.
 */
@Deprecated
public class SnapMultiAdapter extends SnapAdapter<Object> {


    /**
     * @param context           Context.
     * @param multiViewWrappers ArrayList Containing SnapLayoutWrapper.
     */
    @Deprecated
    public SnapMultiAdapter(@NonNull Context context, @NonNull ArrayList<SnapLayoutWrapper> multiViewWrappers) {
        super(context, multiViewWrappers);
    }

    /**
     * @param context        Context.
     * @param layoutWrappers ArrayList Containing SnapLayoutWrapper.
     * @param recyclerView   Corresponding RecyclerView
     */
    public SnapMultiAdapter(@NonNull Context context, @NonNull ArrayList<SnapLayoutWrapper> layoutWrappers, @NonNull RecyclerView recyclerView) {
        super(context, layoutWrappers, recyclerView);
    }

    /**
     * @param context        Context.
     * @param layoutWrappers ArrayList Containing SnapLayoutWrapper.
     * @param recyclerView   Corresponding RecyclerView
     * @param alternateView  ViewGroup that will contain the alternative views like Empty Recycler View, Loading View, etc.
     */
    public SnapMultiAdapter(@NonNull Context context, @NonNull ArrayList<SnapLayoutWrapper> layoutWrappers, @NonNull RecyclerView recyclerView, @NonNull ViewGroup alternateView) {
        super(context, layoutWrappers, recyclerView, alternateView);
    }
}