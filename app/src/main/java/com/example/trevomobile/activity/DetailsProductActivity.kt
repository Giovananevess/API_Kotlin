package com.example.trevomobile.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.example.trevomobile.R
import com.example.trevomobile.api.Product
import com.example.trevomobile.network.ServiceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response


class DetailsProductActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_fragment)

        val productId = intent.getIntExtra("productId", 1)
        CoroutineScope(Dispatchers.Main).launch {
            busqueId(productId)
        }

        val addToCartButton = findViewById<Button>(R.id.buttonAddToBudget)
        addToCartButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("listprodutos", Context.MODE_PRIVATE)

            val productListString = sharedPreferences.getString("productList", "")
            val productList =
                productListString?.split(",")?.mapNotNull { it.toIntOrNull() }?.toMutableList()
                    ?: mutableListOf()
            productList.add(productId)
            val updatedProductList = productList.joinToString(",")
            val editor = sharedPreferences.edit()
            editor.putString("productList", updatedProductList)
            editor.apply()

            Toast.makeText(this@DetailsProductActivity, "Produto adicionado", Toast.LENGTH_SHORT)
                .show()
            println(productList)
        }


    }

    suspend fun busqueId(productId: Int) {
        try {
            val call: Call<Product> = ServiceProvider().productservice.getProductById(productId)
            val response: Response<Product> = withContext(Dispatchers.IO) { call.execute() }

            if (response.isSuccessful) {
                val product: Product? = response.body()
                if (product != null) {
                    withContext(Dispatchers.Main) {
                        mostraConteudo(product)
                    }
                }
            } else {
                println(productId)
            }
        } catch (e: Exception) {
            // Trate aqui outras exceções, como falha na conexão, etc.
        }
    }

    fun mostraConteudo(product: Product) {
        val nomeproduto = findViewById<TextView>(R.id.titleDetail)
        val descricaoproduct = findViewById<TextView>(R.id.descriptionDetail)
        val infoculturaproduto = findViewById<TextView>(R.id.infoCulture)
        val infoareaproduto = findViewById<TextView>(R.id.infoArea)
        val imageproduto = findViewById<ImageView>(R.id.imageProduct)
        nomeproduto.text = product.nome
        descricaoproduct.text = product.descricao
        infoculturaproduto.text = product.culturas.toString()
        infoareaproduto.text = product.area
        try {
            Glide.with(this)
                .load("http://192.168.0.109:8080/trevo/api/produto/capa/" + product.capa)
                .into(imageproduto)
        } catch (e: GlideException) {
            Log.d("TAG", e.toString())
        }
    }

}



