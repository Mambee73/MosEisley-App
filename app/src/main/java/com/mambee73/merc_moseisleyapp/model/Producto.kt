package com.mambee73.merc_moseisleyapp.model


data class Producto(
    val id: Int,                // ID único del producto
    val nombre: String,         // Nombre del producto
    val descripcion: String,    // Descripción del producto
    val precio: Double,         // Precio
    val categoria: String,      // Categoría (ej: Ropa, Libros, etc.)
    val imagenUri: String? = null // URI de la imagen (puede ser null si no hay)
)



