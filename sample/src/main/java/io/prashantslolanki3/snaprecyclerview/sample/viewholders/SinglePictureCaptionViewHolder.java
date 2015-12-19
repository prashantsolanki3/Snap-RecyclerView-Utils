package io.prashantslolanki3.snaprecyclerview.sample.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import io.github.prashantsolanki3.snaplibrary.snap.SnapViewHolder;
import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.model.PictureCaption;

/**
 * Package io.prashantslolanki3.snaprecyclerview.sample.viewholders
 * <p/>
 * Created by Prashant on 12/18/2015.
 * <p/>
 * Email: solankisrp2@gmail.com
 * Github: @prashantsolanki3
 */
public class SinglePictureCaptionViewHolder extends SnapViewHolder<PictureCaption> {

    final TextView title;
    final ImageView imageView;

    public SinglePictureCaptionViewHolder(View itemView, Context context) {
        super(itemView, context);
        title = (TextView) itemView.findViewById(R.id.item_title);
        imageView = (ImageView) itemView.findViewById(R.id.item_iv);
    }

    @Override
    public void populateViewHolder(PictureCaption data, int pos) {
        imageView.setImageDrawable(null);
        Glide.with(getContext())
                .load(data.getUrl())
                .asBitmap()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);

        title.setText(data.getCaption());
    }

    @Override
    public void animateViewHolder(SnapViewHolder viewHolder, int pos) {

    }

    @Override
    public void attachOnClickListeners(SnapViewHolder viewHolder, PictureCaption item, int pos) {

    }
}
