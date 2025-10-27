package com.mambee73.merc_moseisleyapp.ui.navigation




import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen
import com.mambee73.merc_moseisleyapp.ui.screens.*
import com.mambee73.merc_moseisleyapp.ui.viewmodels.UsuarioViewModel

@Composable
fun AppNavigation(navController: NavHostController, usuarioViewModel: UsuarioViewModel) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
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
    }
}
