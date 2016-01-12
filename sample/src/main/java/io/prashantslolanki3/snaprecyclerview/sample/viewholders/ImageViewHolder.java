package io.prashantslolanki3.snaprecyclerview.sample.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import io.github.prashantsolanki3.snaplibrary.snap.SnapViewHolder;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.AbstractSnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableViewHolder;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;

/**
 * Package io.prashantslolanki3.snaprecyclerview.sample.viewholders
 * <p/>
 * Created by Prashant on 12/18/2015.
 * <p/>
 * Email: solankisrp2@gmail.com
 * Github: @prashantsolanki3
 */
public class ImageViewHolder extends SnapSelectableViewHolder<String> {

    final ImageView imageView, tick;

    public ImageViewHolder(View itemView, Context context) {
        super(itemView, context);
        imageView = (ImageView) itemView.findViewById(R.id.item_iv);
        tick = (ImageView) itemView.findViewById(R.id.item_tick);
    }

    public ImageViewHolder(View itemView, Context context, AbstractSnapSelectableAdapter adapter) {
        super(itemView, context, adapter);
        imageView = (ImageView) itemView.findViewById(R.id.item_iv);
        tick = (ImageView) itemView.findViewById(R.id.item_tick);
    }

    @Override
    public void populateViewHolder(String data, int pos) {
        imageView.setImageDrawable(null);
        Glide.with(getContext())
                .load(data)
                .into(imageView);
    }

    @Override
    public void animateViewHolder(SnapViewHolder viewHolder, int pos) {

    }

    @Override
    public void attachOnClickListeners(SnapViewHolder viewHolder, final String item, final int pos) {
        //ViewHolder based selection implementation.
        /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Utiloid.BASIC_UTILS.isNull(getAdapter())&&getAdapter().isSelectionEnabled()) {
                    getAdapter().toggleSelection(pos);
                }
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!Utiloid.BASIC_UTILS.isNull(getAdapter())&&!getAdapter().isSelectionEnabled()) {
                    getAdapter().setSelectionEnabled(true);
                    getAdapter().toggleSelection(pos);
                }
                return true;
            }
        });*/
    }

    @Override
    public void onSelectionEnabled(SnapSelectableViewHolder viewHolder, String item, int pos) {
        tick.setVisibility(View.VISIBLE);
        tick.setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_check_circle_o).colorRes(android.R.color.white).sizeDp(36));
    }

    @Override
    public void onSelectionDisabled(SnapSelectableViewHolder viewHolder, String item, int pos) {
        tick.setVisibility(View.GONE);
    }

    @Override
    public void onSelected(SnapSelectableViewHolder viewHolder, String item, int pos) {
        tick.setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_check_circle).colorRes(android.R.color.white).sizeDp(36));
    }

    @Override
    public void onDeselected(SnapSelectableViewHolder viewHolder, String item, int pos) {
        tick.setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_check_circle_o).colorRes(android.R.color.white).sizeDp(36));
    }
}
