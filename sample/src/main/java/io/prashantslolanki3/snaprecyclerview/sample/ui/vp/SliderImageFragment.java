package io.prashantslolanki3.snaprecyclerview.sample.ui.vp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import io.github.prashantsolanki3.snaprecyclerviewutils.R;

public class SliderImageFragment extends Fragment {


    private static final String ARG_IMAGE_URL = "com.prashant.showcaseviewpager.SliderImageAdapter.imageUrl";

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
    // TODO: Rename and change types and number of parameters
    public static SliderImageFragment newInstance(String imageUrl) {
        SliderImageFragment fragment = new SliderImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE_URL, imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    String imageUrls;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageUrls = getArguments().getString(ARG_IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slider_image, container, false);
        final ImageView sliderIv = (ImageView) view.findViewById(R.id.slider_image);
        sliderIv.setImageDrawable(null);
        Glide.with(getContext())
                .load(imageUrls)
                .asBitmap()
                .into(sliderIv);
        return view;
    }


}