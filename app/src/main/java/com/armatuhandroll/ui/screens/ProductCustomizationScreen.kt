package com.armatuhandroll.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.armatuhandroll.data.CartItem
import com.armatuhandroll.data.Product
import com.armatuhandroll.data.ProductRepository
import com.armatuhandroll.ui.components.AppBackground
import com.armatuhandroll.ui.components.PrimaryActionButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCustomizationScreen(
    product: Product,
    onBack: () -> Unit,
    onAddToCart: (CartItem) -> Unit
) {
    val selectedProteins = remember { mutableStateListOf<String>() }
    val selectedBases = remember { mutableStateListOf<String>() }
    val selectedVegetables = remember { mutableStateListOf<String>() }
    var noRice by remember { mutableStateOf(false) }

    val extrasPrice =
        (selectedProteins.size - 1).coerceAtLeast(0) * 1000 +
            (selectedBases.size - 1).coerceAtLeast(0) * 1000 +
            (selectedVegetables.size - 1).coerceAtLeast(0) * 500
    val totalPrice = product.basePrice + extrasPrice

    AppBackground(heroImageRes = product.heroImageRes) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
            topBar = {
                TopAppBar(
                    title = { Text(product.name) },
                    navigationIcon = { TextButton(onClick = onBack) { Text("Volver") } }
                )
            },
            bottomBar = {
                Surface(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "Total: $${totalPrice}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        PrimaryActionButton(
                            text = "Agregar al carrito",
                            onClick = {
                                onAddToCart(
                                    CartItem(
                                        product = product,
                                        selectedProteins = selectedProteins.toList(),
                                        selectedBases = selectedBases.toList(),
                                        selectedVegetables = selectedVegetables.toList(),
                                        noRice = noRice
                                    )
                                )
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = product.description, style = MaterialTheme.typography.bodyLarge)
                            Text(
                                text = "Incluye 1 proteína + 1 base + 1 vegetal",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 6.dp)
                            )
                            Text(
                                text = "Extras: proteína/base +$1000 · vegetal +$500",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                if (product.supportsNoRice) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Base fija: arroz", fontWeight = FontWeight.SemiBold)
                                Text("Puedes marcar Sin arroz para retirarlo", style = MaterialTheme.typography.bodySmall)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("Sin arroz")
                                Checkbox(checked = noRice, onCheckedChange = { noRice = it })
                            }
                        }
                    }
                }

                item {
                    IngredientSelectorSection(
                        title = "Proteína",
                        subtitle = "La primera no suma costo",
                        options = ProductRepository.proteins,
                        selectedOptions = selectedProteins,
                        extraPrice = 1000
                    )
                }
                item {
                    IngredientSelectorSection(
                        title = "Base",
                        subtitle = "La primera no suma costo",
                        options = ProductRepository.bases,
                        selectedOptions = selectedBases,
                        extraPrice = 1000
                    )
                }
                item {
                    IngredientSelectorSection(
                        title = "Vegetal",
                        subtitle = "El primero no suma costo",
                        options = ProductRepository.vegetables,
                        selectedOptions = selectedVegetables,
                        extraPrice = 500
                    )
                }

                item {
                    OrderPreviewCard(
                        selectedProteins = selectedProteins,
                        selectedBases = selectedBases,
                        selectedVegetables = selectedVegetables,
                        noRice = noRice,
                        showNoRice = product.supportsNoRice,
                        extrasPrice = extrasPrice
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun IngredientSelectorSection(
    title: String,
    subtitle: String,
    options: List<String>,
    selectedOptions: MutableList<String>,
    extraPrice: Int
) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f))) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                options.forEach { option ->
                    val isSelected = selectedOptions.contains(option)
                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            if (isSelected) selectedOptions.remove(option) else selectedOptions.add(option)
                        },
                        label = { Text(option) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                }
            }
            AssistChip(
                onClick = {},
                enabled = false,
                label = { Text("Extras desde la segunda opción: +$${extraPrice}") },
                colors = AssistChipDefaults.assistChipColors(disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer)
            )
        }
    }
}

@Composable
private fun OrderPreviewCard(
    selectedProteins: List<String>,
    selectedBases: List<String>,
    selectedVegetables: List<String>,
    noRice: Boolean,
    showNoRice: Boolean,
    extrasPrice: Int
) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f))) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Resumen del pedido", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text("Proteína: ${selectedProteins.joinToString().ifBlank { "Sin selección" }}")
            Text("Base: ${selectedBases.joinToString().ifBlank { "Sin selección" }}")
            Text("Vegetal: ${selectedVegetables.joinToString().ifBlank { "Sin selección" }}")
            if (showNoRice) {
                Text(if (noRice) "Arroz: Sin arroz" else "Arroz: Incluido")
            }
            Text("Subtotal extras: $${extrasPrice}", fontWeight = FontWeight.SemiBold)
        }
    }
}
