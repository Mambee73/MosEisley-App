package com.mambee73.merc_moseisleyapp.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import com.mambee73.merc_moseisleyapp.model.Usuario

// Endpoints para manejar usuarios
interface ApiService {
    @GET("usuarios") // GET /api/usuarios
    suspend fun getUsuarios(): List<Usuario>

    @POST("usuarios") // POST /api/usuarios
    suspend fun postUsuario(@Body usuario: Usuario): Usuario
}
