package com.armatuhandroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import com.armatuhandroll.data.CartItem
import com.armatuhandroll.ui.navigation.AppNavHost
import com.armatuhandroll.ui.theme.ArmaTuHandrollTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArmaTuHandrollTheme {
                val cartItems = remember { emptyList<CartItem>().toMutableStateList() }
                AppNavHost(cartItems = cartItems)
            }
        }
    }
}
