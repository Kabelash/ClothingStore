package com.kabelash.clothingstore.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kabelash.clothingstore.R
import com.kabelash.clothingstore.model.CartItem
import kotlinx.android.synthetic.main.cart_list_item.view.*

class ShoppingCartAdapter(var context: Context, var cartItems: List<CartItem>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        val layout = LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false)

        return ViewHolder(
            layout
        )
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bindItem(cartItems[position])
    }


    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        fun bindItem(cartItem: CartItem) {

            itemView.product_name.text = cartItem.product.name

            itemView.product_price.text = "£${cartItem.product.price}"

            itemView.product_quantity.text = cartItem.quantity.toString()

        }


    }

}