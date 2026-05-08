package com.example.katalogproduk.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.katalogproduk.R

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imgProduct: ImageView = itemView.findViewById(R.id.imgProduct)

    val txtName: TextView = itemView.findViewById(R.id.txtName)

    val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)

    val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)

    val btnCart: Button = itemView.findViewById(R.id.btnCart)
}