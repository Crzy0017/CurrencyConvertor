package com.example.currencyi.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.currencyi.R
import com.example.currencyi.domain.models.Currency
import java.util.*

class CurrenciesRepository(val network: CurrencyService) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getCurrencies() : List<Currency> {
        val currencyNames = network.apiService.getCurrencyNames().currencies
        val currencyRates = network.apiService.getCurrencyRates("2022-07-01", "2022-07-01").quotes
        var counter = 0
        val currencies = currencyNames.map {
            val ticket = it.key
            val name = it.value
            val rate = currencyRates[ticket]?.endRate ?: 1.0
            Currency("$name $ticket", 0, counter++, R.drawable.flag, rate.toFloat())
        }
        return currencies
    }
}