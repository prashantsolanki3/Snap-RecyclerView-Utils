package io.github.prashantsolanki3.snaplibrary.snap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Prashant on 12/5/2015.
 */
public abstract class SnapViewHolder<T> extends RecyclerView.ViewHolder {
    Context context;

    public SnapViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public abstract void setData(T data,int pos);

    public abstract void animateViewHolder(SnapViewHolder viewHolder, int pos);

}
