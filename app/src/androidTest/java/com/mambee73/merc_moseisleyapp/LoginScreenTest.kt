package com.mambee73.merc_moseisleyapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.navigation.testing.TestNavHostController
import androidx.navigation.compose.ComposeNavigator
import com.mambee73.merc_moseisleyapp.ui.screens.LoginScreen
import com.mambee73.merc_moseisleyapp.ui.viewmodels.UsuarioViewModel
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun muestra_error_si_login_incorrecto() {
        val usuarioVM = UsuarioViewModel()

        val navController = TestNavHostController(composeRule.activity).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }

        composeRule.setContent {
            LoginScreen(usuarioViewModel = usuarioVM, navController = navController)
        }

        // Simular ingreso de credenciales incorrectas
        composeRule.onNodeWithText("Usuario").performTextInput("OtroUsuario")
        composeRule.onNodeWithText("Clave").performTextInput("claveErronea")
        composeRule.onNodeWithText("Ingresar").performClick()

        // Validar que aparece el mensaje de error
        composeRule.onNodeWithText("Error: credenciales inválidas").assertIsDisplayed()
    }
}
