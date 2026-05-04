package com.psinox.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.lajeDataStore by preferencesDataStore(name = "laje")

object LajePrefs {
    val COMPRIMENTO = floatPreferencesKey("comprimento")
    val LARGURA = floatPreferencesKey("largura")
    val ALTURA = floatPreferencesKey("altura")
}

class LajeDataStore(private val context: Context) {
    val lajeData: Flow<LajeState> = context.lajeDataStore.data.map { prefs ->
        LajeState(
            comprimento = prefs[LajePrefs.COMPRIMENTO] ?: 0f,
            largura = prefs[LajePrefs.LARGURA] ?: 0f,
            altura = prefs[LajePrefs.ALTURA] ?: 0f
        )
    }

    suspend fun salvarLaje(comprimento: Float, largura: Float, altura: Float) {
        context.lajeDataStore.edit { prefs ->
            prefs[LajePrefs.COMPRIMENTO] = comprimento
            prefs[LajePrefs.LARGURA] = largura
            prefs[LajePrefs.ALTURA] = altura
        }
    }
}

data class LajeState(
    val comprimento: Float,
    val largura: Float,
    val altura: Float
)
