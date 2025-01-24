package com.oktaygenc.weatherwise.ui.presentation.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ErrorCard(message: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Red.copy(alpha = 0.3f)
        ), modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = message, color = Color.White, modifier = Modifier.padding(16.dp)
        )
    }
}