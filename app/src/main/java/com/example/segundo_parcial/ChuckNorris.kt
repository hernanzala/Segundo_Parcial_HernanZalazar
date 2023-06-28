package com.example.segundo_parcial

import com.google.gson.annotations.SerializedName

data class ChuckNorris (

    @SerializedName("categories") val listaCategorias : List<String>,
    @SerializedName("value") val frase: String,
    val id: String,
    )

