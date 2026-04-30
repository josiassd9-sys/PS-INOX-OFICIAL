package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.psinox.data.dashboardInfo
import com.psinox.data.gaugeInfo

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Dashboard") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    androidx.compose.material3.Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Total de Perfis: ${dashboardInfo.totalPerfis}")
                    Text(text = "Total de Materiais: ${dashboardInfo.totalMateriais}")
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Gauges:", style = MaterialTheme.typography.titleMedium)
            gaugeInfo.forEach { gauge ->
                Text(text = "ID: ${gauge.id} | Valor: ${gauge.valor} ${gauge.unidade}")
            }
        }
    }
}
