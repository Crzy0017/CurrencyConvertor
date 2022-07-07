package com.example.currencyi.domain.models

import com.google.gson.annotations.SerializedName

data class CurrencyRates(
    @SerializedName("quotes") var quotes: Map<String, CurrencyRate> = emptyMap()
)
