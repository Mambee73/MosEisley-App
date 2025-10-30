package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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

    val formularioValido = nombreValido && descripcionValida && precioValido && categoriaValida

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Subir producto", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        if (!nombreValido && nombre.isNotBlank()) {
            Text("El nombre no puede estar vacío", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") })
        if (!descripcionValida && descripcion.isNotBlank()) {
            Text("La descripción no puede estar vacía", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") })
        if (!precioValido && precio.isNotBlank()) {
            Text("Precio inválido. Debe ser un número positivo.", color = MaterialTheme.colorScheme.error)
        }

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
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
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

        Button(
            onClick = {
                val nuevoProducto = Producto(nombre, descripcion, precio.toDouble(), categoria)
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
