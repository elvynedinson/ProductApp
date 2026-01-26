package com.evydev.productapp.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import coil.compose.AsyncImage
import com.evydev.productapp.data.model.Product

@Composable
fun ProductItem(product: Product){

    Text("${product.title} - ${product.price}")

    AsyncImage(
        model = product.image,
        contentDescription = product.title
    )

}