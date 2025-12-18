package com.mambee73.merc_moseisleyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.mambee73.merc_moseisleyapp.ui.navigation.AppNavigation
import com.mambee73.merc_moseisleyapp.ui.theme.MercMosEisleyAppTheme
import com.mambee73.merc_moseisleyapp.ui.viewmodel.ProductoViewModel
import com.mambee73.merc_moseisleyapp.ui.viewmodel.CarritoViewModel
import com.mambee73.merc_moseisleyapp.ui.viewmodel.UsuarioViewModel

// Actividad principal de la aplicación
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crear los ViewModels aquí para que se mantengan vivos durante toda la app
        val productoViewModel: ProductoViewModel by viewModels()
        val carritoViewModel: CarritoViewModel by viewModels()
        val usuarioViewModel: UsuarioViewModel by viewModels()

        // Llamadas iniciales para cargar datos desde el backend
        usuarioViewModel.fetchUsuarios()
        productoViewModel.fetchProductos()

        setContent {
            // Tema de la aplicación
            MercMosEisleyAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    // Controlador de navegación
                    val navController = rememberNavController()

                    // Pasar los ViewModels a la navegación
                    AppNavigation(
                        navController = navController,
                        productoViewModel = productoViewModel,
                        carritoViewModel = carritoViewModel,
                        usuarioViewModel = usuarioViewModel
                    )
                }
            }
        }
    }
}





