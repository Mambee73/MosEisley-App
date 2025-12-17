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
import com.mambee73.merc_moseisleyapp.ui.viewmodel.UsuarioViewModel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect

@Composable
fun VerPerfilScreen(
    navController: NavHostController,
    usuarioViewModel: UsuarioViewModel
) {
    val usuarios by usuarioViewModel.usuarios.collectAsState()

    LaunchedEffect(Unit) {
        usuarioViewModel.fetchUsuarios()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Perfil del Usuario", style = MaterialTheme.typography.headlineMedium)

        if (usuarios.isNotEmpty()) {
            val ultimoUsuario = usuarios.last()
            AsyncImage(
                model = ultimoUsuario.imagenUri,
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Text("Nombre: ${ultimoUsuario.nombre}")
            Text("Correo: ${ultimoUsuario.correo}")
            Text("Carga: ${ultimoUsuario.carga ?: "Sin carga"}")
        } else {
            Text("No hay usuarios registrados todavía")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}
