package com.example.katalogproduk.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.katalogproduk.R

class AddProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_product)

        val etName = findViewById<EditText>(R.id.etName)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val etRating = findViewById<EditText>(R.id.etRating)
        val etDescription = findViewById<EditText>(R.id.etDescription)

        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {

            val resultIntent = Intent()

            resultIntent.putExtra("name", etName.text.toString())

            resultIntent.putExtra(
                "price",
                etPrice.text.toString().toDouble()
            )

            resultIntent.putExtra(
                "rating",
                etRating.text.toString().toFloat()
            )

            resultIntent.putExtra(
                "description",
                etDescription.text.toString()
            )

            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        }
    }
}