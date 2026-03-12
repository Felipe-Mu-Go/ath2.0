package com.armatuhandroll.data

import androidx.annotation.DrawableRes
import com.example.myapplication.R

enum class IngredientCategory {
    PROTEIN,
    BASE,
    VEGETABLE
}

data class Product(
    val id: String,
    val name: String,
    val basePrice: Int,
    val description: String,
    @DrawableRes val heroImageRes: Int?,
    val supportsNoRice: Boolean = false
)

data class CartItem(
    val product: Product,
    val selectedProteins: List<String>,
    val selectedBases: List<String>,
    val selectedVegetables: List<String>,
    val noRice: Boolean = false,
    val quantity: Int = 1
) {
    private val proteinExtras = (selectedProteins.size - 1).coerceAtLeast(0) * 1000
    private val baseExtras = (selectedBases.size - 1).coerceAtLeast(0) * 1000
    private val vegetableExtras = (selectedVegetables.size - 1).coerceAtLeast(0) * 500

    val extrasPrice: Int
        get() = proteinExtras + baseExtras + vegetableExtras

    val totalPrice: Int
        get() = (product.basePrice + extrasPrice) * quantity
}

object ProductRepository {
    val proteins = listOf("Pollo", "Carne", "Camarón", "Kanikama", "Palmito", "Champiñón")
    val bases = listOf("Palta", "Pollo")
    val vegetables = listOf("Cebollín", "Ciboulette", "Choclo")

    val products = listOf(
        Product(
            id = "handroll",
            name = "Handroll",
            basePrice = 5500,
            description = "Tu handroll personalizado, fresco y equilibrado.",
            heroImageRes = R.drawable.handrroll
        ),
        Product(
            id = "sushiburger",
            name = "Sushiburger",
            basePrice = 6900,
            description = "Fusión crujiente con corazón de sushi.",
            heroImageRes = R.drawable.sushiburger
        ),
        Product(
            id = "sushipleto",
            name = "Sushipleto",
            basePrice = 6400,
            description = "Versión contundente para máximo antojo.",
            heroImageRes = null
        ),
        Product(
            id = "gohan",
            name = "Gohan",
            basePrice = 5900,
            description = "Bowl cálido con arroz base y toppings a elección.",
            heroImageRes = R.drawable.gohan,
            supportsNoRice = true
        )
    )
}
