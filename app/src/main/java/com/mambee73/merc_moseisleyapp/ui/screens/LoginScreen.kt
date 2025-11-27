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
fun LoginScreen(navController: NavHostController, usuarioViewModel: UsuarioViewModel) {
    var usuario by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var showClaveError by remember { mutableStateOf(false) }

    // Simulación de usuario válido (esto luego se reemplaza con API REST)
    val usuarioEsperado = "Usuario1"
    val claveEsperada = "mos123"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Acceso a la cantina", style = MaterialTheme.typography.headlineMedium)

                // Campo Usuario
                OutlinedTextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    label = { Text("Usuario") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Campo Clave
                OutlinedTextField(
                    value = clave,
                    onValueChange = { clave = it },
                    label = { Text("Palabra clave") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Botón Entrar
                Button(
                    onClick = {
                        if (usuario == usuarioEsperado && clave == claveEsperada) {
                            usuarioViewModel.nombre.value = usuario
                            usuarioViewModel.clave.value = clave
                            showDialog = false
                            showClaveError = false
                            navController.navigate(Screen.Resumen.route)
                        } else {
                            if (usuario != usuarioEsperado) {
                                showDialog = true // 🔹 Mostrar ventana emergente
                            } else if (clave != claveEsperada) {
                                showClaveError = true
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Entrar")
                }

                // Error de clave incorrecta (solo texto debajo del botón)
                if (showClaveError) {
                    Text(
                        text = "La clave es incorrecta.",
                        color = MaterialTheme.colorScheme.error
                    )
                }

                // Botón para ir a Registro (siempre visible)
                TextButton(
                    onClick = { navController.navigate(Screen.Registro.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("¿Eres nuevo? Identifícate aquí")
                }
            }
        }

        //Ventana emergente cuando el usuario no existe
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("¡Es una trampa!") },
                text = { Text("Usuario no encontrado… debe estar huyendo de alguien. Pero por unos créditos, puedo dejarlo pasar igual.") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                            navController.navigate(Screen.Registro.route)
                        }
                    ) {
                        Text("Entregar Creditos")
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

