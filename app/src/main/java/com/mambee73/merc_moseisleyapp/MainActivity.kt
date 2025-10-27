package com.mambee73.merc_moseisleyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mambee73.merc_moseisleyapp.ui.theme.MercMosEisleyAppTheme
import com.mambee73.merc_moseisleyapp.ui.screens.*
import com.mambee73.merc_moseisleyapp.ui.navigation.AppNavigation

import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mambee73.merc_moseisleyapp.ui.viewmodels.UsuarioViewModel





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MercMosEisleyAppTheme {
                val navController = rememberNavController()
                val usuarioViewModel = viewModel<UsuarioViewModel>()
                AppNavigation(navController, usuarioViewModel)
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MercMosEisleyAppTheme {
        Greeting("Android")
    }
}

