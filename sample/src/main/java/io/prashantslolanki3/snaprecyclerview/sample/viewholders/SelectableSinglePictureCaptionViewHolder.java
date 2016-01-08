package io.prashantslolanki3.snaprecyclerview.sample.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import io.github.prashantsolanki3.snaplibrary.snap.SnapViewHolder;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.AbstractSnapSelectableAdapter;
import io.github.prashantsolanki3.snaplibrary.snap.selectable.SnapSelectableViewHolder;
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
public class SelectableSinglePictureCaptionViewHolder extends SnapSelectableViewHolder<PictureCaption> {

    final TextView title;
    final ImageView imageView;
    final ImageView selectionOverlay;


    public SelectableSinglePictureCaptionViewHolder(View itemView, Context context, AbstractSnapSelectableAdapter adapter) {
        super(itemView, context, adapter);
        title = (TextView) itemView.findViewById(R.id.item_title);
        imageView = (ImageView) itemView.findViewById(R.id.item_iv);
        selectionOverlay = (ImageView) itemView.findViewById(R.id.selection_overlay);
        selectionOverlay.setVisibility(View.GONE);
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
    public void onSelectionEnabled(SnapSelectableViewHolder viewHolder, PictureCaption item, int pos) {
        selectionOverlay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSelected(SnapSelectableViewHolder viewHolder, PictureCaption item, int pos) {
        Glide.with(getContext())
                .load(android.R.drawable.ic_menu_send)
                .into(selectionOverlay);
    }

    @Override
    public void onDeselected(SnapSelectableViewHolder viewHolder, PictureCaption item, int pos) {
        selectionOverlay.setImageDrawable(null);
    }

    @Override
    public void attachOnClickListeners(SnapViewHolder viewHolder, PictureCaption item, final int pos) {
        /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdapter().toggleSelection(getItemData(),pos);
            }
        });*/
    }

    @Override
    public void onSelectionDisabled(SnapSelectableViewHolder viewHolder, PictureCaption item, int pos) {
        selectionOverlay.setVisibility(View.GONE);
    }
}
