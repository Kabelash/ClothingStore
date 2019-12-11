package com.kabelash.clothingstore.model

import com.google.gson.annotations.SerializedName

data class Clothing(

    @SerializedName("productId")
    val productId: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("category")
    val category: String? = null,

    @SerializedName("price")
    val price: String? = null,

    @SerializedName("oldPrice")
    val oldPrice: String? = null,

    @SerializedName("stock")
    val stock: Int? = null
)