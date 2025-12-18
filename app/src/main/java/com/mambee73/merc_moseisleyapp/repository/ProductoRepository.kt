package com.mambee73.merc_moseisleyapp.repository

import com.mambee73.merc_moseisleyapp.model.Producto
import com.mambee73.merc_moseisleyapp.network.RetrofitInstance //uso RetrofitInstancepara obtener y enviar usuarios.
import retrofit2.Response

//Los repositorios son la capa que separa la lógica de red de la interfaz
class ProductoRepository {
    suspend fun getProductos(): Response<List<Producto>> {
        return RetrofitInstance.productoApi.getProductos()
    }

    suspend fun postProducto(producto: Producto): Response<Producto> {
        return RetrofitInstance.productoApi.postProducto(producto)
    }
}



