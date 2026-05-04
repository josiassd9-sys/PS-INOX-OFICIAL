package com.psinox.data

data class SucataItem(
    val id: Int,
    val material: String,
    val composicao: String,
    val preco: Double
)

val tabelaSucataData = listOf(
    SucataItem(1, "Sucata Inox 409/410/420 - Mista", "Cr: 11–13% / C: 0,15–0,40% / Fe balanceado", 0.80),
    SucataItem(2, "Sucata Inox 409/410/420 - Estamparia", "Cr: 11–13% / C: 0,15–0,40% / Fe balanceado", 2.30),
    SucataItem(3, "Sucata Inox 430 - Mista", "Cr: 16–18% / C: ≤0,12% / Fe balanceado – sem níquel", 1.00),
    SucataItem(4, "Sucata Inox 430 - Estamparia", "Cr: 16–18% / C: ≤0,12% / Fe balanceado – sem níquel", 2.50),
    SucataItem(5, "Sucata Nitronic N-32 (tubos para corte)", "Cr: 18–20% / Ni: 8–9% / Mn: 4–6% / N: ~0,25%", 2.00),
    SucataItem(6, "Sucata Baixa Liga", "Ni: 1,5% / Cr: 12% / Mn: 6%", 1.50),
    SucataItem(7, "Sucata 201 / 15-5 / 17-4 / 23-04", "201: Cr: 16–18%, Ni: 3,5–5,5%, Mn: 5–7% | 15-5 PH: Cr: 15%, Ni: 4–5%, Cu: 3% | 17-4 PH: Cr: 15–17%, Ni: 3–5%, Cu: 3–5%", 2.70),
    SucataItem(8, "Sucata Inox 301 / Resist. 304", "Cr: 16–18% / Ni: 6–8% / Mn: ≤2%", 4.40),
    SucataItem(9, "Sucata Inox 321", "Cr: 17-19% / Ni: 9-12% / Ti: 5xC a 0.70%", 5.60),
    SucataItem(10, "Sucata Inox 304 / Ni-resist 1", "Cr: 18–20% / Ni: 8–10,5% / Mn: ≤2%", 5.50),
    SucataItem(11, "Sucata Inox 309", "Cr: 22–24% / Ni: 12–15% / Mn: ≤2%", 7.50),
    SucataItem(12, "Sucata Inox 310", "Cr: 24–26% / Ni: 19–22% / Mn: ≤2%", 12.50),
    SucataItem(13, "Sucata Inox 316", "Cr: 16–18% / Ni: 10–14% / Mo: 2–3%", 10.70),
    SucataItem(14, "Sucata Alloy 904L", "Cr: 19-23% / Ni: 23-28% / Mo: 4-5% / Cu: 1-2%", 15.50),
    SucataItem(15, "Sucata 35/20", "Equivalente ao Aço 3520: Cr: 35% / Ni: 20%", 16.40),
    SucataItem(16, "Sucata 45/20", "Equivalente ao Aço 4520: Cr: 45% / Ni: 20%", 19.30),
    SucataItem(17, "Sucata CD4MCu", "Cr: 25% / Ni: 5% / Mo: 2% / Cu: 1–2%", 4.50),
    SucataItem(18, "Sucata SAF 2205", "Cr: 22% / Ni: 5% / Mo: 3% / N: 0,15%", 8.50),
    SucataItem(19, "Sucata SAF Duplex 2507", "Cr: 25% / Ni: 7% / Mo: 4% / N: 0,3%", 9.50),
    SucataItem(20, "Sucata Manganês 12%", "Mn: 11–14% / C: 1,0–1,4% / Fe balanceado", 2.00)
)

val listaSucatasData = tabelaSucataData
