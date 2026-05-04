package com.psinox.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "geometria_prefs")

object GeometriaKeys {
    val VAO_X = floatPreferencesKey("vao_x")
    val VAO_Y = floatPreferencesKey("vao_y")
    val BALANCO_XESQ = floatPreferencesKey("balanco_xesq")
    val BALANCO_XDIR = floatPreferencesKey("balanco_xdir")
    val BALANCO_YSUP = floatPreferencesKey("balanco_ysup")
    val BALANCO_YINF = floatPreferencesKey("balanco_yinf")
}

data class GeometriaState(
    val vaoX: Float = 0f,
    val vaoY: Float = 0f,
    val balancoXEsq: Float = 0f,
    val balancoXDir: Float = 0f,
    val balancoYSup: Float = 0f,
    val balancoYInf: Float = 0f
)

class GeometriaDataStore(private val context: Context) {
    val geometriaData: Flow<GeometriaState> = context.dataStore.data.map { prefs ->
        GeometriaState(
            vaoX = prefs[GeometriaKeys.VAO_X] ?: 0f,
            vaoY = prefs[GeometriaKeys.VAO_Y] ?: 0f,
            balancoXEsq = prefs[GeometriaKeys.BALANCO_XESQ] ?: 0f,
            balancoXDir = prefs[GeometriaKeys.BALANCO_XDIR] ?: 0f,
            balancoYSup = prefs[GeometriaKeys.BALANCO_YSUP] ?: 0f,
            balancoYInf = prefs[GeometriaKeys.BALANCO_YINF] ?: 0f
        )
    }

    suspend fun salvarGeometria(
        vaoX: Float, vaoY: Float,
        balancoXEsq: Float, balancoXDir: Float,
        balancoYSup: Float, balancoYInf: Float
    ) {
        context.dataStore.edit { prefs ->
            prefs[GeometriaKeys.VAO_X] = vaoX
            prefs[GeometriaKeys.VAO_Y] = vaoY
            prefs[GeometriaKeys.BALANCO_XESQ] = balancoXEsq
            prefs[GeometriaKeys.BALANCO_XDIR] = balancoXDir
            prefs[GeometriaKeys.BALANCO_YSUP] = balancoYSup
            prefs[GeometriaKeys.BALANCO_YINF] = balancoYInf
        }
    }
}
