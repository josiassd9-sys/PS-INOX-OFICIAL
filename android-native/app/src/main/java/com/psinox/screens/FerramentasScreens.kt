package com.psinox.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.psinox.data.configuracoesFerramentasData
import com.psinox.data.listaMateriaisData
import com.psinox.data.tabelaSucataData
import com.psinox.data.listaSucatasData
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListaMateriaisScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Materiais") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
            Column(Modifier.padding(padding).padding(16.dp)) {
                Text("Materiais cadastrados:", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(12.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(listaMateriaisData) { item ->
                        Card(Modifier.fillMaxWidth()) {
                            Row(Modifier.padding(12.dp)) {
                                Text(item.descricao, Modifier.weight(2f))
                                Text("${item.peso} ${item.unidade}", Modifier.weight(1f))
                            }
                        }
                    }
                }
        }
    }
}

@Composable
fun TabelaSucataScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tabela de Sucata") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            Text("Tabela de preços de sucata:", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(tabelaSucataData) { item ->
                    Card(Modifier.fillMaxWidth()) {
                        Row(Modifier.padding(12.dp)) {
                            Text(item.material, Modifier.weight(2f))
                            Text(item.composicao, Modifier.weight(2f))
                            Text("R$ ${item.preco}", Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListaSucatasScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Sucatas") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
                Text("Sucatas cadastradas:", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(listaSucatasData) { item ->
                    Card(Modifier.fillMaxWidth()) {
                        Row(Modifier.padding(12.dp)) {
                            Text(item.material, Modifier.weight(2f))
                            Text(item.composicao, Modifier.weight(2f))
                            Text("R$ ${item.preco}", Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ConfiguracoesFerramentasScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configurações de Ferramentas") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            Text("Configurações atuais:", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(configuracoesFerramentasData) { item ->
                    Card(Modifier.fillMaxWidth()) {
                        Row(Modifier.padding(12.dp)) {
                            Text(item.nome, Modifier.weight(2f))
                            Text(item.valor, Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}
