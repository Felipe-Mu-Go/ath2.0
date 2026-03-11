package com.armatuhandroll.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.armatuhandroll.data.CartItem
import com.armatuhandroll.ui.components.AppBackground
import com.armatuhandroll.ui.components.PrimaryActionButton

@Composable
fun OrderSummaryScreen(
    cartItems: List<CartItem>,
    onBackHome: () -> Unit
) {
    val total = cartItems.sumOf { it.totalPrice }

    AppBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "🎉 Pedido confirmado", style = MaterialTheme.typography.headlineMedium)
            Text(
                text = "Total pagado: $${total}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Text(
                text = "Gracias por comprar en ArmaTuHandroll.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            PrimaryActionButton(text = "Volver al inicio", onClick = onBackHome)
        }
    }
}
