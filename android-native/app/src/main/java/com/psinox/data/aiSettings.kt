package com.psinox.data

data class AISettings(
    val modelo: String = "GPT-4",
    val temperatura: Float = 0.7f,
    val maxTokens: Int = 512
)

val aiSettingsMock = AISettings(
    modelo = "GPT-4",
    temperatura = 0.7f,
    maxTokens = 512
)
