package io.prashantslolanki3.snaprecyclerview.sample.model;

import java.util.ArrayList;

/**
 * Created by Prashant on 12/12/2015.
 */
public class HorizontalRecyclerModel {

    String title;
    ArrayList<String> images;

    public HorizontalRecyclerModel(String title, ArrayList<String> images) {
        this.title = title;
        this.images = images;
    }

    public HorizontalRecyclerModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}

