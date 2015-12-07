package io.github.prashantsolanki3.snaplibrary.snap.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Prashant on 12/6/2015.
 */
public class SnapRecyclerView extends LinearLayout {

    Context context;
    RecyclerView recyclerView;

    public SnapRecyclerView(Context context) {
        super(context);
        this.context = context;
    }

    public SnapRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SnapRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    

}
