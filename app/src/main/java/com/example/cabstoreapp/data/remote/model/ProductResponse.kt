package com.example.cabstoreapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double
)
