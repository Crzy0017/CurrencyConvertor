package com.example.currencyi.di

import com.example.currencyi.data.CurrenciesRepository
import com.example.currencyi.data.CurrencyService
import com.example.currencyi.presentation.thirdfragment.ThirdViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory { CurrencyService() }
    factory { CurrenciesRepository(get())}
    viewModel { ThirdViewModel(get()) }
}