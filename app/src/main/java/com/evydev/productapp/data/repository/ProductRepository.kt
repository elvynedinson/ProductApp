package com.evydev.productapp.data.repository

import com.evydev.productapp.data.model.Product
import com.evydev.productapp.data.remote.ApiService

class ProductRepository(
    private val api: ApiService
){
    suspend fun getProducts(): List<Product> {
        return api.getProducts()
    }
}