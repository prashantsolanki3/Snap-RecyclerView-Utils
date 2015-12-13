package io.github.prashantsolanki3.snaplibrary.snap.endless;

import android.support.v7.widget.RecyclerView;

import io.github.prashantsolanki3.snaplibrary.snap.AbstractSnapMultiAdapter;

/**
 * Created by Prashant on 12/13/2015.
 */
public interface EndlessLoader {

    void loadMore(final AbstractSnapMultiAdapter adapter,final int pageNo);
    void onScrolled(RecyclerView recyclerView,int dx, int dy);

}
