package com.psinox.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SobreScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sobre",
            style = MaterialTheme.typography.titleLarge
        )
        Card(
            modifier = Modifier,
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier) {
                Text("App: PS-INOX Mobile", style = MaterialTheme.typography.bodyLarge)
                Text("Versão: 1.0.0", style = MaterialTheme.typography.bodyLarge)
                Text("Equipe: Josias, Time Dev", style = MaterialTheme.typography.bodyLarge)
                Text("© 2026 PS-INOX", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
}
