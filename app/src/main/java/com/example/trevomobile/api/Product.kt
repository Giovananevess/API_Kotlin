package com.example.trevomobile.api

import java.io.Serializable

data class Product (
    val idProduto: Int,
    val nome: String,
    val descricao: String,
    val area: String,
    val imagem: String,
    val capa: String,
    val maisvendido: Boolean,
    val culturas: List<String>

): Serializable
