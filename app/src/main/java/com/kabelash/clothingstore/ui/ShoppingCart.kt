package com.kabelash.clothingstore.ui

import android.content.Context
import com.kabelash.clothingstore.model.CartItem
import com.kabelash.clothingstore.model.FavoriteItem
import io.paperdb.Paper

class ShoppingCart {

    companion object {

        //Adding item from cart
        fun addItem(cartItem: CartItem) {
            val cart =
                getCart()

            val targetItem = cart.singleOrNull { it.product.productId == cartItem.product.productId }

            if (targetItem == null) {
                cartItem.quantity++
                cart.add(cartItem)
            } else {

                targetItem.quantity++
            }
            saveCart(cart)

        }

        //Removing item from cart
        fun removeItem(cartItem: CartItem, context: Context) {

            val cart =
                getCart()


            val targetItem = cart.singleOrNull { it.product.productId == cartItem.product.productId }

            if (targetItem != null) {

                if (targetItem.quantity > 0) {
                    //Toast.makeText(context, "great quantity", Toast.LENGTH_SHORT).show()
                    targetItem.quantity--
                } else {
                    cart.remove(targetItem)
                }

            }

            saveCart(cart)

        }

        //Adding favourite
        fun addFavourite(favItem: FavoriteItem) {
            val favourite =
                getFav()

            val targetItem = favourite.singleOrNull { it.productFav.productId == favItem.productFav.productId }

            if (targetItem == null) {
                //itemView.imageFavourite.setImageResource(R.drawable.ic_favourite_hover)
                favItem.quantityFav++
                favourite.add(favItem)
            } else {

                targetItem.quantityFav--
            }
            saveFav(
                favourite
            )

        }

        //Saving Cart
        private fun saveCart(cart: MutableList<CartItem>) {
            Paper.book().write("cart", cart)
        }

        //Saving Favorite
        private fun saveFav(fav: MutableList<FavoriteItem>) {
            Paper.book().write("fav", fav)
        }

        fun getCart(): MutableList<CartItem> {
            return Paper.book().read("cart", mutableListOf())
        }

        fun getFav(): MutableList<FavoriteItem> {
            return Paper.book().read("fav", mutableListOf())
        }

        fun getShoppingCartSize(): Int {

            var cartSize = 0
            getCart().forEach {
                cartSize += it.quantity;
            }

            return cartSize
        }
    }

}