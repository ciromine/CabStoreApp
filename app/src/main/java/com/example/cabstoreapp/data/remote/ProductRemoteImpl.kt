package com.example.cabstoreapp.data.remote

import com.example.cabstoreapp.data.remote.model.ProductListResponse
import com.example.cabstoreapp.data.source.ProductRemote
import javax.inject.Inject

class ProductRemoteImpl @Inject constructor(private val restApi: ProductApi) :
    ProductRemote {

    override suspend fun getProductList(): ProductListResponse = restApi.getRecipes()
}
