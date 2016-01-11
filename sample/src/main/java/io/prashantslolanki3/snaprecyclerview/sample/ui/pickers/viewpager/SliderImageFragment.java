package io.prashantslolanki3.snaprecyclerview.sample.ui.pickers.viewpager;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.ui.pickers.ImageFullScreenActivity;

public class SliderImageFragment extends Fragment {


    private static final String ARG_IMAGE_URL = "imageUrl";
    private static final String ARG_ON_CLICK_OPEN_FULLSCREEN = "onClickOpenFullScreenActivity";

    public SliderImageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param imageUrl Url of the Image to be loaded.
     * @return A new instance of fragment SliderImageFragment.
     */
    public static SliderImageFragment newInstance(String imageUrl, boolean onClickOpenFullScreenActivity) {
        SliderImageFragment fragment = new SliderImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE_URL, imageUrl);
        args.putBoolean(ARG_ON_CLICK_OPEN_FULLSCREEN, onClickOpenFullScreenActivity);
        fragment.setArguments(args);
        return fragment;
    }

    String imageUrls;
    boolean onClickOpenFullScreenActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageUrls = getArguments().getString(ARG_IMAGE_URL);
            onClickOpenFullScreenActivity = getArguments().getBoolean(ARG_ON_CLICK_OPEN_FULLSCREEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slider_image, container, false);
        final SubsamplingScaleImageView sliderIv = (SubsamplingScaleImageView) view.findViewById(R.id.slider_image);
        sliderIv.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
        Glide.with(getContext())
                .load(imageUrls)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        sliderIv.setImage(ImageSource.bitmap(resource));
                    }
                });

        if (onClickOpenFullScreenActivity)
            sliderIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(ImageFullScreenActivity.getIntent(getContext(), imageUrls));
                }
            });

        return view;
    }


}