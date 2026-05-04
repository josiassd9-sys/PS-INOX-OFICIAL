package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.psinox.data.materiaisData

import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MateriaisScreen(
    onBack: () -> Unit,
    onMaterialClick: (Int) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 24.dp)) {
        TopAppBar(
            title = { Text("Materiais") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
        Column(modifier = Modifier.padding(top = 16.dp)) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(materiaisData) { material ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onMaterialClick(material.id) },
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = material.nome, style = MaterialTheme.typography.titleMedium)
                            Text(text = material.tipo, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
