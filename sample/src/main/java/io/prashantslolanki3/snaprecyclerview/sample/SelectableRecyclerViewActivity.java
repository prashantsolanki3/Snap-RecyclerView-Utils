package io.prashantslolanki3.snaprecyclerview.sample;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.github.prashantsolanki3.snaplibrary.snap.AbstractSnapMultiAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.endless.EndlessLoader;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.AbstractSnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableLayoutWrapper;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableViewHolder;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.model.PictureCaption;
import io.prashantslolanki3.snaprecyclerview.sample.viewholders.SelectableSinglePictureCaptionViewHolder;

public class SelectableRecyclerViewActivity extends BaseRecyclerViewActivity {

    SnapSelectableAdapter<PictureCaption> adapter;

    @Override
    public void setLayoutManager(RecyclerView recyclerView) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void setAdapter(RecyclerView recyclerView) {
        SnapSelectableLayoutWrapper wrapper = new SnapSelectableLayoutWrapper(PictureCaption.class,
                SelectableSinglePictureCaptionViewHolder.class,
                R.layout.item_pictue_caption_layout,
                1,
                true);

        adapter = new SnapSelectableAdapter<>(this,
                wrapper,
                AbstractSnapSelectableAdapter.SelectionType.MULTIPLE_ON_LONG_PRESS,
                recyclerView);

        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 25; i++) {
            adapter.add(SampleData.getPictureCaption());
        }

        adapter.setEndlessLoader(5, new EndlessLoader<PictureCaption>() {
            @Override
            public void loadMore(AbstractSnapMultiAdapter<PictureCaption> adapter, int pageNo) {
                for (int i = 0; i < 25; i++) {
                    adapter.add(SampleData.getPictureCaption());
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });

        adapter.setOnSelectionListener(new AbstractSnapSelectableAdapter.SelectionListener() {

            ActionMode.Callback callback = new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    mode.setTitle("Action Mode");
                    return true;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                }
            };

            ActionMode actionMode = null;

            @Override
            public void onSelectionModeEnabled(AbstractSnapSelectableAdapter.SelectionType selectionType) {
                actionMode = getToolbar().startActionMode(callback);
            }

            @Override
            public void onSelectionModeDisabled(AbstractSnapSelectableAdapter.SelectionType selectionType) {
                if (actionMode != null) {
                    actionMode.finish();
                }
            }

            @Override
            public void onItemSelected(SnapSelectableViewHolder holder, int pos) {

            }

            @Override
            public void onItemDeselected(SnapSelectableViewHolder holder, int pos) {

            }
        });

    }

    @Override
    public void setFabOnClickAction(View view) {

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
