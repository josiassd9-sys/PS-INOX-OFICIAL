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
import com.psinox.data.tubosODData
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
        Text("Descrição | Peso (kg/m)", style = MaterialTheme.typography.bodyMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(tubosODData) { tubo ->
                Card(Modifier.fillMaxWidth()) {
                    Row(Modifier.padding(12.dp)) {
                        Text(tubo.descricao, Modifier.weight(2f))
                        Text("${tubo.peso}", Modifier.weight(1f))
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
            Text("Descrição | Peso (kg/m)", style = MaterialTheme.typography.bodyMedium)
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(metalonsData) { metalon ->
                    Card(Modifier.fillMaxWidth()) {
                        Row(Modifier.padding(12.dp)) {
                            Text(metalon.descricao, Modifier.weight(2f))
                            Text("${metalon.peso}", Modifier.weight(1f))
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
        Text("Descrição | Peso (kg/un)", style = MaterialTheme.typography.bodyMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(chapasData) { chapa ->
                Card(Modifier.fillMaxWidth()) {
                    Row(Modifier.padding(12.dp)) {
                        Text(chapa.descricao, Modifier.weight(2f))
                        Text("${chapa.peso}", Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
