package com.example.katalogproduk.model

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val rating: Float,
    val imageResId: Int,
    val description: String
)