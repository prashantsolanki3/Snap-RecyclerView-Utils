package io.prashantslolanki3.snaprecyclerview.sample.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import io.github.prashantsolanki3.snaplibrary.snap.SnapViewHolder;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.AbstractSnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableViewHolder;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.model.GalleryItem;

/**
 * Package io.prashantslolanki3.snaprecyclerview.sample.viewholders
 * <p/>
 * Created by Prashant on 12/18/2015.
 * <p/>
 * Email: solankisrp2@gmail.com
 * Github: @prashantsolanki3
 */
public class ImageViewHolder extends SnapSelectableViewHolder<GalleryItem> {

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
    public void populateViewHolder(GalleryItem data, int pos) {
        imageView.setImageDrawable(null);
        Glide.with(getContext())
                .load(data.getImageUri())
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    @Override
    public void animateViewHolder(SnapViewHolder viewHolder, int pos) {

    }

    @Override
    public void attachOnClickListeners(SnapViewHolder viewHolder, GalleryItem item, int pos) {

    }

    @Override
    public void onSelectionEnabled(SnapSelectableViewHolder viewHolder, GalleryItem item, int pos) {
        tick.setVisibility(View.VISIBLE);
        tick.setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_check_circle_o).color(android.R.color.white));
    }

    @Override
    public void onSelectionDisabled(SnapSelectableViewHolder viewHolder, GalleryItem item, int pos) {
        tick.setVisibility(View.GONE);
    }

    @Override
    public void onSelected(SnapSelectableViewHolder viewHolder, GalleryItem item, int pos) {
        tick.setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_check_circle).color(android.R.color.white));
    }

    @Override
    public void onDeselected(SnapSelectableViewHolder viewHolder, GalleryItem item, int pos) {
        tick.setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_check_circle_o).color(android.R.color.white));
    }
}
