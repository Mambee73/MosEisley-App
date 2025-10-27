package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen
import com.mambee73.merc_moseisleyapp.ui.viewmodels.UsuarioViewModel

@Composable
fun LoginScreen(navController: NavHostController, usuarioViewModel: UsuarioViewModel) {
    var usuario by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    val usuarioValido = "Usuario1"
    val claveValida = "mos123"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Acceso a la cantina", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth(),
            isError = error
        )

        OutlinedTextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("Palabra clave") },
            modifier = Modifier.fillMaxWidth(),
            isError = error
        )

        if (error) {
            Text(
                text = "Usuario o clave incorrectos.",
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {
                if (usuario == usuarioValido && clave == claveValida) {
                    usuarioViewModel.nombre.value = usuario
                    usuarioViewModel.clave.value = clave
                    error = false
                    navController.navigate(Screen.Resumen.route)
                } else {
                    error = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }

        TextButton(
            onClick = {
                navController.navigate(Screen.Registro.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¿Eres nuevo? Identifícate aquí")
        }
    }
}
