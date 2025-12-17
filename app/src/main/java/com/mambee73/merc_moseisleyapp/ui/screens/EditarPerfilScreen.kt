package com.mambee73.merc_moseisleyapp.ui.screens

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import coil.compose.rememberAsyncImagePainter
import com.mambee73.merc_moseisleyapp.ui.viewmodel.UsuarioViewModel

// Pantalla para editar el perfil del usuario
@Composable
fun EditarPerfilScreen(navController: NavHostController, usuarioViewModel: UsuarioViewModel) {
    // Estados iniciales con los datos del usuario
    var nombre by remember { mutableStateOf(usuarioViewModel.nombre.value) }
    var correo by remember { mutableStateOf(usuarioViewModel.correo.value) }
    var clave by remember { mutableStateOf(usuarioViewModel.clave.value) }
    var carga by remember { mutableStateOf(usuarioViewModel.carga.value) }

    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }

    // Abrir galería
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { usuarioViewModel.imagenUri.value = it.toString() }
        capturedImage = null
    }

    // Abrir cámara
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
        capturedImage = bitmap
        usuarioViewModel.imagenUri.value = null
    }

    // Validaciones básicas
    val nombreValido = nombre.isNotBlank()
    val correoValido = correo.contains("@") && correo.contains(".")
    val claveValida = clave.length >= 6
    val cargaValida = carga.isNotBlank()

    val formularioValido = nombreValido && correoValido && claveValida && cargaValida

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Editar perfil", style = MaterialTheme.typography.headlineMedium)

        // Imagen de perfil (galería o cámara)
        when {
            capturedImage != null -> {
                Image(
                    painter = rememberAsyncImagePainter(capturedImage),
                    contentDescription = "Imagen de perfil",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            }
            usuarioViewModel.imagenUri.value != null -> {
                AsyncImage(
                    model = usuarioViewModel.imagenUri.value,
                    contentDescription = "Imagen de perfil",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            }
        }

        // Botones para cambiar imagen
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(onClick = { galleryLauncher.launch("image/*") }) {
                Text("Galería")
            }
            OutlinedButton(onClick = { cameraLauncher.launch(null) }) {
                Text("Cámara")
            }
        }

        // Campo Nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            isError = !nombreValido
        )
        if (!nombreValido) {
            Text("El nombre no puede estar vacío", color = MaterialTheme.colorScheme.error)
        }

        // Campo Correo
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth(),
            isError = !correoValido
        )
        if (!correoValido) {
            Text("Correo inválido", color = MaterialTheme.colorScheme.error)
        }

        // Campo Clave
        OutlinedTextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("Clave") },
            modifier = Modifier.fillMaxWidth(),
            isError = !claveValida
        )
        if (!claveValida) {
            Text("La clave debe tener al menos 6 caracteres", color = MaterialTheme.colorScheme.error)
        }

        // Campo Carga
        OutlinedTextField(
            value = carga,
            onValueChange = { carga = it },
            label = { Text("Carga declarada") },
            modifier = Modifier.fillMaxWidth(),
            isError = !cargaValida
        )
        if (!cargaValida) {
            Text("La carga no puede estar vacía", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Guardar cambios
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
