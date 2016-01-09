package io.prashantslolanki3.snaprecyclerview.sample.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import io.github.prashantsolanki3.snaplibrary.snap.SnapViewHolder;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.model.HomeItem;

/**
 * Created by Prashant on 1/9/2016.
 */
public class HomeVH extends SnapViewHolder<HomeItem> {

    TextView title;

    public HomeVH(View itemView, Context context) {
        super(itemView, context);
        title = (TextView) itemView.findViewById(R.id.item_title);
    }

    @Override
    public void populateViewHolder(HomeItem data, int pos) {
        title.setText(data.getTitle());
    }

    @Override
    public void animateViewHolder(SnapViewHolder viewHolder, int pos) {

    }

    @Override
    public void attachOnClickListeners(SnapViewHolder viewHolder, final HomeItem item, int pos) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.startActivity(getContext());
            }
        });
    }
}
