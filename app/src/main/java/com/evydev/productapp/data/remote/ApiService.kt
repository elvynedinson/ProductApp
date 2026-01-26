package com.evydev.productapp.data.remote

import com.evydev.productapp.data.model.Product
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getProducts(): List<Product>

}