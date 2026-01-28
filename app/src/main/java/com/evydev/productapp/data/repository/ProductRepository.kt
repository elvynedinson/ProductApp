package com.evydev.productapp.data.repository

import com.evydev.productapp.data.model.Product
import com.evydev.productapp.data.remote.ApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ApiService
){
    suspend fun getProducts(): List<Product> {
        return api.getProducts()
    }
}