package com.example.katalogproduk.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.katalogproduk.R
import com.example.katalogproduk.activity.DetailActivity
import com.example.katalogproduk.model.Product
import com.example.katalogproduk.viewholder.ProductViewHolder

class ProductAdapter(
    private val productList: MutableList<Product>
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    // DiffUtil update
    fun updateList(newList: List<Product>) {

        val diffCallback = ProductDiffCallback(
            productList,
            newList
        )

        val diffResult = DiffUtil.calculateDiff(diffCallback)

        productList.clear()

        productList.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {

        val product = productList[position]

        holder.imgProduct.setImageResource(product.imageResId)

        holder.txtName.text = product.name

        // format harga rupiah
        holder.txtPrice.text =
            "Rp %,.0f".format(product.price)
                .replace(",", ".")

        holder.ratingBar.rating = product.rating

        // tombol keranjang
        holder.btnCart.setOnClickListener {

            Toast.makeText(
                holder.itemView.context,
                "${product.name} ditambahkan ke keranjang",
                Toast.LENGTH_SHORT
            ).show()
        }

        // klik detail
        holder.itemView.setOnClickListener {

            val intent = Intent(
                holder.itemView.context,
                DetailActivity::class.java
            )

            intent.putExtra("name", product.name)
            intent.putExtra("price", product.price)
            intent.putExtra("rating", product.rating)
            intent.putExtra("description", product.description)
            intent.putExtra("image", product.imageResId)

            holder.itemView.context.startActivity(intent)
        }

        // long click hapus
        holder.itemView.setOnLongClickListener {

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Hapus Produk")
                .setMessage("Yakin ingin menghapus ${product.name}?")

                .setPositiveButton("Ya") { _, _ ->

                    productList.removeAt(position)

                    notifyItemRemoved(position)

                    notifyItemRangeChanged(position, productList.size)

                    Toast.makeText(
                        holder.itemView.context,
                        "Produk dihapus",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                .setNegativeButton("Tidak", null)

                .show()

            true
        }

        // animasi item
        val animation = AnimationUtils.loadAnimation(
            holder.itemView.context,
            R.anim.item_animation
        )

        holder.itemView.startAnimation(animation)
    }
}