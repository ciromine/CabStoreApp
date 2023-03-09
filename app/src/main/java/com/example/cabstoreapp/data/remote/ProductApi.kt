package com.example.cabstoreapp.data.remote

import com.example.cabstoreapp.data.remote.model.ProductListResponse
import com.example.cabstoreapp.utils.Constants.Companion.baseUrl
import com.example.cabstoreapp.utils.Constants.Companion.productRoute
import retrofit2.http.GET

interface ProductApi {

    @GET(baseUrl+ productRoute)
    suspend fun getRecipes(): ProductListResponse
}
