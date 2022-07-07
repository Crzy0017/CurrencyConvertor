package com.example.currencyi.domain.models

import com.google.gson.annotations.SerializedName

data class CurrencyRate(
    @SerializedName("start_rate") var startRate: Double? = null,
    @SerializedName("end_rate") var endRate: Double? = null
)
