package com.mambee73.merc_moseisleyapp.model
import com.google.gson.annotations.SerializedName

// retrofit para convertir datos JSON del backend en objetos de kotlin
data class Producto(
    @SerializedName("ID") val id: Int,
    @SerializedName("NOMBRE") val nombre: String,
    @SerializedName("DESCRIPCION") val descripcion: String,
    @SerializedName("PRECIO") val precio: Double,
    @SerializedName("CATEGORIA") val categoria: String,
    @SerializedName("IMAGENURI") val imagenUri: String? = null
)




