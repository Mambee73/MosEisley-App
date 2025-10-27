package com.mambee73.merc_moseisleyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.mambee73.merc_moseisleyapp.ui.navigation.AppNavigation
import com.mambee73.merc_moseisleyapp.ui.theme.*
import com.mambee73.merc_moseisleyapp.ui.viewmodels.ProductoViewModel
import com.mambee73.merc_moseisleyapp.ui.viewmodels.CarritoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Crear los ViewModels aquí para que se mantengan vivos
        val productoViewModel: ProductoViewModel by viewModels()
        val carritoViewModel: CarritoViewModel by viewModels()

        setContent {
            MercMosEisleyAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    // ✅ Pasarlos a AppNavigation
                    AppNavigation(
                        navController = navController,
                        productoViewModel = productoViewModel,
                        carritoViewModel = carritoViewModel
                    )
                }
            }
        }
    }
}



