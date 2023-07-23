package com.example.trevomobile.api

import java.io.Serializable

data class Cliente(
    val nome: String,
    val email: String,
    val telefone: String

): Serializable

