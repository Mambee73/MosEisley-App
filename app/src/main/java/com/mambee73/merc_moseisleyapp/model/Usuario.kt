package com.mambee73.merc_moseisleyapp.model

data class Usuario(
    val id: Int,                // Identificador único del usuario
    val nombre: String,         // Nombre del usuario
    val correo: String,         // Correo electrónico
    val clave: String,          // Contraseña o clave de acceso
    val imagenUri: String? = null // Ruta/URI de la imagen de perfil (opcional)
)
