package com.mambee73.merc_moseisleyapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Instancia única de Retrofit para toda la app
object RetrofitInstance {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/api/") // emulador Android
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // API para usuarios
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    // API para productos
    val productoApi: ProductoApi by lazy {
        retrofit.create(ProductoApi::class.java)
    }
}



