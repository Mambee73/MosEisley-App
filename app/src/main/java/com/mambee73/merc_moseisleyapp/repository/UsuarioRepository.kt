package com.mambee73.merc_moseisleyapp.repository

import com.mambee73.merc_moseisleyapp.model.Usuario
import com.mambee73.merc_moseisleyapp.network.RetrofitInstance //comunica con el backend
//La idea es que las pantallas y los ViewModels nunca hagan llamadas HTTP directas
import retrofit2.Response

class UsuarioRepository {
    suspend fun getUsuarios(): Response<List<Usuario>> {
        return RetrofitInstance.api.getUsuarios()
    }

    suspend fun postUsuario(usuario: Usuario): Response<Usuario> { //obtener y enviar
        return RetrofitInstance.api.postUsuario(usuario)
    }
}


