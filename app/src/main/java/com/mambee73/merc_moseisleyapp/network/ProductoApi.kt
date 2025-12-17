package com.mambee73.merc_moseisleyapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import com.mambee73.merc_moseisleyapp.model.Producto

interface ProductoApi {
    @GET("productos")
    suspend fun getProductos(): Response<List<Producto>>

    @POST("productos")
    suspend fun postProducto(@Body producto: Producto): Response<Producto>
}
