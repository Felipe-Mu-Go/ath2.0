package com.armatuhandroll.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.armatuhandroll.data.CartItem
import com.armatuhandroll.data.ProductRepository
import com.armatuhandroll.ui.screens.CartScreen
import com.armatuhandroll.ui.screens.HomeScreen
import com.armatuhandroll.ui.screens.OrderSummaryScreen
import com.armatuhandroll.ui.screens.ProductCustomizationScreen
import com.armatuhandroll.ui.screens.SplashScreen

object AppDestinations {
    const val Splash = "splash"
    const val Home = "home"
    const val ProductCustomization = "product_customization"
    const val Cart = "cart"
    const val OrderSummary = "order_summary"
}

@Composable
fun AppNavHost(cartItems: androidx.compose.runtime.snapshots.SnapshotStateList<CartItem>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppDestinations.Splash) {
        composable(AppDestinations.Splash) {
            SplashScreen(onNavigateToHome = {
                navController.navigate(AppDestinations.Home) {
                    popUpTo(AppDestinations.Splash) { inclusive = true }
                }
            })
        }

        composable(AppDestinations.Home) {
            HomeScreen(
                products = ProductRepository.products,
                onProductSelected = { product ->
                    navController.navigate("${AppDestinations.ProductCustomization}/${product.id}")
                },
                onCartClick = { navController.navigate(AppDestinations.Cart) }
            )
        }

        composable(
            route = "${AppDestinations.ProductCustomization}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
            val product = ProductRepository.products.firstOrNull { it.id == productId } ?: return@composable
            ProductCustomizationScreen(
                product = product,
                onBack = { navController.popBackStack() },
                onAddToCart = {
                    cartItems.add(it)
                    navController.navigate(AppDestinations.Cart)
                }
            )
        }

        composable(AppDestinations.Cart) {
            CartScreen(
                cartItems = cartItems,
                onBack = { navController.popBackStack() },
                onCheckout = { navController.navigate(AppDestinations.OrderSummary) }
            )
        }

        composable(AppDestinations.OrderSummary) {
            OrderSummaryScreen(
                cartItems = cartItems,
                onBackHome = {
                    cartItems.clear()
                    navController.navigate(AppDestinations.Home) {
                        popUpTo(AppDestinations.Home) { inclusive = true }
                    }
                }
            )
        }
    }
}
