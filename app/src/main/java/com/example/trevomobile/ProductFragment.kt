package com.example.trevomobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.trevomobile.activity.DetailsProductActivity
import com.example.trevomobile.api.Product
import com.example.trevomobile.api.ProductResponse
import com.example.trevomobile.network.ServiceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class ProductFragment : Fragment() {
    private lateinit var products: List<Product>

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.product_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycleView)
        val buttonpesquisa = view.findViewById<ImageButton>(R.id.searchProduct)
        val labelpesquisa = view.findViewById<EditText>(R.id.search_bar)

        lifecycleScope.launch(Dispatchers.IO) {
            val call: Call<ProductResponse> = ServiceProvider().productservice.getProducts()
            val response: Response<ProductResponse> = call.execute()

            if (response.isSuccessful) {
                val productResponse: ProductResponse? = response.body()
                if (productResponse != null) {
                    products = productResponse.content // Salva a lista de produtos na propriedade 'products'
                    withContext(Dispatchers.Main) {
                        val adapter = ProductListAdapter(requireContext(), products)
                        recyclerView.adapter = adapter
                    }
                }
            }
        }
        buttonpesquisa.setOnClickListener {
            val input = labelpesquisa.text.toString().trim()
            if (input.isNotEmpty()) {
                val productId = input.toIntOrNull()
                if (productId != null) {
                    itemToDetail(productId)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Por favor insira o ID de um produto válido",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Por favor insira o ID de um produto",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }

    fun onItemClick(productId: Int) {
        itemToDetail(productId)
    }

    private fun itemToDetail(productId: Int) {
            val intent = Intent(requireContext(), DetailsProductActivity::class.java)
            intent.putExtra("productId", productId)
            startActivity(intent)

    }

}
