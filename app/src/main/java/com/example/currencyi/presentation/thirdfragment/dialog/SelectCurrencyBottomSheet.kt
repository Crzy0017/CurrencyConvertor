package com.example.currencyi.presentation.thirdfragment.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.currencyi.R
import com.example.currencyi.databinding.SelectCurrencyBottomSheetBinding
import com.example.currencyi.presentation.thirdfragment.CurrencyNamesAdapter
import com.example.currencyi.presentation.thirdfragment.ThirdViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SelectCurrencyBottomSheet : BottomSheetDialogFragment() {

    private var _binding: SelectCurrencyBottomSheetBinding? = null
    private val binding get() = _binding!!

    val viewModel: ThirdViewModel by sharedViewModel()

    private lateinit var adapter: CurrencyNamesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SelectCurrencyBottomSheetBinding.inflate(inflater, container, false)
        viewModel.getCurrencies()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CurrencyNamesAdapter(emptyList(), ::onItemClick)
        binding.rvCurrencies.adapter = adapter
        viewModel.currencies.observeForever() {
            adapter.data = it
            adapter.notifyDataSetChanged()
        }
    }

    private fun onItemClick(name: String) {
        (parentFragment as? SendDataToFirstBottomSheet)?.changeFlag(name, R.drawable.kz)
        dismiss()
    }

    interface SendDataToFirstBottomSheet {
        fun changeFlag(text: String, res: Int)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

