package com.mambee73.merc_moseisleyapp.ui.navigation
//utilizo una sealed class para declarar todas las rutas de forma segura
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object Registro : Screen("registro")
    object Resumen : Screen("resumen")

    object Catalogo : Screen("catalogo")

    object SubirProducto : Screen("subir_producto")

    object Carrito : Screen("carrito") //Esto evita errores comunes como strings mal escritos.

    object EditarPerfil : Screen("editar_perfil")

    object VerPerfil : Screen("ver_perfil")

    object ProductDetail : Screen("product_detail/{id}") {
        fun createRoute(id: Int) = "product_detail/$id" //parámetros dinámicos como el id del producto
    }
}
