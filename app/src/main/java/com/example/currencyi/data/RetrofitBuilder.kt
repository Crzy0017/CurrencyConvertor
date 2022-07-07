package com.example.currencyi.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CurrencyService {
    private val BASE_URL = "https://api.apilayer.com/currency_data/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: CurrencyAPI = getRetrofit().create(CurrencyAPI::class.java)
}