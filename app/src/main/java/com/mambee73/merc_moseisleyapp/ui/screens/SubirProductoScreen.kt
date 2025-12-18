package com.mambee73.merc_moseisleyapp.ui.screens

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.mambee73.merc_moseisleyapp.model.Producto
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen
import com.mambee73.merc_moseisleyapp.ui.viewmodel.ProductoViewModel
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubirProductoScreen(
    navController: NavHostController,
    productoViewModel: ProductoViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }
    var imageUri by remember { mutableStateOf<String?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { imageUri = it.toString(); capturedImage = null }
    }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
        capturedImage = bitmap
        imageUri = null
    }

    // Lista fija de categorías para la demo
    val categorias = listOf(
        "Ropa",
        "Libros/Cómics/Revistas",
        "Artículos Tecnológicos",
        "Cosas Nuevas/Cerradas",
        "Artículos de Segunda Mano/Curiosos",
        "Videojuegos/Holo-Juegos"
    )

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

        // Imagen seleccionada
        when {
            capturedImage != null -> {
                Image(
                    painter = rememberAsyncImagePainter(capturedImage),
                    contentDescription = "Imagen del producto",
                    modifier = Modifier.size(120.dp).clip(CircleShape)
                )
            }
            imageUri != null -> {
                AsyncImage(
                    model = imageUri,
                    contentDescription = "Imagen del producto",
                    modifier = Modifier.size(120.dp).clip(CircleShape)
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(onClick = { galleryLauncher.launch("image/*") }) { Text("Galería") }
            OutlinedButton(onClick = { cameraLauncher.launch(null) }) { Text("Cámara") }
        }

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            isError = !nombreValido
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth(),
            isError = !descripcionValida
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = !precioValido
        )

        // Dropdown de categorías corregido
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                value = categoria,
                onValueChange = {},
                readOnly = true,
                label = { Text("Categoría") },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .clickable { expanded = true }, // 👈 habilita el click
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                isError = !categoriaValida
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                categorias.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = { categoria = opcion; expanded = false }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val nuevoProducto = Producto(
                    id = 0,
                    nombre = nombre.trim(),
                    descripcion = descripcion.trim(),
                    precio = precio.toDoubleOrNull() ?: 0.0,
                    categoria = categoria,
                    imagenUri = imageUri
                )
                productoViewModel.addProducto(nuevoProducto)
                navController.navigate(Screen.Catalogo.route)
            },
            enabled = formularioValido,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Subir producto")
        }
    }
}

