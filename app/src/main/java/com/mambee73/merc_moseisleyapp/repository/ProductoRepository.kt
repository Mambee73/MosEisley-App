package com.mambee73.merc_moseisleyapp.repository

import com.mambee73.merc_moseisleyapp.model.Producto
import com.mambee73.merc_moseisleyapp.network.RetrofitInstance

// Repositorio para manejar productos
class ProductoRepository {
    // Instancia de la API de productos desde RetrofitInstance
    private val api = RetrofitInstance.productoApi

    // Obtener lista de productos desde el backend
    suspend fun getProductos(): List<Producto> {
        // Llama al endpoint GET /api/productos
        return api.getProductos()
    }

    // Enviar producto nuevo al backend
    suspend fun postProducto(producto: Producto): Producto {
        // Llama al endpoint POST /api/productos
        return api.postProducto(producto)
    }
}


