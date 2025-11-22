package com.evalenzuela.connect_kotlin_api.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.InputStream

fun uriToMultipart(context: Context, uri: Uri, partName: String = "image", fileName: String? = null): MultipartBody.Part {
    val resolver: ContentResolver = context.contentResolver
    val inputStream: InputStream? = resolver.openInputStream(uri)
    val bytes = inputStream?.readBytes() ?: ByteArray(0)
    val mime = resolver.getType(uri) ?: "image/*"
    val finalFileName = fileName ?: "upload_${System.currentTimeMillis()}.jpg"

    val requestFile = RequestBody.create(mime.toMediaTypeOrNull(), bytes)
    return MultipartBody.Part.createFormData(partName, finalFileName, requestFile)
}

fun String.toPlainTextRequestBody(): RequestBody =
    this.toRequestBody("text/plain".toMediaType())