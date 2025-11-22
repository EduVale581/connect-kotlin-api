package com.evalenzuela.connect_kotlin_api.ui.screen

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import java.io.File
import androidx.core.content.FileProvider
import com.evalenzuela.connect_kotlin_api.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CreateUserScreen(viewModel: UserViewModel, onBack: () -> Unit) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var selectedUri by remember { mutableStateOf<Uri?>(null) }

    // Gallery (single image)
    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { selectedUri = it }
    }

    // Camera
    val cameraUri = remember { mutableStateOf<Uri?>(null) }
    val takePicture = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            cameraUri.value?.let { selectedUri = it }
        }
    }

    fun createImageFile(context: Context): Uri {
        val time = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val file = File(context.cacheDir, "photo_$time.jpg")
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        cameraUri.value = uri
        return uri
    }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(Modifier.height(12.dp))

        Row {
            Button(onClick = { galleryLauncher.launch("image/*") }) {
                Text("Elegir de Galería")
            }
            Spacer(Modifier.width(8.dp))

            Button(onClick = {
                val uri = createImageFile(context)
                takePicture.launch(uri)
            }) {
                Text("Tomar Foto")
            }
        }

        Spacer(Modifier.height(12.dp))

        // Preview image
        selectedUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.uploadUserWithImage(context, name, email, uri)
                }
            ) {
                Text("Enviar usuario + imagen")
            }
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = { viewModel.createUser(name, email) }) {
            Text("Crear usuario sin imagen")
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = onBack) {
            Text("Volver")
        }
    }
}
