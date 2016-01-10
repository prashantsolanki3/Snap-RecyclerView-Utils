package io.prashantslolanki3.snaprecyclerview.sample.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import java.util.ArrayList;
import java.util.List;

import io.github.prashantsolanki3.snaplibrary.snap.selectable.AbstractSnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SelectionActionModeListener;
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
    private int limit = 1;
    private static final String ARG_SELECTION_LIMIT = "limit";
    private static final String ARG_ARRAY_GALLERY_ITEM_URI = "gallery_item_uri_array";
    private static final String ARG_ARRAY_GALLERY_ITEM_ORIENTATION = "gallery_item_orientation_array";

    public static Intent getIntent(Context context, int selectionLimit) {
        Intent intent = new Intent(context, GalleryActivity.class);
        intent.putExtra(ARG_SELECTION_LIMIT, selectionLimit);
        return intent;
    }

    @Nullable
    public static List<GalleryItem> getResult(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            List<String> items = data.getStringArrayListExtra(ARG_ARRAY_GALLERY_ITEM_URI);
            List<Integer> orientation = data.getIntegerArrayListExtra(ARG_ARRAY_GALLERY_ITEM_ORIENTATION);
            List<GalleryItem> galleryItems = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                galleryItems.add(new GalleryItem(items.get(i), orientation.get(i)));
            }
            return galleryItems;
        } else
            return null;
    }

    @Override
    public void setLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    public void init(Bundle savedInstanceState) {
        getFab().setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_check).colorRes(android.R.color.white));
        if (getIntent() != null) {
            limit = getIntent().getIntExtra(ARG_SELECTION_LIMIT, 1);

        }
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

        adapter.setSelectionLimit(limit);
        adapter.setOnSelectionListener(new SelectionActionModeListener<GalleryItem>(getToolbar()) {
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.menu_actionmode, menu);
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_select_all) {
                    adapter.selectAll();
                    return false;
                }
                if (id == R.id.action_deselect_all) {
                    adapter.deselectAll();
                    return false;
                }

                return false;
            }

            @Override
            public void onItemSelected(GalleryItem item, int pos) {

            }

            @Override
            public void onItemDeselected(GalleryItem item, int pos) {

            }

            @Override
            public void onSelectionLimitReached() {
                getActionMode().setTitle("Max Selected");
            }

            @Override
            public void onNoneSelected() {

            }
        });

        recyclerView.setAdapter(adapter);
        populateGalleryView();
    }

    @Override
    public void setFabOnClickAction(FloatingActionButton view) {
        Intent intent = new Intent();
        ArrayList<Integer> orientations = new ArrayList<>();
        ArrayList<String> uris = new ArrayList<>();
        List<GalleryItem> selection = adapter.getSelectedItems();

        for (int i = 0; i < selection.size(); i++) {
            orientations.add(selection.get(i).getOrientation());
            uris.add(selection.get(i).getImageUri());
        }

        intent.putExtra(ARG_ARRAY_GALLERY_ITEM_ORIENTATION, orientations);
        intent.putExtra(ARG_ARRAY_GALLERY_ITEM_URI, uris);

        setResult(Activity.RESULT_OK, intent);
    }

    void populateGalleryView() {

        Cursor imageCursor = null;
        try {
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.ImageColumns.ORIENTATION};
            final String orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC";
            imageCursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);

            if (imageCursor != null)
                while (imageCursor.moveToNext()) {
                    GalleryItem item = new GalleryItem();
                    item.setImageUri(imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                    item.setOrientation(imageCursor.getInt(imageCursor.getColumnIndex(MediaStore.Images.ImageColumns.ORIENTATION)));
                    adapter.add(item);
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
