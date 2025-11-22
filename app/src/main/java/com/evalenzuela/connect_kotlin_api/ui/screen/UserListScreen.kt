package com.evalenzuela.connect_kotlin_api.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.evalenzuela.connect_kotlin_api.data.model.User
import com.evalenzuela.connect_kotlin_api.viewmodel.UserViewModel

@Composable
fun UserListScreen(viewModel: UserViewModel, onCreate: () -> Unit, onEdit: (Int) -> Unit,  loadOnInit: Boolean = false) {
    val users by viewModel.users.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(Unit) { if (loadOnInit) viewModel.loadingTrue() else viewModel.fetchUsers() }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Usuarios", style = MaterialTheme.typography.headlineSmall)
            Button(onClick = onCreate) { Text("Crear") }
        }

        Spacer(Modifier.height(8.dp))

        if (loading) {
            CircularProgressIndicator(Modifier.testTag("loading_indicator"))
        } else {
            LazyColumn {
                items(users) { user ->
                    UserRow(user = user, onEdit = { user.id?.let { onEdit(it) } }, onDelete = {
                        user.id?.let { viewModel.deleteUser(it) }
                    })
                }
            }
        }
    }
}

@Composable
fun UserRow(user: User, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(Modifier
        .fillMaxWidth()
        .padding(6.dp)
        .clickable { onEdit() }) {
        Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.Start) {
            user.image_url?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp).padding(end = 12.dp)
                )
            }
            Column(Modifier.weight(1f)) {
                Text(user.name, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.testTag("user_name"))
                Text(user.email, style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
