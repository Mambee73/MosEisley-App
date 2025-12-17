package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mambee73.merc_moseisleyapp.ui.viewmodel.ProductoViewModel

// Pantalla para mostrar el detalle de un producto
@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    productoViewModel: ProductoViewModel,
    productoId: Int
) {
    // Buscar producto por id en el ViewModel
    val producto = productoViewModel.productos.value.find { it.id == productoId }

    // Si no se encuentra el producto
    if (producto == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Producto no encontrado", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Volver")
                }
            }
        }
        return
    }

    // Si el producto existe, mostrar detalle
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Detalle del producto", style = MaterialTheme.typography.headlineMedium)

                // Imagen o placeholder
                if (producto.imagenUri != null) {
                    AsyncImage(
                        model = producto.imagenUri,
                        contentDescription = "Imagen del producto",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Sin imagen disponible",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Nombre del producto
                Text(
                    text = producto.nombre,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                // Descripción
                Text(
                    text = producto.descripcion,
                    style = MaterialTheme.typography.bodyMedium
                )

                // Precio
                Text(
                    text = "Precio: ${producto.precio} créditos",
                    style = MaterialTheme.typography.titleMedium
                )

                // Categoría
                AssistChip(
                    onClick = { /* no-op */ },
                    label = { Text(producto.categoria) }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Botón para volver al catálogo
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Volver al catálogo")
                }
            }
        }
    }
}
