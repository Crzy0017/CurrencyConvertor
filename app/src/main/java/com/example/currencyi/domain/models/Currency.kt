package com.example.currencyi.domain.models

import androidx.annotation.DrawableRes

data class Currency(
    val textview: String,
    var amount: Int,
    val id: Int,
    @DrawableRes val imageRes: Int,
    val conversionRate: Float
): Views
