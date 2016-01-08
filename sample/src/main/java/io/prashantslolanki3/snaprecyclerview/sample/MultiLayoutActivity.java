package io.prashantslolanki3.snaprecyclerview.sample;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import io.github.prashantsolanki3.snaplibrary.snap.AbstractSnapMultiAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.SnapAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.SnapLayoutWrapper;
import io.github.prashantsolanki3.snaplibrary.snap.SnapViewHolder;
import io.github.prashantsolanki3.snaplibrary.snap.endless.EndlessLoader;
import io.github.prashantsolanki3.snaplibrary.snap.recycler.SnapOnItemClickListener;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.model.HorizontalRecyclerModel;
import io.prashantslolanki3.snaprecyclerview.sample.model.PictureCaption;
import io.prashantslolanki3.snaprecyclerview.sample.viewholders.HorizontalRecyclerViewHolder;
import io.prashantslolanki3.snaprecyclerview.sample.viewholders.ImageViewHolder;
import io.prashantslolanki3.snaprecyclerview.sample.viewholders.SinglePictureCaptionViewHolder;

/**
 * Package io.prashantslolanki3.snaprecyclerview.sample
 * <p/>
 * Created by Prashant on 12/18/2015.
 * <p/>
 * Email: solankisrp2@gmail.com
 * Github: @prashantsolanki3
 */
public class MultiLayoutActivity extends BaseRecyclerViewActivity {

    SnapAdapter adapter;

    @Override
    public void setLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void setAdapter(final RecyclerView recyclerView) {
        ArrayList<SnapLayoutWrapper> wrappers = new ArrayList<>();
        wrappers.add(new SnapLayoutWrapper(HorizontalRecyclerModel.class, HorizontalRecyclerViewHolder.class, R.layout.item_header_layout, 0));
        wrappers.add(new SnapLayoutWrapper(String.class, ImageViewHolder.class, R.layout.item_image_layout, 1));
        wrappers.add(new SnapLayoutWrapper(PictureCaption.class, SinglePictureCaptionViewHolder.class, R.layout.item_pictue_caption_layout, 2));
        adapter = new SnapAdapter(this, wrappers, recyclerView);

        adapter.setAutoEmptyLayoutHandling(true);
        adapter.setAlternateViewContainer((FrameLayout) findViewById(R.id.alternateViewContainer));

        View empty = adapter.getViewFromId(R.layout.alter_empty);
        TextView textView = (TextView) empty.findViewById(R.id.alter_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        setFabOnClickAction(v);
                    }
                };
                adapter.showAlternateLayout(adapter.getViewFromId(R.layout.alter_loading));
                new Handler().postDelayed(runnable, 2500);
            }
        });

        adapter.setEmptyView(empty);
        adapter.hideAlternateLayout();
        recyclerView.setAdapter(adapter);
        adapter.setEndlessLoader(5, new EndlessLoader<Object>() {
            @Override
            public void loadMore(AbstractSnapMultiAdapter<Object> adapter, int pageNo) {
                for (int i = 0; i < 15; i++) {
                    setFabOnClickAction(recyclerView);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });

        adapter.setOnItemClickListener(new SnapOnItemClickListener() {

            @Override
            public void onItemClick(SnapViewHolder viewHolder, View view, int position) {
                Toast.makeText(getApplicationContext(), "Pos: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongPress(SnapViewHolder viewHolder, View view, int position) {
                Toast.makeText(getApplicationContext(), "Long: " + position, Toast.LENGTH_SHORT).show();
            }

        });
    }

    int counter = 0;

    @Override
    public void setFabOnClickAction(View view) {
        Random random = new Random();
        if (counter % 3 == 0)
            adapter.add(SampleData.getHorizontalRecyclerModels(1, random.nextInt(5)).get(0));
        else if (counter % 4 == 0)
            adapter.add(SampleData.getImageUrls(1).get(0));
        else
            adapter.add(SampleData.getPictureCaption());
        counter++;

        if (adapter.isAlternateLayoutShowing())
            adapter.hideAlternateLayout();

    }

    public boolean setOnRemoveClickAction() {
        if (adapter.getItemCount() > 0)
            adapter.remove(adapter.getItemCount() - 1);
        else
            adapter.showEmptyLayout();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_remove)
            return setOnRemoveClickAction();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
