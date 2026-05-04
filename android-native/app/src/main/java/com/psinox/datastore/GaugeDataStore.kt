package com.psinox.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "gauge_prefs")

object GaugeKeys {
    val CARGA = floatPreferencesKey("carga")
    val USO = stringPreferencesKey("uso")
}

data class GaugeState(
    val carga: Float = 0f,
    val uso: String = "Piso"
)

class GaugeDataStore(private val context: Context) {
    val gaugeData: Flow<GaugeState> = context.dataStore.data.map { prefs ->
        GaugeState(
            carga = prefs[GaugeKeys.CARGA] ?: 0f,
            uso = prefs[GaugeKeys.USO] ?: "Piso"
        )
    }

    suspend fun salvarGauge(carga: Float, uso: String) {
        context.dataStore.edit { prefs ->
            prefs[GaugeKeys.CARGA] = carga
            prefs[GaugeKeys.USO] = uso
        }
    }
}
