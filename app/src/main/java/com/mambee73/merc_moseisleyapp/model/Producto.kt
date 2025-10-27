package com.mambee73.merc_moseisleyapp.model

data class Producto(
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val categoria: String,
    val imagenUrl: String = ""
)

