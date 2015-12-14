# Snap RecyclerView Utils

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Snap--RecyclerView--Utils-green.svg?style=true)](https://android-arsenal.com/details/1/2884)

Simple Library to create RecyclerView Adapters and Viewholder in a snap.

## Features

* Simple RecyclerView Adapter
* Multiple Layouts RecyclerView Adapter
* Endless Loader

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
    compile 'com.github.prashantsolanki3:Snap-RecyclerView-Utils:v1.5'
}
```

---

# Usage

###[Check out the wiki!](https://github.com/prashantsolanki3/Snap-RecyclerView-Utils/wiki)

Make a `ViewHolder` which extends `SnapViewHolder`.

```Java
SnapAdapter<SimpleProduct, ViewHolderProduct> adapterRecycler = new SnapAdapter<>(
	getContext(), //Context
	SimpleProduct.class, //Model class, matching generic type
        R.layout.item_recycler_product, // Item Layout
        ViewHolderProduct.class); // ViewHolder class, matching generic type
                
recyclerView.setAdapter(adapterRecycler);

//Add items to RecyclerView
adapterRecycler.addAll(new ArrayList<SimpleProduct>());
```
### Yeah That's All!
