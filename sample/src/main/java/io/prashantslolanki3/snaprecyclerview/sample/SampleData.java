package io.prashantslolanki3.snaprecyclerview.sample;

import java.util.ArrayList;
import java.util.Random;

import io.prashantslolanki3.snaprecyclerview.sample.model.HorizontalRecyclerModel;
import io.prashantslolanki3.snaprecyclerview.sample.model.PictureCaption;

/**
 * Package io.prashantslolanki3.snaprecyclerview.sample
 * <p/>
 * Created by Prashant on 12/18/2015.
 * <p/>
 * Email: solankisrp2@gmail.com
 * Github: @prashantsolanki3
 */
public class SampleData {

    public static ArrayList<HorizontalRecyclerModel> getHorizontalRecyclerModels(int noOfItems, int noOfImages) {
        ArrayList<HorizontalRecyclerModel> models = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < noOfItems; i++) {
            models.add(new HorizontalRecyclerModel("Title: " + random.nextInt(),
                    getImageUrls(noOfImages)));
        }
        return models;
    }

    public static ArrayList<String> getImageUrls(int no) {
        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < no; i++) {
            strings.add("http://lorempixel.com/300/300/abstract/These%20are%20Random%20Images%20" + new Random().nextInt() + "/");
        }

        return strings;
    }


    public static PictureCaption getPictureCaption() {
        return new PictureCaption("Caption", getImageUrls(1).get(0));
    }

}
