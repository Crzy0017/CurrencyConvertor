package com.example.currencyi.presentation.fifthfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FifthViewModel: ViewModel() {

    private val _imageId = MutableLiveData<Int>()
    val imageId: LiveData<Int> = _imageId
    // TODO: Implement the ViewModel
}