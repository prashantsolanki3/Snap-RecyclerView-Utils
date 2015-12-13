package io.github.prashantsolanki3.snaplibrary.snap;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Prashant Solanki.
 * <p/>
 * Github: prashantsolanki3
 * Email: prs.solanki@live.com
 */
public class SnapMultiAdapter extends AbstractSnapMultiAdapter<Object> {

    /**
     * @param context           Context.
     * @param multiViewWrappers ArrayList Containing SnapLayoutWrapper.
     */
    public SnapMultiAdapter(@NonNull Context context, @NonNull ArrayList<SnapLayoutWrapper> multiViewWrappers) {
        super(context, multiViewWrappers);
    }

}