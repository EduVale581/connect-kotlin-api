package com.evalenzuela.connect_kotlin_api.repository


import android.content.Context
import android.net.Uri
import com.evalenzuela.connect_kotlin_api.data.model.User
import com.evalenzuela.connect_kotlin_api.data.remote.ApiClient
import com.evalenzuela.connect_kotlin_api.utils.toPlainTextRequestBody
import com.evalenzuela.connect_kotlin_api.utils.uriToMultipart

class UserRepository {

    private val api = ApiClient.service

    suspend fun getUsers(): List<User> = api.getUsers()

    suspend fun createUser(name: String, email: String): User =
        api.createUser(User(null, name, email))

    suspend fun uploadUserWithImage(context: Context, name: String, email: String, uri: Uri): User {
        val part = uriToMultipart(context, uri, "image")
        val reqName = name.toPlainTextRequestBody()
        val reqEmail = email.toPlainTextRequestBody()
        return api.uploadUser(reqName, reqEmail, part)
    }

    suspend fun updateUser(id: Int, name: String, email: String): User =
        api.updateUser(id, User(id, name, email))

    suspend fun deleteUser(id: Int) = api.deleteUser(id)
}
