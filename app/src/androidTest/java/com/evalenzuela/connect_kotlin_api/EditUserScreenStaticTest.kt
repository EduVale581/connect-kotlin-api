package com.evalenzuela.connect_kotlin_api
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.evalenzuela.connect_kotlin_api.data.model.User
import com.evalenzuela.connect_kotlin_api.ui.screen.EditUserScreen
import com.evalenzuela.connect_kotlin_api.viewmodel.UserViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
class FakeTestActivity : ComponentActivity()

@RunWith(AndroidJUnit4::class)
class EditUserScreenStaticTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val dummyViewModel = UserViewModel()
    @Test
    fun testCargaDatosUsuario() {

        composeTestRule.setContent {
            EditUserScreen(
                viewModel = dummyViewModel,
                userId = 1,
                onBack = {}
            )
        }

        composeTestRule.onNodeWithText("Nombre").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
    }
}
