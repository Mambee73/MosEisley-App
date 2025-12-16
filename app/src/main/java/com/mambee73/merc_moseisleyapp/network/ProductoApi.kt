package com.mambee73.merc_moseisleyapp.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import com.mambee73.merc_moseisleyapp.model.Producto

// Endpoints para manejar productos
interface ProductoApi {
    @GET("productos") // GET /api/productos
    suspend fun getProductos(): List<Producto>

    @POST("productos") // POST /api/productos
    suspend fun postProducto(@Body producto: Producto): Producto
}
