package com.example.katalogproduk

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.katalogproduk.activity.AddProductActivity
import com.example.katalogproduk.adapter.ProductAdapter
import com.example.katalogproduk.model.Product

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: ProductAdapter

    private val productList = mutableListOf<Product>()

    // backup data untuk search
    private val fullProductList = mutableListOf<Product>()

    // launcher tambah produk
    private val addProductLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == Activity.RESULT_OK) {

                val data = result.data

                if (data != null) {

                    val newProduct = Product(
                        productList.size + 1,
                        data.getStringExtra("name") ?: "",
                        data.getDoubleExtra("price", 0.0),
                        data.getFloatExtra("rating", 0f),
                        R.drawable.product11,
                        data.getStringExtra("description") ?: ""
                    )

                    productList.add(newProduct)

                    fullProductList.add(newProduct)

                    adapter.notifyItemInserted(productList.size - 1)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val btnAdd = findViewById<Button>(R.id.btnAdd)

        val searchView = findViewById<SearchView>(R.id.searchView)

        addProducts()

        // backup semua data
        fullProductList.addAll(productList)

        adapter = ProductAdapter(productList)

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        recyclerView.adapter = adapter

        // tombol tambah produk
        btnAdd.setOnClickListener {

            val intent = Intent(
                this,
                AddProductActivity::class.java
            )

            addProductLauncher.launch(intent)
        }

        // search/filter
        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    filterProducts(newText ?: "")

                    return true
                }
            }
        )
    }

    // filter produk
    private fun filterProducts(query: String) {

        val filteredList = fullProductList.filter {

            it.name.contains(query, ignoreCase = true)
        }

        adapter.updateList(filteredList)
    }

    // data dummy produk
    private fun addProducts() {

        productList.add(
            Product(
                1,
                "Laptop Gaming",
                12000000.0,
                4.5f,
                R.drawable.product1,
                "Laptop gaming keren"
            )
        )

        productList.add(
            Product(
                2,
                "Mouse RGB",
                250000.0,
                4.0f,
                R.drawable.product2,
                "Mouse gaming RGB"
            )
        )

        productList.add(
            Product(
                3,
                "Keyboard Mechanical",
                500000.0,
                5.0f,
                R.drawable.product3,
                "Keyboard mekanikal"
            )
        )

        productList.add(
            Product(
                4,
                "Headset Gaming",
                350000.0,
                4.2f,
                R.drawable.product4,
                "Headset keren"
            )
        )

        productList.add(
            Product(
                5,
                "Monitor",
                2000000.0,
                4.7f,
                R.drawable.product5,
                "Monitor gaming"
            )
        )
    }
}