package com.evalenzuela.connect_kotlin_api.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.evalenzuela.connect_kotlin_api.viewmodel.UserViewModel


@Composable
fun EditUserScreen(
    viewModel: UserViewModel,
    userId: Int?,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // preload user data from viewModel state
    val users by viewModel.users.collectAsState()
    LaunchedEffect(userId) {
        val u = users.find { it.id == userId }
        u?.let {
            name = it.name
            email = it.email
        }
    }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            if (userId !== null) {
                viewModel.updateUser(userId, name, email)
                onBack()
            }
        }) {
            Text("Guardar")
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = onBack) { Text("Cancelar") }
    }
}
