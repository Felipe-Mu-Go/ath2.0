package com.armatuhandroll.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.armatuhandroll.data.CartItem
import com.armatuhandroll.data.IngredientOption
import com.armatuhandroll.data.Product
import com.armatuhandroll.ui.components.AppBackground
import com.armatuhandroll.ui.components.PrimaryActionButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCustomizationScreen(
    product: Product,
    onBack: () -> Unit,
    onAddToCart: (CartItem) -> Unit
) {
    val selected = remember { mutableStateListOf<IngredientOption>() }

    AppBackground {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
            topBar = {
                TopAppBar(
                    title = { Text(product.name) },
                    navigationIcon = { TextButton(onClick = onBack) { Text("Volver") } }
                )
            },
            bottomBar = {
                Column(modifier = Modifier.padding(16.dp)) {
                    PrimaryActionButton(
                        text = "Agregar al carrito",
                        onClick = { onAddToCart(CartItem(product = product, selectedIngredients = selected.toList())) }
                    )
                }
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Text(text = product.imageEmoji, style = MaterialTheme.typography.displaySmall)
                    Text(text = product.description, style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = "Base: $${product.basePrice}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                    )
                }
                items(product.ingredients) { ingredient ->
                    IngredientRow(
                        ingredient = ingredient,
                        selected = selected.contains(ingredient),
                        onSelectionChanged = { checked ->
                            if (checked) selected.add(ingredient) else selected.remove(ingredient)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun IngredientRow(
    ingredient: IngredientOption,
    selected: Boolean,
    onSelectionChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = ingredient.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = ingredient.category, style = MaterialTheme.typography.bodySmall)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (ingredient.extraPrice > 0) {
                Text(text = "+$${ingredient.extraPrice}")
            }
            Checkbox(checked = selected, onCheckedChange = onSelectionChanged)
        }
    }
}
