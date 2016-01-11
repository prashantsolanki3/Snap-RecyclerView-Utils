package io.prashantslolanki3.snaprecyclerview.sample.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.github.prashantsolanki3.snaprecyclerviewutils.R;

public abstract class BaseRecyclerViewActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public Toolbar toolbar;
    public FloatingActionButton fab;
    public AppBarLayout appBarLayout;
    public CollapsingToolbarLayout collapsingToolbarLayout;

    public
    @LayoutRes
    int getContentLayoutId() {
        return R.layout.activity_base_recycler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutId());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab_add_items);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        init(savedInstanceState);
        recyclerView.setHasFixedSize(true);
        setLayoutManager(recyclerView);
        setAdapter(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFabOnClickAction((FloatingActionButton) view);
            }
        });
    }

    public abstract void setLayoutManager(RecyclerView recyclerView);

    public abstract void setAdapter(RecyclerView recyclerView);

    public abstract void setFabOnClickAction(FloatingActionButton view);

    public abstract void init(Bundle savedInstanceState);

    public FloatingActionButton getFab() {
        return fab;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }


}

