package io.prashantslolanki3.snaprecyclerview.sample.ui.pickers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import io.github.prashantsolanki3.snaprecyclerviewutils.R;
import io.prashantslolanki3.snaprecyclerview.sample.ui.pickers.viewpager.SliderImageFragment;

public class ImageFullScreenActivity extends AppCompatActivity {

    public static final String ARG_IMAGE_URL = "imageUrl";
    public String imageUrl = null;

    public static Intent getIntent(Context context, String imageUrl) {
        Intent intent = new Intent(context, ImageFullScreenActivity.class);
        intent.putExtra(ARG_IMAGE_URL, imageUrl);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_layout);
        if (getIntent() != null) {
            imageUrl = getIntent().getStringExtra(ARG_IMAGE_URL);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,
                            SliderImageFragment.newInstance(imageUrl, false))
                    .commit();
        } else
            Toast.makeText(this, "Image URL not received", Toast.LENGTH_LONG).show();
    }
}
