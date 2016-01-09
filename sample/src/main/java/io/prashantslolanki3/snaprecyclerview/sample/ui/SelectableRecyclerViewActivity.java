package io.prashantslolanki3.snaprecyclerview.sample.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import io.github.prashantsolanki3.snaplibrary.snap.AbstractSnapMultiAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.endless.EndlessLoader;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.AbstractSnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SelectionActionModeListener;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableLayoutWrapper;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableViewHolder;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.SampleData;
import io.prashantslolanki3.snaprecyclerview.sample.model.PictureCaption;
import io.prashantslolanki3.snaprecyclerview.sample.viewholders.ImageViewHolder;
import io.prashantslolanki3.snaprecyclerview.sample.viewholders.SelectableSinglePictureCaptionViewHolder;

public class SelectableRecyclerViewActivity extends BaseRecyclerViewActivity {

    SnapSelectableAdapter adapter;

    @Override
    public void setLayoutManager(RecyclerView recyclerView) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void setFabOnClickAction(FloatingActionButton view) {

    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public void setAdapter(RecyclerView recyclerView) {
        ArrayList<SnapSelectableLayoutWrapper> wrappers = new ArrayList<>();
        wrappers.add(new SnapSelectableLayoutWrapper(PictureCaption.class,
                SelectableSinglePictureCaptionViewHolder.class,
                R.layout.item_pictue_caption_layout,
                1,
                true));

        wrappers.add(new SnapSelectableLayoutWrapper(String.class,
                ImageViewHolder.class,
                R.layout.item_image_layout,
                2, false));

        adapter = new SnapSelectableAdapter(this,
                wrappers,
                recyclerView,
                AbstractSnapSelectableAdapter.SelectionType.MULTIPLE_ON_LONG_PRESS);

        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 25; i++) {
            adapter.add(SampleData.getPictureCaption());
            adapter.add(SampleData.getImageUrls(1).get(0));
        }

        adapter.setEndlessLoader(5, new EndlessLoader() {
            @Override
            public void loadMore(AbstractSnapMultiAdapter adapter, int pageNo) {
                for (int i = 0; i < 25; i++) {
                    adapter.add(SampleData.getPictureCaption());
                    adapter.add(SampleData.getImageUrls(1));
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });


        adapter.setOnSelectionListener(new SelectionActionModeListener(getToolbar()) {

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onItemSelected(SnapSelectableViewHolder holder, int pos) {

            }

            @Override
            public void onItemDeselected(SnapSelectableViewHolder holder, int pos) {

            }

            @Override
            public void onSelectionLimitReached() {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selectable_recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
