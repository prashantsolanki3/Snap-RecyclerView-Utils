package io.prashantslolanki3.snaprecyclerview.sample.ui.pickers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import java.util.ArrayList;
import java.util.List;

import io.github.prashantsolanki3.snaplibrary.snap.selectable.AbstractSnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SelectionListener;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableLayoutWrapper;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.github.prashantsolanki3.utiloid.U;
import io.prashantslolanki3.snaprecyclerview.sample.ui.BaseRecyclerViewActivity;
import io.prashantslolanki3.snaprecyclerview.sample.ui.pickers.viewpager.ImagesViewPagerAdapter;
import io.prashantslolanki3.snaprecyclerview.sample.viewholders.ImageViewHolder;

/**
 * Created by Prashant on 1/9/2016.
 */
public class SnapImagePickerActivity extends BaseRecyclerViewActivity {

    private int limit = 1;
    private static final String ARG_SELECTION_LIMIT = "limit";
    private static final String ARG_ARRAY_GALLERY_ITEM_URI = "gallery_item_uri_array";
    private static final String ARG_ARRAY_GALLERY_ITEM_ORIENTATION = "gallery_item_orientation_array";

    SnapSelectableAdapter<String> adapter;
    ViewPager viewPager;
    ImagesViewPagerAdapter imagesViewPagerAdapter;
    AbstractSnapSelectableAdapter.SelectionType selectionType;

    public static Intent getIntent(Context context, int selectionLimit) {
        Intent intent = new Intent(context, SnapImagePickerActivity.class);
        intent.putExtra(ARG_SELECTION_LIMIT, selectionLimit);
        return intent;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_gallery;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        getFab().setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_close).colorRes(android.R.color.white));
        if (getIntent() != null) {
            limit = getIntent().getIntExtra(ARG_SELECTION_LIMIT, 1);
        }

        if (getIntent() != null && getIntent().getAction() != null && getIntent().getAction().equals(Intent.ACTION_PICK)) {
            limit = 1;
        }

        //Set SelectionType according to the Selection Limit.
        selectionType = limit == 1 ? AbstractSnapSelectableAdapter.SelectionType.SINGLE : AbstractSnapSelectableAdapter.SelectionType.MULTIPLE;

        collapsingToolbarLayout.setTitleEnabled(false);
        appBarLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) U.getScreenWidthPixels()));
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setLayoutParams(new CollapsingToolbarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) U.getScreenWidthPixels()));
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appBarLayout.setExpanded(true, true);
            }
        });
    }

    @Nullable
    public static List<String> getResult(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            List<String> items = data.getStringArrayListExtra(ARG_ARRAY_GALLERY_ITEM_URI);
            return items;
        } else
            return null;
    }

    @Override
    public void setLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    public void setAdapter(RecyclerView recyclerView) {
        SnapSelectableLayoutWrapper wrapper = new SnapSelectableLayoutWrapper(String.class,
                ImageViewHolder.class,
                R.layout.item_image_layout,
                1, true);

        adapter = new SnapSelectableAdapter<>(this,
                wrapper,
                recyclerView,
                selectionType);

        adapter.setSelectionLimit(limit);
        adapter.setOnSelectionListener(new SelectionListener<String>() {
            @Override
            public void onSelectionModeEnabled(AbstractSnapSelectableAdapter.SelectionType selectionType) {

            }

            @Override
            public void onSelectionModeDisabled(AbstractSnapSelectableAdapter.SelectionType selectionType) {

            }

            @Override
            public void onItemSelected(String item, int pos) {
                getFab().setImageDrawable(new IconDrawable(getApplicationContext(),
                        FontAwesomeIcons.fa_check)
                        .colorRes(android.R.color.white));

                Glide.with(getApplicationContext())
                        .load(item)
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                Palette.from(resource)
                                        .generate(new Palette.PaletteAsyncListener() {
                                            @Override
                                            public void onGenerated(Palette palette) {
                                                Palette.Swatch swatch = palette.getVibrantSwatch();
                                                if (!U.isNull(swatch))
                                                    collapsingToolbarLayout.setContentScrimColor(swatch.getRgb());
                                            }
                                        });
                            }
                        });

                if (adapter.selectionType == AbstractSnapSelectableAdapter.SelectionType.SINGLE)
                    appBarLayout.setExpanded(true, true);

                imagesViewPagerAdapter.add(item);
            }

            @Override
            public void onItemDeselected(String item, int pos) {
                imagesViewPagerAdapter.remove(item);
            }

            @Override
            public void onSelectionLimitReached() {

            }

            @Override
            public void onSelectionLimitExceeding() {
                Snackbar.make(getFab(), "Select " + adapter.getSelectionLimit() + " only.", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onNoneSelected() {
                getFab().setImageDrawable(new IconDrawable(getApplicationContext(),
                        FontAwesomeIcons.fa_close)
                        .colorRes(android.R.color.white));
            }
        });
        recyclerView.setAdapter(adapter);
        populateGalleryView();
    }

    @Override
    public void setFabOnClickAction(FloatingActionButton view) {

        if (adapter.getSelectedItems().isEmpty()) {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }

        Intent intent = new Intent();
        if (getIntent().getAction() == null) {
            List<String> selection = adapter.getSelectedItems();
            intent.putExtra(ARG_ARRAY_GALLERY_ITEM_URI, new ArrayList<>(selection));
            setResult(Activity.RESULT_OK, intent);
            this.finish();
        } else if (getIntent().getAction().equals(Intent.ACTION_PICK)) {
            intent.setData(Uri.parse(adapter.getSelectedItems().get(0)));
            setResult(Activity.RESULT_OK, intent);
            this.finish();
        }
    }

    void populateGalleryView() {

        Cursor imageCursor = null;
        try {
            final String[] columns = {MediaStore.Images.Media.DATA};
            final String orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC";
            imageCursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);

            if (imageCursor != null)
                while (imageCursor.moveToNext()) {
                    adapter.add(imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                }

            imagesViewPagerAdapter = new ImagesViewPagerAdapter(getSupportFragmentManager(), adapter.getItemCount() > 0 ? adapter.getItem(0) : "");
            viewPager.setAdapter(imagesViewPagerAdapter);
            viewPager.setOffscreenPageLimit(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (imageCursor != null && !imageCursor.isClosed()) {
                imageCursor.close();
            }
        }

    }




}

/*TODO: OnClick Open FullScreen Activity.
  TODO: Remove Title.
  TODO: Give Options ro change color of toolbar, enable/disable dynamic color,
  TODO: FAB colors and icons for 3 states - none selected, some selected, max selected.
  TODO: Set Placeholder for ViewPager.*/