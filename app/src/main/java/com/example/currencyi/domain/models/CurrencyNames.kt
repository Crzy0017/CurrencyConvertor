package com.example.currencyi.domain.models

import com.google.gson.annotations.SerializedName

class CurrencyNames {
    @SerializedName("currencies" ) var currencies : Map<String, String> = emptyMap()

}