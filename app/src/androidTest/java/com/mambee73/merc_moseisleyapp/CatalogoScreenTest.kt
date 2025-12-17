package com.mambee73.merc_moseisleyapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.navigation.testing.TestNavHostController
import androidx.navigation.compose.ComposeNavigator
import com.mambee73.merc_moseisleyapp.ui.screens.CatalogoScreen
import com.mambee73.merc_moseisleyapp.ui.viewmodel.ProductoViewModel   // 👈 corregido (singular)
import com.mambee73.merc_moseisleyapp.ui.viewmodel.CarritoViewModel   // 👈 corregido (singular)
import org.junit.Rule
import org.junit.Test

class CatalogoScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun titulo_catalogo_debe_aparecer() {
        val productoVM = ProductoViewModel()
        val carritoVM = CarritoViewModel()

        val navController = TestNavHostController(composeRule.activity).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }

        composeRule.setContent {
            CatalogoScreen(
                navController = navController,
                productoViewModel = productoVM,
                carritoViewModel = carritoVM
            )
        }

        // Validar que el título aparece
        composeRule.onNodeWithText("Catálogo Mos Eisley").assertIsDisplayed()
    }
}
