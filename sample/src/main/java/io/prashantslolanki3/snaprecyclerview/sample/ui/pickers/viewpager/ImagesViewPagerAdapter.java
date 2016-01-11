package io.prashantslolanki3.snaprecyclerview.sample.ui.pickers.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ImagesViewPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<String> items;
    String placeHolder;

    public ImagesViewPagerAdapter(FragmentManager fm, String s) {
        super(fm);
        items = new ArrayList<>();
        placeHolder = s;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {

        if (getCount() == 1 && items.size() == 0)
            return SliderImageFragment.newInstance(placeHolder, false);

        return SliderImageFragment.newInstance(items.get(position), true);
    }

    public void add(String s) {
        items.add(0, s);
        notifyDataSetChanged();
    }

    public void remove(String s) {
        items.remove(s);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return items.size() == 0 ? 1 : items.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}