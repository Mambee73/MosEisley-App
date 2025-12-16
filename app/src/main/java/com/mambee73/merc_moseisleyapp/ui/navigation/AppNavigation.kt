package com.mambee73.merc_moseisleyapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mambee73.merc_moseisleyapp.ui.RegistroScreen
import com.mambee73.merc_moseisleyapp.ui.screens.*
import com.mambee73.merc_moseisleyapp.ui.viewmodels.CarritoViewModel
import com.mambee73.merc_moseisleyapp.ui.viewmodels.ProductoViewModel
import com.mambee73.merc_moseisleyapp.ui.viewmodel.UsuarioViewModel


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

        composable(Screen.EditarPerfil.route) {
            EditarPerfilScreen(navController, usuarioViewModel)
        }

        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: return@composable
            ProductDetailScreen(navController, productoViewModel, id)
        }


    }
}
