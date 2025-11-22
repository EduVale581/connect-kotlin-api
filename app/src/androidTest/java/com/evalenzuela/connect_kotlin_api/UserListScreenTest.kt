package com.evalenzuela.connect_kotlin_api


import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.evalenzuela.connect_kotlin_api.ui.screen.UserListScreen
import com.evalenzuela.connect_kotlin_api.viewmodel.UserViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListHeaderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testVistaCrearUsuario() {
        var wasClicked = false

        // Aseguramos que el Activity ya esté lanzado y fijamos el contenido desde la Activity
        composeTestRule.setContent {
                UserListScreen(
                    viewModel = UserViewModel(),
                    onCreate = { wasClicked = true },
                    onEdit = {},
                    loadOnInit = true
                )
        }

        // Espera a que Compose procese
        composeTestRule.waitForIdle()

        // Verificar que aparece el título
        composeTestRule.onNodeWithText("Usuarios").assertIsDisplayed()

        // Verificar que aparece el botón
        composeTestRule.onNodeWithText("Crear").assertIsDisplayed()

        // Simular un click al botón
        composeTestRule.onNodeWithText("Crear").performClick()

        // Verificar que se llamó el callback
        assert(wasClicked)
    }
}

