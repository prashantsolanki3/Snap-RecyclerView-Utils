package io.github.prashantsolanki3.snaplibrary.snap.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import io.github.prashantsolanki3.snaplibrary.snap.layout.viewholder.SnapViewHolder;

public class SnapGestureDetector extends GestureDetector {

    View view;
    int position;
    SnapViewHolder viewHolder;

    public SnapGestureDetector(Context context, OnGestureListener listener) {
        super(context, listener);
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public SnapViewHolder getViewHolder() {
        return viewHolder;
    }

    public void setViewHolder(SnapViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    public static abstract class SnapGestureListener extends SnapGestureDetector.SimpleOnGestureListener {

        SnapGestureDetector detector;

        public void setDetector(SnapGestureDetector detector) {
            this.detector = detector;
        }

        public abstract void onLongPress(MotionEvent e, SnapViewHolder viewHolder, View view, int position);

        public abstract boolean onSingleTapUp(MotionEvent e, SnapViewHolder viewHolder, View view, int position);

        @Override
        public void onLongPress(MotionEvent e) {
            onLongPress(e, detector.getViewHolder(), detector.getView(), detector.getPosition());
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return onSingleTapUp(e, detector.getViewHolder(), detector.getView(), detector.getPosition());
        }
    }
}
