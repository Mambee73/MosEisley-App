package com.mambee73.merc_moseisleyapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mambee73.merc_moseisleyapp.model.Usuario
import com.mambee73.merc_moseisleyapp.repository.UsuarioRepository
import kotlinx.coroutines.launch

// ViewModel para manejar usuarios
class UsuarioViewModel : ViewModel() {
    // Estados locales para Compose (inputs del formulario)
    val nombre = mutableStateOf("")
    val correo = mutableStateOf("")
    val clave = mutableStateOf("")
    val carga = mutableStateOf("")
    val imagenUri = mutableStateOf<String?>(null)

    // Lista de usuarios cargados desde el backend
    val usuarios = mutableStateOf<List<Usuario>>(emptyList())

    // Repositorio de usuarios
    private val repository = UsuarioRepository()

    // Crear un objeto Usuario con los datos actuales
    fun getUsuarioActual(): Usuario {
        return Usuario(
            id = 1,
            nombre = nombre.value,
            correo = correo.value,
            clave = clave.value,
            imagenUri = imagenUri.value
        )
    }

    // Validación simple de login
    fun login(usuario: String, claveIngresada: String): Boolean {
        return usuario == "Usuario1" && claveIngresada == "mos123"
    }

    // Cargar usuarios desde el backend
    fun fetchUsuarios() {
        viewModelScope.launch {
            try {
                usuarios.value = repository.getUsuarios()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Agregar usuario nuevo al backend
    fun addUsuario(usuario: Usuario) {
        viewModelScope.launch {
            try {
                repository.postUsuario(usuario)
                fetchUsuarios() // refresca lista después de agregar
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
