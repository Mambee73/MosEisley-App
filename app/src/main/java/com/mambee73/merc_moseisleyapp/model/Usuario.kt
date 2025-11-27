package com.mambee73.merc_moseisleyapp.model

data class Usuario(
    val id: Int,
    val nombre: String,
    val correo: String,
    val clave: String,
    val imagenUri: String? = null
)
