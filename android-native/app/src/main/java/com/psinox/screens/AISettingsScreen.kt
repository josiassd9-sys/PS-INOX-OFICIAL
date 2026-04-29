package com.psinox.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.psinox.data.aiSettingsMock
import com.psinox.data.AISettings
import com.psinox.utils.PreferencesManager
import com.psinox.utils.ApiHelper
import org.json.JSONObject

@Composable
fun AISettingsScreen() {
    val context = LocalContext.current
    var info by remember { mutableStateOf(aiSettingsMock) }
    var erroApi by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val apiUrl = PreferencesManager.getApiUrl(context)
        if (!apiUrl.isNullOrBlank()) {
            val json = ApiHelper.getPerfisFromApi(apiUrl) // Espera-se que endpoint retorne objeto aiSettings
            if (json != null) {
                try {
                    val obj = JSONObject(json)
                    info = AISettings(
                        modelo = obj.optString("modelo", aiSettingsMock.modelo),
                        temperatura = obj.optDouble("temperatura", aiSettingsMock.temperatura.toDouble()).toFloat(),
                        maxTokens = obj.optInt("maxTokens", aiSettingsMock.maxTokens)
                    )
                    erroApi = null
                } catch (e: Exception) {
                    erroApi = "Erro ao processar dados da API."
                    info = aiSettingsMock
                }
            } else {
                erroApi = "Não foi possível obter dados da API."
                info = aiSettingsMock
            }
        } else {
            info = aiSettingsMock
            erroApi = null
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "AI Settings",
            style = MaterialTheme.typography.titleLarge
        )
        if (erroApi != null) {
            Text(erroApi!!, color = MaterialTheme.colorScheme.error)
        }
        Card(
            modifier = Modifier,
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier) {
                Text("Modelo: ${info.modelo}", style = MaterialTheme.typography.bodyLarge)
                Text("Temperatura: ${info.temperatura}", style = MaterialTheme.typography.bodyLarge)
                Text("Max Tokens: ${info.maxTokens}", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
}
