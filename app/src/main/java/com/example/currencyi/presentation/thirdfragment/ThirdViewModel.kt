package com.example.currencyi.presentation.thirdfragment

import androidx.lifecycle.*
import com.example.currencyi.data.CurrenciesRepository
import com.example.currencyi.domain.models.Currency
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ThirdViewModel(
    private val repository: CurrenciesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val _currencyConvertor = MutableLiveData<MutableList<Currency>>()
    val currencyConvertor: LiveData<MutableList<Currency>> = _currencyConvertor

    private val _currencies = MutableLiveData<List<Currency>>()
    val currencies: LiveData<List<Currency>> = _currencies

    fun getCurrencies() = viewModelScope.launch(ioDispatcher) {
        val currencies = repository.getCurrencies()
        _currencies.value = currencies
    }


    fun appendCurrency(currency: Currency) {
        val currentList = _currencyConvertor.value
        currentList?.add(currency)
        _currencyConvertor.value = currentList!!
    }

    fun convertCurrency(valueNew: Float) {
        val newValue = valueNew
    }

}