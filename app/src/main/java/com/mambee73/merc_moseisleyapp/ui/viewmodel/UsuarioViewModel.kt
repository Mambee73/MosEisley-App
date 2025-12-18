package com.mambee73.merc_moseisleyapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mambee73.merc_moseisleyapp.model.Usuario
import com.mambee73.merc_moseisleyapp.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuarioViewModel( //valida credenciales y maneja el registro
    private val repository: UsuarioRepository = UsuarioRepository()
) : ViewModel() {

    val nombre = mutableStateOf("")
    val correo = mutableStateOf("")
    val clave = mutableStateOf("")
    val carga = mutableStateOf("")
    val imagenUri = mutableStateOf<String?>(null)

    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios: StateFlow<List<Usuario>> = _usuarios

    fun getUsuarioActual(): Usuario {
        return Usuario(
            id = 0,
            nombre = nombre.value,
            correo = correo.value,
            clave = clave.value,
            carga = carga.value,
            imagenUri = imagenUri.value
        )
    }

    fun fetchUsuarios() {
        viewModelScope.launch {
            try {
                val response = repository.getUsuarios()
                if (response.isSuccessful) {
                    _usuarios.value = response.body() ?: emptyList()
                    Log.d("API", "Usuarios recibidos: ${response.body()}")
                } else {
                    Log.e("API", "Error al obtener usuarios: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Excepción al obtener usuarios", e)
                _usuarios.value = emptyList()
            }
        }
    }

    fun addUsuario(usuario: Usuario) {
        viewModelScope.launch {
            try {
                val response = repository.postUsuario(usuario)
                if (response.isSuccessful) {
                    Log.d("API", "Usuario agregado: ${response.body()}")
                    fetchUsuarios()
                } else {
                    Log.e("API", "Error al agregar usuario: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Excepción al agregar usuario", e)
            }
        }
    }

    // ✅ Nueva función de login
    fun login(usuario: String, claveIngresada: String): Boolean {
        return _usuarios.value.any { it.nombre == usuario && it.clave == claveIngresada }
    }
}
