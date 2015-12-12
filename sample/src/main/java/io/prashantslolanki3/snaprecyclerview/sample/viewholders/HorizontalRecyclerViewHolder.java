package io.prashantslolanki3.snaprecyclerview.sample.viewholders;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.github.prashantsolanki3.snaplibrary.snap.SnapAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.SnapViewHolder;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.model.HorizontalRecyclerModel;

/**
 *
 * Created by Prashant on 12/12/2015.
 *
 */
public class HorizontalRecyclerViewHolder extends SnapViewHolder<HorizontalRecyclerModel> {

    RecyclerView recyclerView;
    SnapAdapter<HorizontalRecyclerModel,InternalTextViewHolder> adapter;

    public HorizontalRecyclerViewHolder(View itemView, Context context) {
        super(itemView, context);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.header_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
        adapter = new SnapAdapter<>(getContext(), R.layout.item_text_layout,InternalTextViewHolder.class);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void attachOnClickListeners(SnapViewHolder viewHolder, HorizontalRecyclerModel item, int pos) {

    }

    @Override
    public void animateViewHolder(SnapViewHolder viewHolder, int pos) {

    }

    @Override
    public void populateViewHolder(HorizontalRecyclerModel data, int pos) {
        for (int i = 0; i <= pos ; i++) {
            adapter.add(data);
        }
    }
}
