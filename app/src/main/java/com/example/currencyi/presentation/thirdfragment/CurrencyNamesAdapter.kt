package com.example.currencyi.presentation.thirdfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyi.R
import com.example.currencyi.databinding.CurrencyNameBinding
import com.example.currencyi.domain.models.Currency
import kotlin.reflect.KFunction2

class CurrencyNamesAdapter(
    var data: List<Currency>,
    val onClick: (String) -> Unit
) : RecyclerView.Adapter<CurrencyNameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyNameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CurrencyNameBinding.inflate(inflater, parent, false)
        return CurrencyNameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyNameViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.binding.tvCurrencyName.setOnClickListener { onClick(item.textview) }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}