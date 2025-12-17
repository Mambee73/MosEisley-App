package com.mambee73.merc_moseisleyapp.repository

import com.mambee73.merc_moseisleyapp.model.Usuario
import com.mambee73.merc_moseisleyapp.network.RetrofitInstance

import retrofit2.Response

class UsuarioRepository {
    suspend fun getUsuarios(): Response<List<Usuario>> {
        return RetrofitInstance.api.getUsuarios()
    }

    suspend fun postUsuario(usuario: Usuario): Response<Usuario> {
        return RetrofitInstance.api.postUsuario(usuario)
    }
}


