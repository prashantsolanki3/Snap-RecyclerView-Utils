package io.prashantslolanki3.snaprecyclerview.sample.model;

/**
 * Package io.prashantslolanki3.snaprecyclerview.sample.model
 * <p/>
 * Created by Prashant on 12/18/2015.
 * <p/>
 * Email: solankisrp2@gmail.com
 * Github: @prashantsolanki3
 */
public class PictureCaption {

    String caption;
    String url;

    public PictureCaption(String caption, String url) {
        this.caption = caption;
        this.url = url;
    }

    public PictureCaption() {
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
