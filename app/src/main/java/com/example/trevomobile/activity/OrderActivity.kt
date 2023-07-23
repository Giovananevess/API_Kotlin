package com.example.trevomobile.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.trevomobile.R

class OrderActivity :  AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_activity)
//        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)
//        // recyclerView.adapter = ListProductAdapter(this, products = listOf(Product("vasco", "http://10.0.0.43:8080/trevo/api/produto/foto/product_tour_15_1595611598493_ADVANCE_2000_VORTEX_16.jpg")))
//        val button = findViewById<Button>(R.id.buttonFooterOrder)

    }

    fun OrderToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}