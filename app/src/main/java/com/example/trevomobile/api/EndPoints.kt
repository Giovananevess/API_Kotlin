package com.example.trevomobile.api


import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface EndPoints {
    @GET("api/produtos")
    fun getProducts(): Call<ProductResponse>

    @GET("api/produto/{id}")
    fun getProductById(@Path(value = "id", encoded = true) id: Int): Call<Product>


    @POST("api/proposta")
    fun createClient(@Body client: Cliente): Call<Void>

    @GET(value = "api/propostas")
    fun getBudgetByEmail(@Query("email") email: String): Call<BudgetResponse>

    @POST(value = "api/proposta")
    fun sendorder(@Body orcamento: JsonObject) : Call<JsonObject>
}
