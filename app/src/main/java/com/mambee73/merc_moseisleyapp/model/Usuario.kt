package com.mambee73.merc_moseisleyapp.model

import com.google.gson.annotations.SerializedName

data class Usuario(
    @SerializedName("ID") val id: Int? = null,          // Autogenerado por Oracle
    @SerializedName("NOMBRE") val nombre: String,       // Nombre del usuario
    @SerializedName("CORREO") val correo: String,       // Correo electrónico
    @SerializedName("CLAVE") val clave: String,         // Clave de acceso
    @SerializedName("CARGA") val carga: String? = null, // Info adicional (puede ser null)
    @SerializedName("IMAGENURI") val imagenUri: String? = null // URI de imagen de perfil
)
