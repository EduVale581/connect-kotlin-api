package com.evalenzuela.connect_kotlin_api.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import com.evalenzuela.connect_kotlin_api.ui.screen.CreateUserScreen
import com.evalenzuela.connect_kotlin_api.ui.screen.EditUserScreen
import com.evalenzuela.connect_kotlin_api.ui.screen.UserListScreen
import com.evalenzuela.connect_kotlin_api.viewmodel.UserViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserApp() {
    val nav = rememberNavController()
    val vm: UserViewModel = viewModel()
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("App Metodos") },
        )
    }) { paddingValues ->
        NavHost(navController = nav, startDestination = "list", modifier = Modifier.padding(paddingValues)) {
            composable("list") {
                UserListScreen(vm, onCreate = { nav.navigate("create") }, onEdit = { id -> nav.navigate("edit/$id") })
            }
            composable("create") {
                CreateUserScreen(vm, onBack = { nav.popBackStack() })
            }
            composable("edit/{id}") { back ->
                val id = back.arguments?.getString("id")?.toIntOrNull() ?: return@composable
                EditUserScreen(vm, id, onBack = { nav.popBackStack() })
            }
        }
    }
}
