package com.mambee73.merc_moseisleyapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import com.mambee73.merc_moseisleyapp.ui.screens.CatalogoScreen
import com.mambee73.merc_moseisleyapp.ui.viewmodels.ProductoViewModel
import com.mambee73.merc_moseisleyapp.ui.viewmodels.CarritoViewModel
import org.junit.Rule
import org.junit.Test

class CatalogoScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun titulo_catalogo_debe_aparecer() {
        val productoVM = ProductoViewModel()
        val carritoVM = CarritoViewModel()

        composeRule.setContent {
            CatalogoScreen(
                navController = androidx.navigation.testing.TestNavHostController(composeRule.activity),
                productoViewModel = productoVM,
                carritoViewModel = carritoVM
            )
        }

        // Validar que el título aparece
        composeRule.onNodeWithText("Catálogo Mos Eisley").assertIsDisplayed()
    }
}
