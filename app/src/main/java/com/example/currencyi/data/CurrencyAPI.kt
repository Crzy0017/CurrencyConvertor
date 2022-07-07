package com.example.currencyi.data

import com.example.currencyi.domain.models.CurrencyNames
import com.example.currencyi.domain.models.CurrencyRates
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val API_KEY = "rNdVTpMEujbfAiZJXUWWo5FUj1LXRLcX'5"

interface CurrencyAPI {
    @GET("list")
    suspend fun getCurrencyNames(
        @Header("apiKey") api: String = API_KEY
    ) : CurrencyNames

    @GET("change")
    suspend fun getCurrencyRates(
        @Query("end_date") endDate: String,
        @Query("start_date") startDate: String,
        @Query("source") source: String = "KZT",
        @Header("apiKey") api: String = API_KEY
    ) : CurrencyRates
}