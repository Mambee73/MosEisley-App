package com.mambee73.merc_moseisleyapp.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UsuarioViewModel : ViewModel() {
    var nombre = mutableStateOf("")
    var correo = mutableStateOf("")
    var clave = mutableStateOf("")
    var carga = mutableStateOf("")
}
