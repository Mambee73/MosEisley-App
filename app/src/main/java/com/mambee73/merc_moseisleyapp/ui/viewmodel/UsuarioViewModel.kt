package com.mambee73.merc_moseisleyapp.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UsuarioViewModel : ViewModel() {
    val nombre = mutableStateOf("")
    val correo = mutableStateOf("")
    val clave = mutableStateOf("")
    val carga = mutableStateOf("")
    val imagenUri = mutableStateOf<String?>(null)
}
