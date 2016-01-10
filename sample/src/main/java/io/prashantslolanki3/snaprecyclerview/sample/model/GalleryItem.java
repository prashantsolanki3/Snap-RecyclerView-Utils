package io.prashantslolanki3.snaprecyclerview.sample.model;

/**
 * Created by Prashant on 6/29/2015.
 */
public class GalleryItem {
    private String imageUri;
    private int orientation;

    public GalleryItem() {
    }

    public GalleryItem(String imageUri, int orientation) {
        this.imageUri = imageUri;
        this.orientation = orientation;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
