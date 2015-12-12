package io.prashantslolanki3.snaprecyclerview.sample.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.github.prashantsolanki3.snaplibrary.snap.SnapViewHolder;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.model.HorizontalRecyclerModel;
import io.prashantslolanki3.snaprecyclerview.sample.model.TextModel;

/**
 * Created by Prashant on 12/12/2015.
 */
public class TextViewHolder extends SnapViewHolder<TextModel> {

    TextView textView;

    public TextViewHolder(View itemView, Context context) {
        super(itemView, context);
        textView = (TextView) itemView.findViewById(R.id.tv);
    }

    @Override
    public void attachOnClickListeners(SnapViewHolder viewHolder, TextModel item, int pos) {

    }

    @Override
    public void animateViewHolder(SnapViewHolder viewHolder, int pos) {

    }

    @Override
    public void populateViewHolder(TextModel data, int pos) {
        textView.setText(null);
        textView.setText(" Vh: "+this.getClass().getSimpleName()+" Model: "+data.getClass().getSimpleName());
    }

}
