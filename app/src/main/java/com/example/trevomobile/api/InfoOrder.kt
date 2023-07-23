package com.example.trevomobile.api

import com.google.gson.JsonObject

data class InfoOrder(
    val cliente: JsonObject,
    val produtos: List<Int>
)