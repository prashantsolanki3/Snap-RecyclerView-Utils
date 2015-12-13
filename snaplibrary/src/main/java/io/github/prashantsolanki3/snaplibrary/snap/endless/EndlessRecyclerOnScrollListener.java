package io.github.prashantsolanki3.snaplibrary.snap.endless;

import android.support.v7.widget.RecyclerView;

/**
* Custom Scroll listener for RecyclerView. 
* Based on implementation https://gist.github.com/ssinss/e06f12ef66c51252563e 
*/ 
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = "EndlessScrollListener";
 
 
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;
 
 
    private int currentPage = 1;

    public EndlessRecyclerOnScrollListener(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    RecyclerViewPositionHelper mRecyclerViewHelper;
 
 
    @Override 
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mRecyclerViewHelper = RecyclerViewPositionHelper.createHelper(recyclerView);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mRecyclerViewHelper.getItemCount();
        firstVisibleItem = mRecyclerViewHelper.findFirstVisibleItemPosition();

        onScroll(recyclerView,dx,dy);
 
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            } 
        } 
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached 
            // Do something 
            currentPage++;
 
 
            onLoadMore(currentPage);
 

            loading = true;
        } 
    } 
 
 
    //Start loading 
    public abstract void onLoadMore(int currentPage);
    public abstract void onScroll(RecyclerView recyclerView, int dx, int dy);
} 