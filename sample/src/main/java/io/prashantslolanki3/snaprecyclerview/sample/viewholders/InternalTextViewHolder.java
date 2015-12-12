package io.prashantslolanki3.snaprecyclerview.sample.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import io.github.prashantsolanki3.snaplibrary.snap.SnapViewHolder;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.model.HorizontalRecyclerModel;

/**
 * Created by Prashant on 12/12/2015.
 */
public class InternalTextViewHolder extends SnapViewHolder<HorizontalRecyclerModel> {

    TextView textView;

    public InternalTextViewHolder(View itemView, Context context) {
        super(itemView, context);
        textView = (TextView) itemView.findViewById(R.id.tv);
    }

    @Override
    public void attachOnClickListeners(SnapViewHolder viewHolder, HorizontalRecyclerModel item, int pos) {

    }

    @Override
    public void animateViewHolder(SnapViewHolder viewHolder, int pos) {

    }

    @Override
    public void populateViewHolder(HorizontalRecyclerModel data, int pos) {
        textView.setText(null);
        textView.setText(" Vh: "+this.getClass().getSimpleName()+" Model: "+data.getClass().getSimpleName());
    }

}
