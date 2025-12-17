package com.mambee73.merc_moseisleyapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.navigation.testing.TestNavHostController
import androidx.navigation.compose.ComposeNavigator
import com.mambee73.merc_moseisleyapp.ui.screens.CarritoScreen
import com.mambee73.merc_moseisleyapp.ui.viewmodel.CarritoViewModel
import org.junit.Rule
import org.junit.Test

class CarritoScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun titulo_carrito_debe_aparecer() {
        val carritoVM = CarritoViewModel()

        val navController = TestNavHostController(composeRule.activity).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }

        composeRule.setContent {
            CarritoScreen(
                navController = navController,
                carritoViewModel = carritoVM
            )
        }

        // Validar que aparece el título del carrito
        composeRule.onNodeWithText("Tu carrito").assertIsDisplayed()
    }
}
