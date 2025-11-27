package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen
import com.mambee73.merc_moseisleyapp.ui.viewmodels.UsuarioViewModel

@Composable
fun RegistroScreen(navController: NavHostController, usuarioViewModel: UsuarioViewModel) {
    var usuario by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var confirmarClave by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var producto by remember { mutableStateOf("") }

    var showErrors by remember { mutableStateOf(false) }

    // Validaciones
    val usuarioValido = usuario.isNotBlank()
    val claveValida = clave.length >= 6
    val clavesCoinciden = clave == confirmarClave
    val correoValido = correo.contains("@") && correo.contains(".")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center // 🔹 Centra la tarjeta en la pantalla
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(0.9f), // 🔹 Ocupa 90% del ancho
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
                Text("Tarjeta de Identificación Galáctica", style = MaterialTheme.typography.headlineMedium)

                // Usuario
                OutlinedTextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    label = { Text("Usuario") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (showErrors && !usuarioValido) {
                    Text("El usuario no puede estar vacío", color = MaterialTheme.colorScheme.error)
                }

                // Contraseña
                OutlinedTextField(
                    value = clave,
                    onValueChange = { clave = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (showErrors && !claveValida) {
                    Text("La contraseña debe tener al menos 6 caracteres", color = MaterialTheme.colorScheme.error)
                }

                // Confirmar contraseña
                OutlinedTextField(
                    value = confirmarClave,
                    onValueChange = { confirmarClave = it },
                    label = { Text("Confirmar contraseña") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (showErrors && !clavesCoinciden) {
                    Text("Las contraseñas no coinciden", color = MaterialTheme.colorScheme.error)
                }

                // Correo electrónico
                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (showErrors && !correoValido) {
                    Text("Correo inválido", color = MaterialTheme.colorScheme.error)
                }

                // Producto (opcional)
                OutlinedTextField(
                    value = producto,
                    onValueChange = { producto = it },
                    label = { Text("¿Qué cargas en tu nave (opcional)?") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Botón Ver resumen
                Button(
                    onClick = {
                        showErrors = true
                        if (usuarioValido && claveValida && clavesCoinciden && correoValido) {
                            usuarioViewModel.nombre.value = usuario
                            usuarioViewModel.clave.value = clave
                            usuarioViewModel.correo.value = correo
                            usuarioViewModel.carga.value = producto
                            navController.navigate(Screen.Resumen.route)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Entra al mercado")
                }
            }
        }
    }
}
