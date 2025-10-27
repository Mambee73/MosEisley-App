package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CatalogoScreen(navController: NavHostController) {
    val productos = listOf(
        "Vestimenta del Borde Exterior (Ropa)",
        "Relatos del Núcleo y Holo-Archivos (Libros/Cómics/Revistas)",
        "Circuitos y Gadgets (Artículos Tecnológicos)",
        "Mercancía Sellada / Sin abrir (Cosas Nuevas/Cerradas)",
        "Encuentros del Borde (Artículos de Segunda Mano/Curiosos)",
        "Entretenimiento Galáctico (Videojuegos/Holo-Juegos)",

    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Contrabando", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(productos) { producto ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(producto, style = MaterialTheme.typography.titleMedium)
                        Text("Disponible en Mos Eisley", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
