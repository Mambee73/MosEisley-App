package com.mambee73.merc_moseisleyapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.navigation.testing.TestNavHostController
import androidx.navigation.compose.ComposeNavigator
import com.mambee73.merc_moseisleyapp.ui.screens.ProductDetailScreen
import com.mambee73.merc_moseisleyapp.ui.viewmodels.ProductoViewModel
import org.junit.Rule
import org.junit.Test

class ProductDetailScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun muestra_placeholder_si_no_hay_imagen() {
        val productoVM = ProductoViewModel()
        val producto = productoVM.obtenerProductoPorId(1)!! // Poncho estilo Tatooine

        val navController = TestNavHostController(composeRule.activity).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }

        composeRule.setContent {
            ProductDetailScreen(
                navController = navController,
                productoViewModel = productoVM,
                productoId = producto.id
            )
        }

        // Validar que aparece el texto de placeholder
        composeRule.onNodeWithText("Sin imagen disponible").assertIsDisplayed()
    }
}
