package io.github.prashantsolanki3.snaplibrary.snap.endless;

import android.support.v7.widget.RecyclerView;

import io.github.prashantsolanki3.snaplibrary.snap.AbstractSnapMultiAdapter;

/**
 * Created by Prashant on 12/13/2015.
 *
 */
public interface EndlessLoader {

    /**
     * Load more Items to your RecyclerView.
     * Do check for class type if you are using AbstractSnapMultiAdapter provided in params.
     * */
    void loadMore(final AbstractSnapMultiAdapter adapter,final int pageNo);

    /**
     * The stock onScrolled method provided by ReyclerView.onScrollListener.
     * */
    void onScrolled(RecyclerView recyclerView,int dx, int dy);

}
