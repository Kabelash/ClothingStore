package com.kabelash.clothingstore.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kabelash.clothingstore.ui.Adapter.ProductAdapter
import com.kabelash.clothingstore.R
import com.kabelash.clothingstore.model.Clothing
import com.kabelash.clothingstore.network.APIConfig
import com.kabelash.clothingstore.network.APIService
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: APIService
    private lateinit var productAdapter: ProductAdapter

    private var products = listOf<Clothing>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        apiService = APIConfig.getRetrofitClient(this).create(APIService::class.java)

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary))
        swipeRefreshLayout.isRefreshing = true

        products_recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        cart_size.text = ShoppingCart.getShoppingCartSize().toString()

        getProducts()

        //To open Shopping cart activity
        showCart.setOnClickListener {
            startActivity(Intent(this, ShoppingCartActivity::class.java))
        }

    }


    private fun getProducts() {

        apiService.getProducts().enqueue(object : retrofit2.Callback<List<Clothing>> {
            override fun onFailure(call: Call<List<Clothing>>, t: Throwable) {
                //For testing purposes
                Log.d("Data error", t.message)
                //Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<List<Clothing>>, response: Response<List<Clothing>>) {

                swipeRefreshLayout.isRefreshing = false
                swipeRefreshLayout.isEnabled = false

                products = response.body()!!

                productAdapter =
                    ProductAdapter(
                        this@MainActivity,
                        products
                    )

                products_recyclerview.adapter = productAdapter

                productAdapter.notifyDataSetChanged()

            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }
}
