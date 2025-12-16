package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen
import com.mambee73.merc_moseisleyapp.ui.viewmodels.CarritoViewModel

// Pantalla para mostrar el carrito de compras
@Composable
fun CarritoScreen(navController: NavHostController, carritoViewModel: CarritoViewModel) {
    var mostrarDialogo by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 120.dp), // espacio para los botones
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Tu carrito", style = MaterialTheme.typography.headlineMedium)

            // Lista de productos en el carrito
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(carritoViewModel.carrito) { producto ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
                            Text("Precio: ${producto.precio} créditos", style = MaterialTheme.typography.bodyMedium)
                            Text("Categoría: ${producto.categoria}", style = MaterialTheme.typography.labelSmall)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { carritoViewModel.quitarDelCarrito(producto) }) {
                                Text("Quitar del carrito")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Total: ${carritoViewModel.calcularTotal()} créditos",
                style = MaterialTheme.typography.titleMedium
            )
        }

        // Botones fijos al fondo
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { mostrarDialogo = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pagar")
            }

            Button(
                onClick = { navController.navigate(Screen.Resumen.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al resumen")
            }
        }

        // Diálogo de confirmación de pago
        if (mostrarDialogo) {
            AlertDialog(
                onDismissRequest = { mostrarDialogo = false },
                title = { Text("¡Toma asiento comprador!") },
                text = { Text("Redirigiendo a entidad bancaria intergaláctica...") },
                confirmButton = {
                    Button(onClick = {
                        mostrarDialogo = false
                        carritoViewModel.vaciarCarrito()
                        navController.navigate(Screen.Catalogo.route)
                    }) {
                        Text("Aceptar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { mostrarDialogo = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

