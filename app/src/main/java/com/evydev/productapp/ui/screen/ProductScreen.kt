package com.evydev.productapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.evydev.productapp.data.model.Product
import com.evydev.productapp.ui.components.ProductItem
import com.evydev.productapp.ui.state.ProductUiState
import com.evydev.productapp.ui.viewmodel.ProductViewModel

@Composable
fun ProductScreen(
    viewModel: ProductViewModel = viewModel()
){
    val state by viewModel.state.collectAsState()

    when(state){
        is ProductUiState.Loading ->{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        is ProductUiState.Success -> {
            val products = (state as ProductUiState.Success).products
            ProductList(products)
        }

        is ProductUiState.Error -> {
            val message = (state as ProductUiState.Error).message
            Text("Error $message")
        }
    }
}

@Composable
fun ProductList(products: List<Product>) {

    LazyColumn {
        items(products) { product ->
            ProductItem(product)
        }
    }
}
