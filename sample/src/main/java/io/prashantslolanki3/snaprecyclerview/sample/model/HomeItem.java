package io.prashantslolanki3.snaprecyclerview.sample.model;

import android.support.v4.app.FragmentActivity;

/**
 * Created by Prashant on 1/9/2016.
 */
public class HomeItem {

    String title;
    Class<? extends FragmentActivity> activity;

    public HomeItem(String title, Class<? extends FragmentActivity> activity) {
        this.title = title;
        this.activity = activity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getActivity() {
        return activity;
    }
}
