package com.example.currencyi.model

import androidx.annotation.DrawableRes

data class CurrencySearch(
    val id: Int,
    val name: String,
    @DrawableRes val flagImage: Int
)
