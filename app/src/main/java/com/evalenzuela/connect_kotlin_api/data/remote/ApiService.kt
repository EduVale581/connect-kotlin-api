package com.evalenzuela.connect_kotlin_api.data.remote

import com.evalenzuela.connect_kotlin_api.data.model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @POST("users")
    suspend fun createUser(@Body user: User): User

    @Multipart
    @POST("users/upload")
    suspend fun uploadUser(
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part image: MultipartBody.Part
    ): User

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User): User

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}
