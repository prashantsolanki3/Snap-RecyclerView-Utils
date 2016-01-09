package io.prashantslolanki3.snaprecyclerview.sample.model;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Prashant on 1/9/2016.
 */
public class HomeItem {

    String title;
    Class t;

    public HomeItem(String title, Class t) {
        this.title = title;
        this.t = t;
    }

    public String getTitle() {
        return title;
    }

    public void startActivity(Context context) {
        context.startActivity(new Intent(context, t));
    }

}
