package com.example.currencyi.presentation.thirdfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyi.domain.models.Currency

class ThirdViewModel: ViewModel() {
    // TODO: Implement the ViewModel

    private val _currencyConvertor = MutableLiveData<MutableList<Currency>>()
    val currencyConvertor: LiveData<MutableList<Currency>> = _currencyConvertor

}