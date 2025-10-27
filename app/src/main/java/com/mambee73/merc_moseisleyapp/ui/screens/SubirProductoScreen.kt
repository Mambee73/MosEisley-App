package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mambee73.merc_moseisleyapp.model.Producto
import com.mambee73.merc_moseisleyapp.ui.viewmodels.ProductoViewModel
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen

@Composable
fun SubirProductoScreen(navController: NavHostController, productoViewModel: ProductoViewModel) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Subir producto a la cantina", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio en créditos") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoría") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val nuevoProducto = Producto(
                    nombre = nombre,
                    descripcion = descripcion,
                    precio = precio.toDoubleOrNull() ?: 0.0,
                    categoria = categoria
                )
                productoViewModel.agregarProducto(nuevoProducto)
                navController.navigate(Screen.Catalogo.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar producto")
        }
    }
}
