# Snap RecyclerView Utils

Simple Library to create RecyclerView Adapters and Viewholder in a snap.

Send any data to any Viewholder without creating custom RecyclerView Adapters.

## Add to your project

Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
Add the dependency

```
	dependencies {
	        compile 'com.github.prashantsolanki3:Snap-RecyclerView-Utils:v1.1'
	}
```

Simple Usage using ViewHolder which extends  SnapViewHolder.

```
SnapAdapter<SimpleProduct,ViewHolderProduct>  adapterRecycler = new SnapAdapter<>(getContext(),
                SimpleProduct.class, //Model Class containing data
                R.layout.item_recycler_product, // Item Layout
                ViewHolderProduct.class); // ViewHolder
                
recyclerView.setAdapter(adapterRecycler);

//Add items to RecyclerView
adapterRecycler.addItems(new ArrayList<SimpleProduct>());

```

ViewHolderProduct class which extends SnapViewHolder
* Overrides 2 Methods: setData and animateViewHolder.

```

public class ViewHolderProduct extends SnapViewHolder<SimpleProduct> {

    TextView title;
    ImageView thumbnail;
    SimpleProduct simpleProduct;

    public VhProductList(View itemView, Context context) {
        super(itemView, context);
        initViews();
    }

    private void initViews(){
        title = (TextView) itemView.findViewById(R.id.product_title);
        thumbnail= (ImageView) itemView.findViewById(R.id.product_image);
    }


    @Override
    public void setData(SimpleProduct data, int pos) {
        this.simpleProduct=data;
        
        thumbnail.setImageDrawable(null);
        
        title.setText(data.getTitle());

        Glide.with(getContext())
                .load(data.getListImage())
                .into(thumbnail);

    }

    @Override
    public void animateViewHolder(SnapViewHolder viewHolder, int position) {
        //Apply Animations to ViewHolder.
        
        AnimationSet set = new AnimationSet(true);

        if(position%3==0)
            set.addAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.gp_slide_in_left));
        else if(position%3==1)
            set.addAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.gp_slide_in_right));

        set.addAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.gp_slide_in_bottom));

        viewHolder.itemView.startAnimation(set);
    }

}

```

# If your Viewholder doesn't extends SnapViewHolder.

* Override populateViewHolderItem method in SnapAdapter to setData.
* Optionally Override animateItems method in SnapAdaper to animate Items.
