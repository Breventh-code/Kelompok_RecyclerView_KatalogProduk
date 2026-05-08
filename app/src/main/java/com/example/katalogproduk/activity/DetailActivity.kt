package com.example.katalogproduk.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.katalogproduk.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        val image = findViewById<ImageView>(R.id.detailImage)
        val name = findViewById<TextView>(R.id.detailName)
        val price = findViewById<TextView>(R.id.detailPrice)
        val rating = findViewById<RatingBar>(R.id.detailRating)
        val description = findViewById<TextView>(R.id.detailDescription)

        val productName = intent.getStringExtra("name")
        val productPrice = intent.getDoubleExtra("price", 0.0)
        val productRating = intent.getFloatExtra("rating", 0f)
        val productDescription = intent.getStringExtra("description")
        val productImage = intent.getIntExtra("image", 0)

        image.setImageResource(productImage)

        name.text = productName

        price.text = "Rp $productPrice"

        rating.rating = productRating

        description.text = productDescription
    }
}