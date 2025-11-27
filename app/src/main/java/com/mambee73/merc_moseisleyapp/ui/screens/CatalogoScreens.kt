package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen
import com.mambee73.merc_moseisleyapp.ui.viewmodels.CarritoViewModel
import com.mambee73.merc_moseisleyapp.ui.viewmodels.ProductoViewModel

@Composable
fun CatalogoScreen(
    navController: NavHostController,
    productoViewModel: ProductoViewModel,
    carritoViewModel: CarritoViewModel
) {
    var categoriaSeleccionada by remember { mutableStateOf("") }
    var textoBusqueda by remember { mutableStateOf("") }

    val productosFiltrados = productoViewModel.productos.filter { producto ->
        val coincideCategoria = categoriaSeleccionada.isEmpty() ||
                producto.categoria.startsWith(categoriaSeleccionada, ignoreCase = true)

        val coincideBusqueda = textoBusqueda.isEmpty() ||
                producto.nombre.contains(textoBusqueda, ignoreCase = true)

        coincideCategoria && coincideBusqueda
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Catálogo Mos Eisley", style = MaterialTheme.typography.headlineMedium)
        Text("Productos cargados: ${productoViewModel.productos.size}")

        OutlinedTextField(
            value = textoBusqueda,
            onValueChange = { textoBusqueda = it },
            label = { Text("Buscar producto") },
            modifier = Modifier.fillMaxWidth()
        )

        val categorias = listOf(
            "Ropa",
            "Libros/Cómics/Revistas",
            "Artículos Tecnológicos",
            "Cosas Nuevas/Cerradas",
            "Artículos de Segunda Mano/Curiosos",
            "Videojuegos/Holo-Juegos"
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categorias) { categoria ->
                FilterChip(
                    selected = categoriaSeleccionada == categoria,
                    onClick = {
                        categoriaSeleccionada = if (categoriaSeleccionada == categoria) "" else categoria
                    },
                    label = { Text(categoria) }
                )
            }
        }

        Button(onClick = {
            categoriaSeleccionada = ""
            textoBusqueda = ""
        }) {
            Text("Mostrar todos los productos")
        }

        if (productosFiltrados.isEmpty()) {
            Text("No se encontraron productos en esta categoría o búsqueda.")
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(productosFiltrados) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // 🔹 Navegar al detalle con el id del producto
                            navController.navigate(Screen.ProductDetail.createRoute(producto.id))
                        }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
                        Text(producto.descripcion, maxLines = 2) // resumen corto
                        Text("Precio: ${producto.precio} créditos")
                        Text("Categoría: ${producto.categoria}", style = MaterialTheme.typography.labelSmall)

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = { carritoViewModel.agregarAlCarrito(producto) }) {
                            Text("Agregar al carrito")
                        }
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

        Button(
            onClick = { navController.navigate(Screen.Carrito.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir al carrito")
        }
    }
}
