package io.prashantslolanki3.snaprecyclerview.sample.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import io.github.prashantsolanki3.snaplibrary.snap.selectable.AbstractSnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableLayoutWrapper;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.model.GalleryItem;
import io.prashantslolanki3.snaprecyclerview.sample.viewholders.ImageViewHolder;

/**
 * Created by Prashant on 1/9/2016.
 */
public class GalleryActivity extends BaseRecyclerViewActivity {

    SnapSelectableAdapter<GalleryItem> adapter;

    @Override
    public void setLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @Override
    public void setAdapter(RecyclerView recyclerView) {
        SnapSelectableLayoutWrapper wrapper = new SnapSelectableLayoutWrapper(GalleryItem.class,
                ImageViewHolder.class,
                R.layout.item_image_layout,
                1, true);

        adapter = new SnapSelectableAdapter<>(this,
                wrapper,
                recyclerView,
                AbstractSnapSelectableAdapter.SelectionType.MULTIPLE_ON_LONG_PRESS);

        recyclerView.setAdapter(adapter);
        populateGalleryView();
    }

    @Override
    public void setFabOnClickAction(FloatingActionButton view) {
        view.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_share));
    }

    void populateGalleryView() {

        Cursor imageCursor = null;
        try {
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.ImageColumns.ORIENTATION,
                    MediaStore.Images.Media.LATITUDE, MediaStore.Images.Media.LONGITUDE, MediaStore.Images.Thumbnails.DATA,
                    MediaStore.Images.ImageColumns.HEIGHT, MediaStore.Images.ImageColumns.WIDTH};
            final String orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC";
            imageCursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);


            if (imageCursor != null)
                while (imageCursor.moveToNext()) {
                    GalleryItem item = new GalleryItem();
                    item.setImageUri(imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                    item.setOrientation(imageCursor.getInt(imageCursor.getColumnIndex(MediaStore.Images.ImageColumns.ORIENTATION)));
                    item.setLatitude(imageCursor.getDouble(imageCursor.getColumnIndex(MediaStore.Images.ImageColumns.LATITUDE)));
                    item.setLongitude(imageCursor.getDouble(imageCursor.getColumnIndex(MediaStore.Images.ImageColumns.LONGITUDE)));
                    item.setThumbnailUri(imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA)));
                    item.setHeight(imageCursor.getInt(imageCursor.getColumnIndex(MediaStore.Images.ImageColumns.HEIGHT)));
                    item.setWidth(imageCursor.getInt(imageCursor.getColumnIndex(MediaStore.Images.ImageColumns.WIDTH)));
                    adapter.add(item);
                    // Log.d(getClass().getSimpleName(),item.toString());
                }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (imageCursor != null && !imageCursor.isClosed()) {
                imageCursor.close();
            }
        }
    }
}
