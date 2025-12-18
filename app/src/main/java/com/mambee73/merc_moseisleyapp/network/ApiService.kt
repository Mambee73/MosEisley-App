package com.mambee73.merc_moseisleyapp.network //definimos cómo la app se comunica con el backend

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import com.mambee73.merc_moseisleyapp.model.Usuario

interface ApiService {
    @GET("usuarios")
    suspend fun getUsuarios(): Response<List<Usuario>>
// contiene los endpoints para usuarios
    @POST("usuarios")
    suspend fun postUsuario(@Body usuario: Usuario): Response<Usuario>
}

