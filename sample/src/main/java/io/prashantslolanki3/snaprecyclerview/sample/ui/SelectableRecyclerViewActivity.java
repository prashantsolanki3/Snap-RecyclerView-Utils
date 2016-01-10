package io.prashantslolanki3.snaprecyclerview.sample.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import java.util.ArrayList;

import io.github.prashantsolanki3.snaplibrary.snap.AbstractSnapMultiAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.endless.EndlessLoader;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.AbstractSnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SelectionActionModeListener;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableLayoutWrapper;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.model.PictureCaption;
import io.prashantslolanki3.snaprecyclerview.sample.model.SampleData;
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
        getFab().setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_share_alt).colorRes(android.R.color.white));
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

        adapter = new SnapSelectableAdapter(this,
                wrappers,
                recyclerView,
                AbstractSnapSelectableAdapter.SelectionType.MULTIPLE);

        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 25; i++) {
            adapter.add(SampleData.getPictureCaption());
        }

        adapter.setEndlessLoader(5, new EndlessLoader() {
            @Override
            public void loadMore(AbstractSnapMultiAdapter adapter, int pageNo) {
                for (int i = 0; i < 25; i++) {
                    adapter.add(SampleData.getPictureCaption());
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });


        adapter.setOnSelectionListener(new SelectionActionModeListener(getToolbar()) {
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
            public void onItemSelected(Object item, int pos) {

            }

            @Override
            public void onItemDeselected(Object item, int pos) {

            }

            @Override
            public void onNoneSelected() {

            }

            @Override
            public void onSelectionLimitReached() {
                getActionMode().setTitle("Max Selected");
            }
        });

    }
}
