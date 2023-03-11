package com.example.cabstoreapp.data.source

import com.example.cabstoreapp.data.remote.model.ProductListResponse

interface ProductRemote {

    suspend fun getProductList(): ProductListResponse
}
