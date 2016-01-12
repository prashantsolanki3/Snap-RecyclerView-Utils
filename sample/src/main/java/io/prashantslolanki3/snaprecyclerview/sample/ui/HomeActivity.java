package io.prashantslolanki3.snaprecyclerview.sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import java.util.List;

import io.github.prashantsolanki3.snaplibrary.snap.adapter.AbstractSnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.adapter.SnapAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.layout.viewholder.SnapViewHolder;
import io.github.prashantsolanki3.snaplibrary.snap.layout.wrapper.SnapLayoutWrapper;
import io.github.prashantsolanki3.snaplibrary.snap.listeners.touch.SnapOnItemClickListener;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.model.HomeItem;
import io.prashantslolanki3.snaprecyclerview.sample.ui.pickers.SnapImagePickerActivity;
import io.prashantslolanki3.snaprecyclerview.sample.viewholders.HomeVH;

/**
 * Created by Prashant on 1/9/2016.
 */
public class HomeActivity extends BaseRecyclerViewActivity {

    private final int REQUEST_CODE_GALLERY = 5;

    @Override
    public void init(Bundle savedInstanceState) {
        getFab().setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_share_alt).colorRes(android.R.color.white));
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
        adapter.add(new HomeItem("Gallery", SnapImagePickerActivity.class));

        adapter.setOnItemClickListener(new SnapOnItemClickListener() {
            @Override
            public void onItemClick(SnapViewHolder viewHolder, View view, int position) {
                switch (position) {
                    case 2:
                        Intent intent = new SnapImagePickerActivity.Builder(getApplicationContext())
                                .setSelectionType(AbstractSnapSelectableAdapter.SelectionType.MULTIPLE_ON_LONG_PRESS, 5)
                                .setTitle("Snap Image Picker")
                                .setHeaderPlaceHolderImage("http://www.gettyimages.ca/gi-resources/images/Homepage/Category-Creative/UK/UK_Creative_462809583.jpg")
                                .getIntent();

                        startActivityForResult(intent, REQUEST_CODE_GALLERY);
                        break;

                    default:
                        startActivity(new Intent(getApplicationContext(), ((HomeVH) viewHolder).getItemData().getActivity()));
                }
            }

            @Override
            public void onItemLongPress(SnapViewHolder viewHolder, View view, int position) {

            }
        });
    }

    @Override
    public void setFabOnClickAction(FloatingActionButton view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY) {
            List<String> galleryItems = SnapImagePickerActivity.getResult(resultCode, data);
            if (galleryItems != null)
                Toast.makeText(this, galleryItems.size() + " Images Received", Toast.LENGTH_SHORT).show();
        }
    }
}
