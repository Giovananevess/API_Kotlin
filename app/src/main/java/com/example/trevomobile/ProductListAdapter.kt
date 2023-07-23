package com.example.trevomobile

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.example.trevomobile.activity.DetailsProductActivity
import com.example.trevomobile.api.ItemClickListener
import com.example.trevomobile.api.Product

class ProductListAdapter(private val context: Context, private val products: List<Product>) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {


    private var onItemClickListener: ItemClickListener? = null
    fun setOnItemClickListener(listener: ItemClickListener) {
        onItemClickListener = listener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(product: Product) {
            val title = itemView.findViewById<TextView>(R.id.cardTitle)
            val img = itemView.findViewById<ImageView>(R.id.cardImage)
            val button = itemView.findViewById<Button>(R.id.cardButton)
            title.text = product.nome
            button.setOnClickListener {
                val productId = product.idProduto
                val intent = Intent(itemView.context, DetailsProductActivity::class.java)
                println(productId)
                intent.putExtra("productId", productId)
                itemView.context.startActivity(intent)
            }
            try {
                Glide.with(itemView.context)
                    .load("http://192.168.0.109:8080/trevo/api/produto/foto/" + product.imagem)
                    .into(img)
            } catch (e: GlideException) {
                Log.d("TAG", e.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.product_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.vincula(product)
    }
}