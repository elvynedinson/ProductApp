package com.evydev.productapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evydev.productapp.data.remote.RetrofitClient.api
import com.evydev.productapp.ui.state.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel(){

    private val _state = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val state: StateFlow<ProductUiState> = _state

    init {
        loadProducts()
    }

     fun loadProducts() {

        viewModelScope.launch {
            _state.value = ProductUiState.Loading

            try {
                val products = api.getProducts()
                _state.value = ProductUiState.Success(products)

            }catch (e: Exception){
                _state.value = ProductUiState.Error("Error al cargar los Productos")
            }
        }
    }
}

