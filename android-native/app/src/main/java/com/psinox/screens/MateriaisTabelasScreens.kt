package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.psinox.data.tubosODScheduleData
import com.psinox.data.metalonData
import com.psinox.data.chapasData

@Composable
fun TubosODScheduleScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TopAppBar(
            title = { Text("Tubos OD & Schedule") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
        Spacer(Modifier.height(16.dp))
        Text("OD (mm)  |  Schedule  |  Espessura (mm)  |  Peso (kg/m)", style = MaterialTheme.typography.bodyMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(tubosODScheduleData) { tubo ->
                Card(Modifier.fillMaxWidth()) {
                    Row(Modifier.padding(12.dp)) {
                        Text("${tubo.odMm}", Modifier.weight(1f))
                        Text(tubo.schedule, Modifier.weight(1f))
                        Text("${tubo.espessuraMm}", Modifier.weight(1f))
                        Text("${tubo.pesoKgM}", Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun MetalonsScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TopAppBar(
            title = { Text("Metalons") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
        Spacer(Modifier.height(16.dp))
        Text("Largura x Altura (mm) | Espessura (mm) | Peso (kg/m)", style = MaterialTheme.typography.bodyMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(metalonData) { metalon ->
                Card(Modifier.fillMaxWidth()) {
                    Row(Modifier.padding(12.dp)) {
                        Text("${metalon.larguraMm} x ${metalon.alturaMm}", Modifier.weight(1f))
                        Text("${metalon.espessuraMm}", Modifier.weight(1f))
                        Text("${metalon.pesoKgM}", Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun ChapasScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TopAppBar(
            title = { Text("Chapas") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
        Spacer(Modifier.height(16.dp))
        Text("Espessura (mm) | Largura (mm) | Comprimento (mm) | Peso (kg)", style = MaterialTheme.typography.bodyMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(chapasData) { chapa ->
                Card(Modifier.fillMaxWidth()) {
                    Row(Modifier.padding(12.dp)) {
                        Text("${chapa.espessuraMm}", Modifier.weight(1f))
                        Text("${chapa.larguraMm}", Modifier.weight(1f))
                        Text("${chapa.comprimentoMm}", Modifier.weight(1f))
                        Text("${chapa.pesoKg}", Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
