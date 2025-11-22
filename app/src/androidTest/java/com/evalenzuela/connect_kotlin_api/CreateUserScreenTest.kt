package com.evalenzuela.connect_kotlin_api
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.evalenzuela.connect_kotlin_api.ui.screen.CreateUserScreen
import com.evalenzuela.connect_kotlin_api.viewmodel.UserViewModel
import org.junit.Rule
import org.junit.Test

class CreateUserScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val dummyViewModel = UserViewModel()

    @Test
    fun testVistaCrearUusuario() {

        composeTestRule.setContent {
            CreateUserScreen(
                viewModel = dummyViewModel,
                onBack = {}
            )
        }

        // Verifica que los campos de texto existen
        composeTestRule.onNodeWithText("Nombre")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Email")
            .assertIsDisplayed()

        // Verifica botones estáticos
        composeTestRule.onNodeWithText("Elegir de Galería")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Tomar Foto")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Crear usuario sin imagen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Volver")
            .assertIsDisplayed()

        // Probar interacción simple en campos
        composeTestRule.onNodeWithText("Nombre")
            .performTextInput("Juan Pérez")

        composeTestRule.onNodeWithText("Email")
            .performTextInput("correo@example.com")

        // Asegurar que el texto ingresado se mantiene en pantalla
        composeTestRule.onNodeWithText("Juan Pérez")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("correo@example.com")
            .assertIsDisplayed()
    }
}
