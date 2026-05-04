package com.psinox.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.vigaPrincipalDataStore by preferencesDataStore(name = "viga_principal")

object VigaPrincipalPrefs {
    val COMPRIMENTO = floatPreferencesKey("comprimento")
    val LARGURA = floatPreferencesKey("largura")
    val ALTURA = floatPreferencesKey("altura")
}

class VigaPrincipalDataStore(private val context: Context) {
    val vigaData: Flow<VigaPrincipalState> = context.vigaPrincipalDataStore.data.map { prefs ->
        VigaPrincipalState(
            comprimento = prefs[VigaPrincipalPrefs.COMPRIMENTO] ?: 0f,
            largura = prefs[VigaPrincipalPrefs.LARGURA] ?: 0f,
            altura = prefs[VigaPrincipalPrefs.ALTURA] ?: 0f
        )
    }

    suspend fun salvarViga(comprimento: Float, largura: Float, altura: Float) {
        context.vigaPrincipalDataStore.edit { prefs ->
            prefs[VigaPrincipalPrefs.COMPRIMENTO] = comprimento
            prefs[VigaPrincipalPrefs.LARGURA] = largura
            prefs[VigaPrincipalPrefs.ALTURA] = altura
        }
    }
}

data class VigaPrincipalState(
    val comprimento: Float,
    val largura: Float,
    val altura: Float
)
