package com.mambee73.merc_moseisleyapp.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mambee73.merc_moseisleyapp.ui.viewmodels.UsuarioViewModel

@Composable
fun EditarPerfilScreen(navController: NavHostController, usuarioViewModel: UsuarioViewModel) {
    var nombre by remember { mutableStateOf(usuarioViewModel.nombre.value) }
    var correo by remember { mutableStateOf(usuarioViewModel.correo.value) }
    var clave by remember { mutableStateOf(usuarioViewModel.clave.value) }
    var carga by remember { mutableStateOf(usuarioViewModel.carga.value) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { usuarioViewModel.imagenUri.value = it.toString() }
    }

    val nombreValido = nombre.isNotBlank()
    val correoValido = correo.contains("@") && correo.contains(".")
    val claveValida = clave.length >= 6
    val cargaValida = carga.isNotBlank()

    val formularioValido = nombreValido && correoValido && claveValida && cargaValida

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Editar perfil", style = MaterialTheme.typography.headlineMedium)

        usuarioViewModel.imagenUri.value?.let { uri ->
            AsyncImage(
                model = uri,
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
        }

        Button(onClick = { launcher.launch("image/*") }) {
            Text("Cambiar imagen de perfil")
        }

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        if (!nombreValido && nombre.isNotBlank()) {
            Text("El nombre no puede estar vacío", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") })
        if (!correoValido && correo.isNotBlank()) {
            Text("Correo inválido", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(value = clave, onValueChange = { clave = it }, label = { Text("Clave") })
        if (!claveValida && clave.isNotBlank()) {
            Text("La clave debe tener al menos 6 caracteres", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(value = carga, onValueChange = { carga = it }, label = { Text("Carga declarada") })
        if (!cargaValida && carga.isNotBlank()) {
            Text("La carga no puede estar vacía", color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = {
                usuarioViewModel.nombre.value = nombre
                usuarioViewModel.correo.value = correo
                usuarioViewModel.clave.value = clave
                usuarioViewModel.carga.value = carga
                navController.popBackStack()
            },
            enabled = formularioValido,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar cambios")
        }
    }
}
