package com.example.trevomobile.api

import com.google.gson.annotations.SerializedName

data class Order (
    @SerializedName("idProposta")
    val idProposta: Int,
    @SerializedName("data")
    val data: List<Int>?,
    @SerializedName("cliente")
    val cliente: Cliente,
    @SerializedName("produtos")
    val produtos: List<Int>
        )