# Snap RecyclerView Utils

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Snap--RecyclerView--Utils-green.svg?style=true)](https://android-arsenal.com/details/1/2884)

Simple Library to create RecyclerView Adapters and Viewholder in a snap.

Send any data to any Viewholder without creating custom RecyclerView Adapters.

## Add to your project

[![Release](https://img.shields.io/github/release/prashantsolanki3/Snap-RecyclerView-Utils.svg?label=jitpack)](https://jitpack.io/#prashantsolanki3/Snap-RecyclerView-Utils)

Add JitPack to repositories in your project's root `build.gradle` file:

```Gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Add the dependency to your module's `build.gradle` file:

```Gradle
dependencies {
	...
    compile 'com.github.prashantsolanki3:Snap-RecyclerView-Utils:v1.1'
}
```

---

# Usage

Simple usage using `ViewHolder` which extends `SnapViewHolder`.

```Java
SnapAdapter<SimpleProduct, ViewHolderProduct> adapterRecycler = new SnapAdapter<>(
	getContext(),
        R.layout.item_recycler_product, // Item Layout
        ViewHolderProduct.class); // ViewHolder class, matching generic type
                
recyclerView.setAdapter(adapterRecycler);

//Add items to RecyclerView
adapterRecycler.addItems(new ArrayList<SimpleProduct>());
```

`ViewHolderProduct` class which extends `SnapViewHolder`
* Overrides 2 methods: `setData()` and `animateViewHolder()`.

```java
public class ViewHolderProduct extends SnapViewHolder<SimpleProduct> {

    final TextView title;
    final ImageView thumbnail;
    final SimpleProduct simpleProduct;

    public VhProductList(View itemView, Context context) {
        super(itemView, context);
        initViews();
    }

    private void initViews() {
        title = (TextView) itemView.findViewById(R.id.product_title);
        thumbnail= (ImageView) itemView.findViewById(R.id.product_image);
    }

    @Override
    public void setData(SimpleProduct data, int pos) {
        this.simpleProduct = data;
        
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

        if (position % 3 == 0)
            set.addAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.gp_slide_in_left));
        else if (position % 3 == 1)
            set.addAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.gp_slide_in_right));

        set.addAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.gp_slide_in_bottom));

        viewHolder.itemView.startAnimation(set);
    }
}
```

### If your `Viewholder` doesn't extend `SnapViewHolder`.

* Override `populateViewHolderItem()` method in `SnapAdapter` to `setData()`.
* Optionally override `animateItems()` method in `SnapAdaper` to animate Items.
