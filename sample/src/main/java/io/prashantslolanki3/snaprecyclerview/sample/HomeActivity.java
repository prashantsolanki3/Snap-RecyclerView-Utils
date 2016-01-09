package io.prashantslolanki3.snaprecyclerview.sample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import io.github.prashantsolanki3.snaplibrary.snap.SnapAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.SnapLayoutWrapper;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.model.HomeItem;
import io.prashantslolanki3.snaprecyclerview.sample.ui.BaseRecyclerViewActivity;
import io.prashantslolanki3.snaprecyclerview.sample.ui.GalleryActivity;
import io.prashantslolanki3.snaprecyclerview.sample.ui.MultiLayoutActivity;
import io.prashantslolanki3.snaprecyclerview.sample.ui.SelectableRecyclerViewActivity;
import io.prashantslolanki3.snaprecyclerview.sample.viewholders.HomeVH;

/**
 * Created by Prashant on 1/9/2016.
 */
public class HomeActivity extends BaseRecyclerViewActivity {

    @Override
    public void init(Bundle savedInstanceState) {
        getFab().setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_share).color(android.R.color.white));
    }

    @Override
    public void setLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void setAdapter(RecyclerView recyclerView) {
        SnapAdapter<HomeItem> adapter = new SnapAdapter<>(this, new SnapLayoutWrapper(HomeItem.class,
                HomeVH.class, R.layout.item_home_layout, 1), recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.add(new HomeItem("Simple", MultiLayoutActivity.class));
        adapter.add(new HomeItem("Selectable", SelectableRecyclerViewActivity.class));
        adapter.add(new HomeItem("Gallery", GalleryActivity.class));
    }

    @Override
    public void setFabOnClickAction(FloatingActionButton view) {

    }
}
