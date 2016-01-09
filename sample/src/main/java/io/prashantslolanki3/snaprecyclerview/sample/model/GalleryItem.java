package io.prashantslolanki3.snaprecyclerview.sample.model;

/**
 * Created by Prashant on 6/29/2015.
 */
public class GalleryItem {
    private String imageUri;
    private int orientation;
    private double latitude;
    private double longitude;
    private String thumbnailUri;
    private int height, width;

    public GalleryItem() {
    }


    public int getHeight() {
        return height;

    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setThumbnailUri(String thumbnailUri) {
        this.thumbnailUri = thumbnailUri;
    }

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }


    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}
