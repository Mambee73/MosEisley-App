package com.mambee73.merc_moseisleyapp.ui.screens

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.mambee73.merc_moseisleyapp.model.Producto
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen
import com.mambee73.merc_moseisleyapp.ui.viewmodels.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubirProductoScreen(navController: NavHostController, productoViewModel: ProductoViewModel) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }
    var imageUri by remember { mutableStateOf<String?>(null) }

    // 🔹 Launcher para galería
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { imageUri = it.toString(); capturedImage = null }
    }

    // 🔹 Launcher para cámara
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
        capturedImage = bitmap
        imageUri = null
    }

    val categorias = listOf(
        "Ropa",
        "Libros/Cómics/Revistas",
        "Artículos Tecnológicos",
        "Cosas Nuevas/Cerradas",
        "Artículos de Segunda Mano/Curiosos",
        "Videojuegos/Holo-Juegos"
    )

    // Validaciones
    val nombreValido = nombre.isNotBlank()
    val descripcionValida = descripcion.isNotBlank()
    val precioValido = precio.toDoubleOrNull()?.let { it > 0 } ?: false
    val categoriaValida = categoria.isNotBlank()
    val imagenValida = capturedImage != null || imageUri != null

    val formularioValido = nombreValido && descripcionValida && precioValido && categoriaValida && imagenValida

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Subir producto", style = MaterialTheme.typography.headlineMedium)

        // Imagen del producto
        when {
            capturedImage != null -> {
                Image(
                    painter = rememberAsyncImagePainter(capturedImage),
                    contentDescription = "Imagen del producto",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            }
            imageUri != null -> {
                AsyncImage(
                    model = imageUri,
                    contentDescription = "Imagen del producto",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            }
        }

        // Botones para imagen
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(onClick = { galleryLauncher.launch("image/*") }) {
                Text("Galería")
            }
            OutlinedButton(onClick = { cameraLauncher.launch(null) }) {
                Text("Cámara")
            }
        }
        if (!imagenValida) {
            Text("Debes agregar una imagen del producto", color = MaterialTheme.colorScheme.error)
        }

        // Nombre
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

        // Descripción
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth(),
            isError = !descripcionValida
        )
        if (!descripcionValida) {
            Text("La descripción no puede estar vacía", color = MaterialTheme.colorScheme.error)
        }

        // Precio
        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth(),
            isError = !precioValido
        )
        if (!precioValido) {
            Text("Precio inválido. Debe ser un número positivo.", color = MaterialTheme.colorScheme.error)
        }

        // Categoría
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = categoria,
                onValueChange = {},
                readOnly = true,
                label = { Text("Categoría") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                isError = !categoriaValida
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categorias.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            categoria = opcion
                            expanded = false
                        }
                    )
                }
            }
        }
        if (!categoriaValida) {
            Text("Debes seleccionar una categoría", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón publicar
        Button(
            onClick = {
                val nuevoProducto = Producto(
                    id = 0, // se reemplaza en el ViewModel
                    nombre = nombre,
                    descripcion = descripcion,
                    precio = precio.toDouble(),
                    categoria = categoria,
                    imagenUri = imageUri // si viene de galería, si no queda null
                )
                productoViewModel.agregarProducto(nuevoProducto)
                navController.navigate(Screen.Catalogo.route)
            },
            enabled = formularioValido,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Subir producto")
        }
    }
}

