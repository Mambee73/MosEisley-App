package com.mambee73.merc_moseisleyapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mambee73.merc_moseisleyapp.ui.screens.*
import com.mambee73.merc_moseisleyapp.ui.viewmodels.UsuarioViewModel
import com.mambee73.merc_moseisleyapp.ui.viewmodels.ProductoViewModel
import com.mambee73.merc_moseisleyapp.ui.viewmodels.CarritoViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    productoViewModel: ProductoViewModel,
    carritoViewModel: CarritoViewModel
) {
    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

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
            CatalogoScreen(navController, productoViewModel, carritoViewModel)
        }

        composable(Screen.SubirProducto.route) {
            SubirProductoScreen(navController, productoViewModel)
        }

        composable(Screen.Carrito.route) {
            CarritoScreen(navController, carritoViewModel)
        }
    }
}
