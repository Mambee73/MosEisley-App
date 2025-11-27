package com.mambee73.merc_moseisleyapp.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mambee73.merc_moseisleyapp.model.Usuario

class UsuarioViewModel : ViewModel() {
    val nombre = mutableStateOf("")
    val correo = mutableStateOf("")
    val clave = mutableStateOf("")
    val carga = mutableStateOf("")
    val imagenUri = mutableStateOf<String?>(null)


    fun getUsuarioActual(): Usuario {
        return Usuario(
            id = 1,
            nombre = nombre.value,
            correo = correo.value,
            clave = clave.value,
            imagenUri = imagenUri.value
        )
    }

    // 🔹 Ejemplo de login simple
    fun login(usuario: String, claveIngresada: String): Boolean {
        return usuario == "Usuario1" && claveIngresada == "mos123"
    }
}

