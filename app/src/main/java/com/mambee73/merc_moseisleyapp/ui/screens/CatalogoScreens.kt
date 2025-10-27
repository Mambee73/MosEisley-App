package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
    val categorias = listOf(
        "Ropa", "Libros", "Tecnología", "Sellado", "Segunda Mano", "Videojuegos"
    )

    val productos = listOf(
        Producto("Vestimenta del Borde Exterior", "Ropa resistente", 250.0, "Ropa"),
        Producto("Relatos del Núcleo", "Historias clásicas", 120.0, "Libros"),
        Producto("Circuito imperial", "Tecnología avanzada", 500.0, "Tecnología"),
        Producto("Caja sellada de especia", "Sin abrir", 800.0, "Sellado"),
        Producto("Encuentro del Borde", "Curioso y usado", 90.0, "Segunda Mano"),
        Producto("Holo-Juego de carreras", "Entretenimiento", 300.0, "Videojuegos")
    )

    var categoriaSeleccionada by remember { mutableStateOf("") }
    var busqueda by remember { mutableStateOf("") }

    val productosFiltrados = productos.filter {
        (categoriaSeleccionada.isEmpty() || it.categoria == categoriaSeleccionada) &&
                (busqueda.isEmpty() || it.nombre.contains(busqueda, ignoreCase = true))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Contrabando", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            label = { Text("Buscar producto") },
            modifier = Modifier.fillMaxWidth()
        )


        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            categorias.forEach { categoria ->
                FilterChip(
                    selected = categoriaSeleccionada == categoria,
                    onClick = {
                        categoriaSeleccionada = if (categoriaSeleccionada == categoria) "" else categoria
                    },
                    label = { Text(categoria) }
                )
            }
        }


        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(productosFiltrados) { producto ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                        Text(producto.descripcion, style = MaterialTheme.typography.bodySmall)
                        Text("Precio: ${producto.precio} créditos", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }

        Button(
            onClick = { navController.navigate(Screen.Resumen.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al resumen")
        }
    }
}
