package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mambee73.merc_moseisleyapp.model.Producto
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen
import com.mambee73.merc_moseisleyapp.ui.viewmodels.ProductoViewModel

@Composable
fun CatalogoScreen(navController: NavHostController, productoViewModel: ProductoViewModel) {
    val categoriasDisponibles = listOf(
        "Ropa",
        "Libros/Cómics/Revistas",
        "Artículos Tecnológicos",
        "Cosas Nuevas/Cerradas",
        "Artículos de Segunda Mano/Curiosos",
        "Videojuegos/Holo-Juegos"
    )

    var categoriaSeleccionada by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Catálogo de la Cantina", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categoriasDisponibles) { categoria ->
                FilterChip(
                    selected = categoria == categoriaSeleccionada,
                    onClick = {
                        categoriaSeleccionada = if (categoriaSeleccionada == categoria) "" else categoria
                    },
                    label = { Text(categoria) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        val productosFiltrados = if (categoriaSeleccionada.isNotEmpty()) {
            productoViewModel.productos.filter { it.categoria == categoriaSeleccionada }
        } else {
            productoViewModel.productos
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(productosFiltrados) { producto ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
                        Text(producto.descripcion, style = MaterialTheme.typography.bodyMedium)
                        Text("Precio: ${producto.precio} créditos", style = MaterialTheme.typography.bodySmall)
                        Text("Categoría: ${producto.categoria}", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { navController.navigate(Screen.Resumen.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al resumen")
        }
    }
}
