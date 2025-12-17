package com.mambee73.merc_moseisleyapp

import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.navigation.compose.ComposeNavigator
import com.mambee73.merc_moseisleyapp.ui.screens.LoginScreen
import com.mambee73.merc_moseisleyapp.ui.viewmodel.UsuarioViewModel
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun login_correcto_muestra_resumen() {
        val usuarioVM = UsuarioViewModel()
        val navController = TestNavHostController(composeRule.activity).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }

        composeRule.setContent {
            NavHost(
                navController = navController,
                startDestination = "login"
            ) {
                composable("login") {
                    LoginScreen(usuarioViewModel = usuarioVM, navController = navController)
                }
                composable("resumen") {
                    Text("Resumen de usuario") // 👈 marcador de destino
                }
            }
        }

        // Simular ingreso de credenciales correctas
        composeRule.onNodeWithText("Usuario").performTextInput("Usuario1")
        composeRule.onNodeWithText("Palabra clave").performTextInput("mos123")
        composeRule.onNodeWithText("Entrar").performClick()

        // Validar que aparece el texto de la pantalla resumen
        composeRule.onNodeWithText("Resumen de usuario").assertIsDisplayed()
    }

    @Test
    fun login_incorrecto_muestra_error() {
        val usuarioVM = UsuarioViewModel()
        val navController = TestNavHostController(composeRule.activity).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }

        composeRule.setContent {
            NavHost(
                navController = navController,
                startDestination = "login"
            ) {
                composable("login") {
                    LoginScreen(usuarioViewModel = usuarioVM, navController = navController)
                }
                composable("resumen") {
                    Text("Resumen de usuario")
                }
            }
        }

        // Simular ingreso de credenciales incorrectas
        composeRule.onNodeWithText("Usuario").performTextInput("OtroUsuario")
        composeRule.onNodeWithText("Palabra clave").performTextInput("claveErronea")
        composeRule.onNodeWithText("Entrar").performClick()

        // Validar que aparece el mensaje de error
        composeRule.onNodeWithText("La clave es incorrecta.").assertIsDisplayed()
    }
}
