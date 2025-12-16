package com.mambee73.merc_moseisleyapp.repository

import com.mambee73.merc_moseisleyapp.model.Usuario
import com.mambee73.merc_moseisleyapp.network.RetrofitInstance

// Repositorio para manejar usuarios
// Se encarga de hablar con el backend usando ApiService
class UsuarioRepository {

    // Obtener lista de usuarios desde el backend
    suspend fun getUsuarios(): List<Usuario> {
        return RetrofitInstance.api.getUsuarios()
    }

    // Enviar usuario nuevo al backend
    suspend fun postUsuario(usuario: Usuario): Usuario {
        return RetrofitInstance.api.postUsuario(usuario)
    }
}

