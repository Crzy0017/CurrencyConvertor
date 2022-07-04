package com.example.currencyi.presentation.fourthfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyi.domain.models.CurrencySearch

class FourthViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _currencySearch = MutableLiveData<List<CurrencySearch>>()
    val currencySearch: LiveData<List<CurrencySearch>> = _currencySearch

}