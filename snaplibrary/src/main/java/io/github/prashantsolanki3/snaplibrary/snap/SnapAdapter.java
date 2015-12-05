package io.github.prashantsolanki3.snaplibrary.snap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * Created by Prashant Solanki.
 *
 * Github: prashantsolanki3
 * Email: prs.solanki@live.com
 * */
public class SnapAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    Context context;
    ArrayList<T> list;
    Class<T> modelClass;
    protected int itemLayout;
    Class<VH> viewHolderClass;
    private int lastPosition = -1;


    public SnapAdapter(Context context, Class<T> modelClass, int itemLayout, Class<VH> viewHolderClass) {
        this.context = context;
        this.modelClass = modelClass;
        this.itemLayout = itemLayout;
        this.viewHolderClass = viewHolderClass;
    }


    public Context getContext() {
        return context;
    }

    private void setAnimation(VH vh, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            animateItems(vh,position);
            lastPosition = position;
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) throws RuntimeException {

        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(this.itemLayout, parent, false);

        try {
            Constructor e = this.viewHolderClass.getConstructor(View.class);
            VH vh = (VH) e.newInstance(view);
            if(vh instanceof SnapViewHolder)
                ((SnapViewHolder)vh).setContext(context);
            return vh;
        } catch (Exception var5) {
            try {
                Constructor e = this.viewHolderClass.getConstructor(View.class,Context.class);
                return (VH) e.newInstance(view,context);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position){
        T t = list.get(position);
        this.populateViewHolderItem(holder,t,position);
        this.setAnimation(holder,position);
    }


    public T getItem(int pos){
        return list.get(pos);
    }

    public ArrayList<T> getAllItems(){
        return list;
    }

    public void removeItem(int pos){
        this.list.remove(pos);
        this.notifyItemRemoved(pos);
        this.notifyDataSetChanged();
    }

    public void removeItem(T item){
        int pos=this.list.indexOf(item);
        this.list.remove(item);
        this.notifyItemRemoved(pos);
        this.notifyDataSetChanged();
    }

    public void clearAdapter(){
        int size= this.list.size();
        this.list.clear();
        this.list = new ArrayList<>();
        this.notifyItemRangeRemoved(0, size - 1);
        this.notifyDataSetChanged();
    }

    public void addAndOverwriteAllItems(ArrayList<T> data) {
        this.list = new ArrayList<>(data);
        this.notifyItemRangeChanged(0, this.list.size() - 1);
        this.notifyDataSetChanged();
    }

    public void addItems(ArrayList<T> list){
        int prevSize = this.list.size()-1;
        this.list.addAll(list);
        this.notifyItemRangeChanged(prevSize, this.list.size() - 1);
        this.notifyDataSetChanged();
    }

    public void addItem(T cardBase){
        this.list.add(cardBase);
        this.notifyItemRangeChanged(list.size()-2, list.size() - 1);
        this.notifyDataSetChanged();
    }

    /**
     * Populate view of viewholder here.
     * */
    public void populateViewHolderItem(VH viewHolder, T item , int position ) {
        if (viewHolder instanceof SnapViewHolder) {
            ((SnapViewHolder) viewHolder).setItemData(item);
            ((SnapViewHolder) viewHolder).populateViewHolder(item, position);
            ((SnapViewHolder) viewHolder).attachOnClickListeners((SnapViewHolder)viewHolder, item, position);
        }
    }

    /**
     * Animate viewHolder.itemView in this method.
     * Position is not needed for simple animations.
     * Use position for complex animations.
     * */
    public void animateItems(VH viewHolder,int pos){
        if(viewHolder instanceof SnapViewHolder)
            ((SnapViewHolder)viewHolder).animateViewHolder((SnapViewHolder)viewHolder,pos);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

}