## ClothingStore Kotlin.

File structure of the project:
 

In this task I’ve used following dependencies:
1.	Cardview
    	    implementation 'androidx.cardview:cardview:1.0.0'

2.	Recyclerview
    	    implementation 'androidx.recyclerview:recyclerview:1.1.0'

3.	RxJava
  	    implementation 'io.reactivex.rxjava2:rxjava:2.2.4'

4.	RxKotlin
    	    implementation 'io.reactivex.rxjava2:rxkotlin:2.2.0'

5.	Retrofit 2
    implementation 'com.squareup.retrofit2:retrofit:2.6.1'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.1'
    implementation 'com.squareup.retrofit2:converter-scalars:2.3.0'

6.	Gson
  	   implementation 'com.google.code.gson:gson:2.8.5'

7.	Picasso
   	  implementation 'com.squareup.picasso:picasso:2.71828'



I have created 4 layout files:
activity_mian.xml: Which contains RecyclerView & Floating Button for checkout option.
product_row_item.xml: Which contains the raw item for the RecyclerView such as product image, title, addToCart, removeItem, addFavourite
shopping_cart: Which view the shoppingcart items
cart_list_item.xml: raw item for the shopping cart which contains product name, image & quantity.

I have created 3 model classes under ‘model’ package:
Clothing: contains Serialized objects of the JSON
CarItem: contains product & quantity to be added to shopping cart
FavoriteItem: contains product & quantity to be added to favorites

Then under network I’ve created API interface called APIService as below
 
And used Retrofit 2 for network calls in the APIConfig object
Then I’ve created the MainActivity along with ProductAdapter to load to load the Json data in the RecyclerView. Also I’ve implemented the adding & removing cart item and add favourites in the ProductAdapter and also I’ve created another class called ShoppingCart to add companion object to save to shopping cart items & favourites. (sample code given below)
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

Finally, I’ve created the ShoppingCartActivity & ShoppingCartAdapter to view the shopping cart. I’ve implemented this as an optional.
Screenshots:
                                   

