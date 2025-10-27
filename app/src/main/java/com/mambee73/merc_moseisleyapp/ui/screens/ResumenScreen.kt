package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mambee73.merc_moseisleyapp.ui.viewmodels.UsuarioViewModel
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen
import com.mambee73.merc_moseisleyapp.ui.screens.SubirProductoScreen


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
            onClick = { navController.navigate(Screen.Home.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salir al inicio")
        }



    }
}
