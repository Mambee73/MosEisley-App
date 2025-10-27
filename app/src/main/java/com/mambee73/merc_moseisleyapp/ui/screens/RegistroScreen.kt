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
fun RegistroScreen(navController: NavHostController, usuarioViewModel: UsuarioViewModel) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var producto by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Identificación galáctica", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = producto,
            onValueChange = { producto = it },
            label = { Text("¿Qué cargas en tu nave (o bolsillo)?") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("Palabra clave") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                usuarioViewModel.nombre.value = nombre
                usuarioViewModel.correo.value = correo
                usuarioViewModel.clave.value = clave
                usuarioViewModel.carga.value = producto
                navController.navigate(Screen.Resumen.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver resumen")
        }
    }
}

