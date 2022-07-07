package com.example.currencyi.presentation.thirdfragment

import androidx.recyclerview.widget.RecyclerView
import com.example.currencyi.databinding.CurrencyNameBinding
import com.example.currencyi.domain.models.Currency

class CurrencyNameViewHolder(val binding: CurrencyNameBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(currency: Currency) {
        binding.tvCurrencyName.text = currency.textview
    }
}