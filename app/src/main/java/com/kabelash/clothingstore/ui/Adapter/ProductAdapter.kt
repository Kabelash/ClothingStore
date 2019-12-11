package com.kabelash.clothingstore.ui.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.kabelash.clothingstore.R
import com.kabelash.clothingstore.ui.ShoppingCart
import com.kabelash.clothingstore.model.CartItem
import com.kabelash.clothingstore.model.Clothing
import com.kabelash.clothingstore.model.FavoriteItem
import com.kabelash.clothingstore.ui.MainActivity
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.product_row_item.view.*

class ProductAdapter(var context: Context, var products: List<Clothing> = arrayListOf()) :
    androidx.recyclerview.widget.RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.product_row_item, parent, false)
        return ViewHolder(
            view
        )

    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bindProduct(products[position])


//        (context as MainActivity).coordinator

    }

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        @SuppressLint("CheckResult")
        fun bindProduct(product: Clothing) {

            itemView.product_name.text = product.name
            itemView.product_price.text = "£${product.price.toString()}"

            //Picasso.get().load(product.photos[0].filename).fit().into(itemView.product_image)


            //                val products = mutableListOf<Product>()
//                products.add(product)
//

//                ShoppingCart.addItem(item)


            Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {

                //Add item to shopping cart
                itemView.addToCart.setOnClickListener { view ->
                    val item = CartItem(product)

                    ShoppingCart.addItem(
                        item
                    )
                    Snackbar.make(
                        (itemView.context as MainActivity).coordinator,
                        "${product.name} added to your cart",
                        Snackbar.LENGTH_LONG
                    ).show()

                    it.onNext(ShoppingCart.getCart())

                }

                //Remove item from shopping cart
                itemView.removeItem.setOnClickListener { view ->

                    val item = CartItem(product)

                    ShoppingCart.removeItem(
                        item,
                        itemView.context
                    )
                    Snackbar.make(
                        (itemView.context as MainActivity).coordinator,
                        "${product.name} removed from your cart",
                        Snackbar.LENGTH_LONG
                    ).show()
                    it.onNext(ShoppingCart.getCart())

                }

                //Add to favourites
                itemView.imageFavourite.setOnClickListener { view ->

                    val item = FavoriteItem(product)

                    ShoppingCart.addFavourite(
                        item
                    )

                    if(item.quantityFav.equals(0)){
                        itemView.imageFavourite.setImageResource(R.drawable.ic_favourite_hover)
                        Snackbar.make(
                            (itemView.context as MainActivity).coordinator,
                            "${product.name} added to favourites",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }else if (item.quantityFav.equals(1)){
                        itemView.imageFavourite.setImageResource(R.drawable.ic_favourite_1)
                    }

                }


            }).subscribe { cart ->

                var quantity = 0

                cart.forEach { cartItem ->

                    quantity += cartItem.quantity
                }

                (itemView.context as MainActivity).cart_size.text = quantity.toString()
                Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()

            }
//                Toast.makeText(itemView.context, "${product.name} added to your cart", Toast.LENGTH_SHORT).show()


        }

    }

}