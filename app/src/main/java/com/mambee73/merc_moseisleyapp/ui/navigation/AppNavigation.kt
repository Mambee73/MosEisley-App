package com.mambee73.merc_moseisleyapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen
import com.mambee73.merc_moseisleyapp.ui.screens.*
import com.mambee73.merc_moseisleyapp.ui.viewmodels.UsuarioViewModel
import com.mambee73.merc_moseisleyapp.ui.viewmodels.ProductoViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    usuarioViewModel: UsuarioViewModel,
    productoViewModel: ProductoViewModel
) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(Screen.Login.route) {
            LoginScreen(navController, usuarioViewModel)
        }

        composable(Screen.Registro.route) {
            RegistroScreen(navController, usuarioViewModel)
        }

        composable(Screen.Resumen.route) {
            ResumenScreen(navController, usuarioViewModel)
        }

        composable(Screen.Catalogo.route) {
            CatalogoScreen(navController, productoViewModel)
        }

        composable(Screen.SubirProducto.route) {
            SubirProductoScreen(navController, productoViewModel)
        }


    }
}
