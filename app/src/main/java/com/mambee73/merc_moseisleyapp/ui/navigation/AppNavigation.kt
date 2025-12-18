package com.mambee73.merc_moseisleyapp.ui.navigation
//organizar toda la navegación de la aplicación

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mambee73.merc_moseisleyapp.ui.screens.*
import com.mambee73.merc_moseisleyapp.ui.viewmodel.CarritoViewModel
import com.mambee73.merc_moseisleyapp.ui.viewmodel.ProductoViewModel
import com.mambee73.merc_moseisleyapp.ui.viewmodel.UsuarioViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    productoViewModel: ProductoViewModel,
    carritoViewModel: CarritoViewModel,
    usuarioViewModel: UsuarioViewModel
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
            ResumenScreen(navController, usuarioViewModel, productoViewModel)
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

        composable(Screen.VerPerfil.route) {
            VerPerfilScreen(navController, usuarioViewModel)
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
