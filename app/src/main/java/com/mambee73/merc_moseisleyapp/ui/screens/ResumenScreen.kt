package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mambee73.merc_moseisleyapp.ui.viewmodels.UsuarioViewModel

@Composable
fun ResumenScreen(navController: NavHostController, usuarioViewModel: UsuarioViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Ticket De Entrada", style = MaterialTheme.typography.headlineMedium)

        Text("Nombre: ${usuarioViewModel.nombre.value}")
        Text("Correo: ${usuarioViewModel.correo.value}")
        Text("Palabra clave: ${usuarioViewModel.clave.value}")
        Text("Carga declarada: ${usuarioViewModel.carga.value}")

        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al inicio")
        }
    }
}
