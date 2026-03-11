package com.armatuhandroll.data

data class IngredientOption(
    val id: String,
    val name: String,
    val category: String,
    val extraPrice: Int = 0
)

data class Product(
    val id: String,
    val name: String,
    val basePrice: Int,
    val imageEmoji: String,
    val description: String,
    val ingredients: List<IngredientOption>
)

data class CartItem(
    val product: Product,
    val selectedIngredients: List<IngredientOption>,
    val quantity: Int = 1
) {
    val totalPrice: Int
        get() = (product.basePrice + selectedIngredients.sumOf { it.extraPrice }) * quantity
}

object ProductRepository {
    private val defaultIngredients = listOf(
        IngredientOption("base_1", "Arroz", "Base"),
        IngredientOption("protein_1", "Salmón", "Proteína", extraPrice = 800),
        IngredientOption("protein_2", "Camarón", "Proteína", extraPrice = 700),
        IngredientOption("extra_1", "Palta", "Extra", extraPrice = 500),
        IngredientOption("extra_2", "Queso crema", "Extra", extraPrice = 400),
        IngredientOption("sauce_1", "Salsa teriyaki", "Salsa", extraPrice = 300)
    )

    val products = listOf(
        Product(
            id = "handroll",
            name = "Handroll",
            basePrice = 5500,
            imageEmoji = "🍣",
            description = "Sabor clásico y fresco en formato handroll.",
            ingredients = defaultIngredients
        ),
        Product(
            id = "sushiburger",
            name = "SushiBurger",
            basePrice = 6900,
            imageEmoji = "🍔",
            description = "Una fusión crujiente con estilo urbano.",
            ingredients = defaultIngredients
        ),
        Product(
            id = "sushipleto",
            name = "SushiPleto",
            basePrice = 6400,
            imageEmoji = "🌯",
            description = "Cargado, intenso y perfecto para antojos.",
            ingredients = defaultIngredients
        ),
        Product(
            id = "gohan",
            name = "Gohan",
            basePrice = 5900,
            imageEmoji = "🍚",
            description = "Bowl cálido y reconfortante con toppings.",
            ingredients = defaultIngredients
        )
    )
}
