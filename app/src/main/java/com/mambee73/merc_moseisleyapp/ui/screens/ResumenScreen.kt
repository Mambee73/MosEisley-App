package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mambee73.merc_moseisleyapp.ui.viewmodels.UsuarioViewModel
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen

@Composable
fun ResumenScreen(navController: NavHostController, usuarioViewModel: UsuarioViewModel) {
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(0.9f),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("🎟️ Ticket de Entrada", style = MaterialTheme.typography.headlineMedium)

                usuarioViewModel.imagenUri.value?.let { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = "Imagen de perfil",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    )
                }

                Text("👤 Usuario: ${usuarioViewModel.nombre.value}")
                Text("📧 Correo: ${usuarioViewModel.correo.value}")
                Text("🔑 Palabra clave: ${usuarioViewModel.clave.value}")
                Text("🛒 Carga declarada: ${usuarioViewModel.carga.value}")

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate(Screen.Catalogo.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Entrar a la Cantina")
                }

                Button(
                    onClick = { navController.navigate(Screen.SubirProducto.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Publicar producto en la Cantina")
                }

                Button(
                    onClick = { navController.navigate(Screen.Carrito.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ir al carrito")
                }

                Button(
                    onClick = { navController.navigate(Screen.EditarPerfil.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Editar perfil")
                }

                // Botón cerrar sesión con confirmación
                OutlinedButton(
                    onClick = { showDialog = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cerrar sesión")
                }

                // Botón salir al inicio
                TextButton(
                    onClick = { navController.navigate(Screen.Home.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salir al inicio")
                }
            }
        }

        // 🔹 Ventana emergente de confirmación
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Confirmar cierre de sesión") },
                text = { Text("¿Estás seguro que quieres cerrar sesión?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            // Limpiar datos del usuario
                            usuarioViewModel.nombre.value = ""
                            usuarioViewModel.correo.value = ""
                            usuarioViewModel.clave.value = ""
                            usuarioViewModel.carga.value = ""
                            usuarioViewModel.imagenUri.value = null
                            // Navegar al login y limpiar stack
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Resumen.route) { inclusive = true }
                            }
                            showDialog = false
                        }
                    ) {
                        Text("Sí, cerrar sesión")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}
