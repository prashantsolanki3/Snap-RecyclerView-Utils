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
 */
public class SnapAdapter<T, VH extends SnapViewHolder> extends AbstractSnapMultiAdapter<T> {

    public SnapAdapter(@NonNull Context context, @NonNull Class<T> modelClass, @LayoutRes int itemLayout, @NonNull Class<VH> viewHolderClass) {
        super(context,
                new ArrayList<>(Collections.singletonList(new SnapMultiViewWrapper(modelClass,
                        viewHolderClass,
                        itemLayout,
                        0))));
    }
}