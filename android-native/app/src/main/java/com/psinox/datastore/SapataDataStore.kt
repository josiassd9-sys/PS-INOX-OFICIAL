package com.psinox.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.sapataDataStore by preferencesDataStore(name = "sapata")

object SapataPrefs {
    val CARGA = floatPreferencesKey("carga")
    val FCK = floatPreferencesKey("fck")
    val COEF_SEG = floatPreferencesKey("coef_seg")
}

class SapataDataStore(private val context: Context) {
    val sapataData: Flow<SapataState> = context.sapataDataStore.data.map { prefs ->
        SapataState(
            carga = prefs[SapataPrefs.CARGA] ?: 0f,
            fck = prefs[SapataPrefs.FCK] ?: 0f,
            coefSeg = prefs[SapataPrefs.COEF_SEG] ?: 0f
        )
    }

    suspend fun salvarSapata(carga: Float, fck: Float, coefSeg: Float) {
        context.sapataDataStore.edit { prefs ->
            prefs[SapataPrefs.CARGA] = carga
            prefs[SapataPrefs.FCK] = fck
            prefs[SapataPrefs.COEF_SEG] = coefSeg
        }
    }
}

data class SapataState(
    val carga: Float,
    val fck: Float,
    val coefSeg: Float
)
