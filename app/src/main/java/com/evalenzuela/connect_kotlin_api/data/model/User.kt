package com.evalenzuela.connect_kotlin_api.data.model

data class User(
    val id: Int?,
    val name: String,
    val email: String,
    val image_url: String? = null
)