package com.mambee73.merc_moseisleyapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object Registro : Screen("registro")
    object Resumen : Screen("resumen")

    object Catalogo : Screen("catalogo")

    object SubirProducto : Screen("subir_producto")

    object Carrito : Screen("carrito")

    object EditarPerfil : Screen("editar_perfil")




}


