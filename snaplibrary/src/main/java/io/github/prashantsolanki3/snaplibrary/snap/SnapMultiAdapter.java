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

    public SnapMultiAdapter(@NonNull Context context, ArrayList<SnapMultiViewWrapper> multiViewWrappers) {
        super(context, multiViewWrappers);
    }

}