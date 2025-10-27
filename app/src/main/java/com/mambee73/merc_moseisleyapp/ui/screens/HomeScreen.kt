package com.mambee73.merc_moseisleyapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mambee73.merc_moseisleyapp.R
import com.mambee73.merc_moseisleyapp.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavHostController) {
    var mostrarDialogo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Imagen decorativa (opcional)
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Banner Mos Eisley",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Bienvenido a Mos Eisley",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Aquí encontrarás artefactos únicos, tecnología olvidada y ofertas que no verás en Coruscant.",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(onClick = { mostrarDialogo = true }) {
            Text("Explorar rarezas interplanetarias")
        }

        if (mostrarDialogo) {
            AlertDialog(
                onDismissRequest = { mostrarDialogo = false },
                title = { Text("¡Alto ahí!") },
                text = { Text("Antes de entrar a la cantina, necesitamos saber quién eres.") },
                confirmButton = {
                    Button(onClick = {
                        mostrarDialogo = false
                        navController.navigate(Screen.Login.route)
                    }) {
                        Text("Identificarse")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { mostrarDialogo = false }) {
                        Text("Mejor no...")
                    }
                }
            )
        }
    }
}
