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
import com.psinox.data.gaugeInfoMock
import com.psinox.data.GaugeInfo
import com.psinox.utils.PreferencesManager
import com.psinox.utils.ApiHelper
import org.json.JSONObject

@Composable
fun GaugeScreen() {
    val context = LocalContext.current
    var info by remember { mutableStateOf(gaugeInfoMock) }
    var erroApi by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val apiUrl = PreferencesManager.getApiUrl(context)
        if (!apiUrl.isNullOrBlank()) {
            val json = ApiHelper.getPerfisFromApi(apiUrl) // Espera-se que endpoint retorne objeto gauge
            if (json != null) {
                try {
                    val obj = JSONObject(json)
                    info = GaugeInfo(
                        valor = obj.optDouble("valor", gaugeInfoMock.valor.toDouble()).toFloat(),
                        descricao = obj.optString("descricao", gaugeInfoMock.descricao)
                    )
                    erroApi = null
                } catch (e: Exception) {
                    erroApi = "Erro ao processar dados da API."
                    info = gaugeInfoMock
                }
            } else {
                erroApi = "Não foi possível obter dados da API."
                info = gaugeInfoMock
            }
        } else {
            info = gaugeInfoMock
            erroApi = null
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gauge",
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
                Text("Valor: ${info.valor}", style = MaterialTheme.typography.bodyLarge)
                Text("Descrição: ${info.descricao}", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
}
