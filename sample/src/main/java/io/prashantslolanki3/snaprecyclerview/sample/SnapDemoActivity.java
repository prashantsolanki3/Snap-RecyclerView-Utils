package io.prashantslolanki3.snaprecyclerview.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.prashantsolanki3.snaplibrary.snap.SnapMultiAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.SnapMultiViewWrapper;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.model.HorizontalRecyclerModel;
import io.prashantslolanki3.snaprecyclerview.sample.model.TextModel;
import io.prashantslolanki3.snaprecyclerview.sample.viewholders.HorizontalRecyclerViewHolder;
import io.prashantslolanki3.snaprecyclerview.sample.viewholders.TextViewHolder;

public class SnapDemoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SnapMultiAdapter multiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap_recycler_view_utils);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        ArrayList<SnapMultiViewWrapper> wrappers = new ArrayList<>();
        wrappers.add(new SnapMultiViewWrapper(HorizontalRecyclerModel.class, HorizontalRecyclerViewHolder.class, R.layout.item_header_layout, 1));
        wrappers.add(new SnapMultiViewWrapper(TextModel.class, TextViewHolder.class, R.layout.item_text_layout,3));

        multiAdapter = new SnapMultiAdapter(this,wrappers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(multiAdapter);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_snap_recycler_view_utils, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_recycler_item) {
            multiAdapter.add(new HorizontalRecyclerModel());
            return true;
        }

        if (id == R.id.action_add_slider_item) {
            multiAdapter.add(new TextModel());
            return true;
        }

        if (id == R.id.action_remove) {

            int pos = multiAdapter.getItemCount()-1;
            if(pos<0)
                Toast.makeText(getApplicationContext(),
                        "No Items",
                        Toast.LENGTH_SHORT)
                .show();
            else
            multiAdapter.remove(pos);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
