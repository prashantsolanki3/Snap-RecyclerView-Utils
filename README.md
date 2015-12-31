# Snap RecyclerView Utils

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Snap--RecyclerView--Utils-brightgreen.svg?style=flat-square)](https://android-arsenal.com/details/1/2884)
[![Travis CI](https://travis-ci.org/prashantsolanki3/Snap-RecyclerView-Utils.svg)](https://travis-ci.org/prashantsolanki3/Snap-RecyclerView-Utils)

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
    compile 'com.github.prashantsolanki3:Snap-RecyclerView-Utils:v1.6'
}
```

---

## Usage

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

***

##Contribute

Contribute by creating issues (tagged enhancement, bugs) in the repo or create a pull request.

##Using Snap RecyclerView Utils? 

If you are using Snap RecyclerView Utils in your app and would like to be listed here, please let us know opening a new issue!

###License

Copyright 2015 Prashant Solanki

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
