package io.prashantslolanki3.snaprecyclerview.sample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.github.prashantsolanki3.snaprecyclerviewutils.R;

public abstract class BaseRecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        setLayoutManager(recyclerView);
        setAdapter(recyclerView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_items);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFabOnClickAction(view);
            }
        });
    }

    public abstract void setLayoutManager(RecyclerView recyclerView);

    public abstract void setAdapter(RecyclerView recyclerView);

    public abstract void setFabOnClickAction(View view);


}
