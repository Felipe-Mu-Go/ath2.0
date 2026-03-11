package com.armatuhandroll.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.armatuhandroll.data.Product
import com.armatuhandroll.ui.components.AppBackground
import com.armatuhandroll.ui.components.AppTitle
import com.armatuhandroll.ui.components.ProductCard

@Composable
fun HomeScreen(
    products: List<Product>,
    onProductSelected: (Product) -> Unit,
    onCartClick: () -> Unit
) {
    AppBackground {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
            topBar = {
                TopAppBar(
                    title = { Text("Catálogo") },
                    actions = {
                        IconButton(onClick = onCartClick) {
                            BadgedBox(badge = { Badge {} }) {
                                Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrito")
                            }
                        }
                    }
                )
            }
        ) { innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
                    AppTitle(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Selecciona tu favorito",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(products) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductSelected(product) },
                        modifier = Modifier.height(220.dp)
                    )
                }
            }
        }
    }
}
