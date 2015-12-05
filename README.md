# Snap RecyclerView Utils

Simple Library to create RecyclerView Adapters and Viewholder in a snap.

Send any data to any Viewholder without creating custom RecyclerView Adapters.

Simple Usage using ViewHolder which extends  SnapViewHolder.

```
SnapAdapter<SimpleProduct,ViewHolderProduct>  adapterRecycler = new SnapAdapter<>(getContext(),
                SimpleProduct.class, //Model Class containing data
                R.layout.item_recycler_product, // Item Layout
                ViewHolderProduct.class); // ViewHolder
                
recyclerView.setAdapter(adapterRecycler);

adapterRecycler.addItems(new ArrayList<SimpleProduct>());

```

