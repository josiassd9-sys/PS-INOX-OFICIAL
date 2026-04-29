package com.psinox.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import com.psinox.utils.PreferencesManager

@Composable
fun ConfiguracoesScreen() {
    val context = LocalContext.current
    var apiUrl by remember { mutableStateOf(PreferencesManager.getApiUrl(context) ?: "") }
    var savedMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Configurações",
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedTextField(
            value = apiUrl,
            onValueChange = { apiUrl = it },
            label = { Text("URL da API") },
            placeholder = { Text("https://sua-api.com/perfis") },
            singleLine = true,
            modifier = Modifier
        )
        Button(onClick = {
            PreferencesManager.saveApiUrl(context, apiUrl)
            savedMessage = "URL salva com sucesso!"
            Toast.makeText(context, savedMessage, Toast.LENGTH_SHORT).show()
        }) {
            Text("Salvar URL da API")
        }
    }
}
