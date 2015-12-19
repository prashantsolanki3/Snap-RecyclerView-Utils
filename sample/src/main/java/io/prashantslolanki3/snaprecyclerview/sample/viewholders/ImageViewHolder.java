package io.prashantslolanki3.snaprecyclerview.sample.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import io.github.prashantsolanki3.snaplibrary.snap.SnapViewHolder;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;

/**
 * Package io.prashantslolanki3.snaprecyclerview.sample.viewholders
 * <p/>
 * Created by Prashant on 12/18/2015.
 * <p/>
 * Email: solankisrp2@gmail.com
 * Github: @prashantsolanki3
 */
public class ImageViewHolder extends SnapViewHolder<String> {

    final ImageView imageView;

    public ImageViewHolder(View itemView, Context context) {
        super(itemView, context);
        imageView = (ImageView) itemView.findViewById(R.id.item_iv);
    }

    @Override
    public void populateViewHolder(String data, int pos) {
        imageView.setImageDrawable(null);
        Glide.with(getContext())
                .load(data)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    @Override
    public void animateViewHolder(SnapViewHolder viewHolder, int pos) {

    }

    @Override
    public void attachOnClickListeners(SnapViewHolder viewHolder, String item, int pos) {

    }
}
