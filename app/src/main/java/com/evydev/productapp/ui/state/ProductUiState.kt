package com.evydev.productapp.ui.state

import com.evydev.productapp.data.model.Product

sealed class ProductUiState {

    object Loading: ProductUiState()

    data class Success(val products: List<Product>): ProductUiState()
    data class Error(val message: String): ProductUiState()

}