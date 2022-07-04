package com.example.currencyi.domain.models

import androidx.annotation.DrawableRes

data class Currency(
    val textview: String,
    val value: Int,
    val id: Int,
    @DrawableRes val imageRes: Int
): Views
