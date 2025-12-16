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
    // Variables para los campos del formulario
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false)