package com.mambee73.merc_moseisleyapp.ui

// Importamos lo necesario para Compose y navegación
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen
import com.mambee73.merc_moseisleyapp.ui.viewmodel.UsuarioViewModel

@Composable
fun RegistroScreen(
    navController: NavHostController,
    usuarioViewModel: UsuarioViewModel = viewModel()
) {
    // Variables para guardar lo que escribe el usuario
    var usuario by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var confirmarClave by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var producto by remember { mutableStateOf("") }

    // Para mostrar errores si algo está mal
    var showErrors by remember { mutableStateOf(false) }

    // Guardamos la imagen seleccionada
    var imagenUri by remember { mutableStateOf<Uri?>(null) }

    // Esto abre la galería del sistema para elegir una imagen
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            imagenUri = uri
            usuarioViewModel.imagenUri.value = uri.toString() // guardamos en el ViewModel
        }
    }

    // Validaciones básicas
    val usuarioValido = usuario.isNotBlank()
    val claveValida = clave.length >= 6
    val clavesCoinciden = clave == confirmarClave
    val correoValido = correo.contains("@") && correo.contains(".")

    // Layout principal
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
                Text("Tarjeta de Identificación Galáctica", style = MaterialTheme.typography.headlineMedium)

                // Imagen de perfil (se muestra si el usuario eligió una)
                AsyncImage(
                    model = imagenUri,
                    contentDescription = "Imagen de perfil",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )

                // Botón para abrir la galería
                Button(onClick = { launcher.launch("image/*") }) {
                    Text("Seleccionar imagen de perfil")
                }

                // Campo Usuario
                OutlinedTextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    label = { Text("Usuario") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (showErrors && !usuarioValido) {
                    Text("El usuario no puede estar vacío", color = MaterialTheme.colorScheme.error)
                }

                // Campo Contraseña
                OutlinedTextField(
                    value = clave,
                    onValueChange = { clave = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (showErrors && !claveValida) {
                    Text("La contraseña debe tener al menos 6 caracteres", color = MaterialTheme.colorScheme.error)
                }

                // Campo Confirmar contraseña
                OutlinedTextField(
                    value = confirmarClave,
                    onValueChange = { confirmarClave = it },
                    label = { Text("Confirmar contraseña") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (showErrors && !clavesCoinciden) {
                    Text("Las contraseñas no coinciden", color = MaterialTheme.colorScheme.error)
                }

                // Campo Correo
                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (showErrors && !correoValido) {
                    Text("Correo inválido", color = MaterialTheme.colorScheme.error)
                }

                // Campo Producto (opcional)
                OutlinedTextField(
                    value = producto,
                    onValueChange = { producto = it },
                    label = { Text("¿Qué cargas en tu nave (opcional)?") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Botón Registrar
                Button(
                    onClick = {
                        showErrors = true
                        if (usuarioValido && claveValida && clavesCoinciden && correoValido) {
                            // Guardamos datos en el ViewModel
                            usuarioViewModel.nombre.value = usuario
                            usuarioViewModel.clave.value = clave
                            usuarioViewModel.correo.value = correo
                            usuarioViewModel.carga.value = producto

                            // Creamos el objeto Usuario
                            val nuevoUsuario = usuarioViewModel.getUsuarioActual()

                            // Enviamos al backend con Retrofit
                            usuarioViewModel.addUsuario(nuevoUsuario)

                            // Navegamos al resumen
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
