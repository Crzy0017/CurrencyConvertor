package com.example.currencyi.presentation.thirdfragment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.currencyi.R
import com.example.currencyi.presentation.thirdfragment.ThirdViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SelectCurrencyBottomSheet: BottomSheetDialogFragment(){

    val viewModel: ThirdViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.select_currency_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kazFlag = view.findViewById<TextView>(R.id.kazFlag)
        val euroFlag = view.findViewById<TextView>(R.id.euroFlag)
        val americaFlag = view.findViewById<TextView>(R.id.usaFlag)
        val turkeyFlag = view.findViewById<TextView>(R.id.turFlag)


        kazFlag.setOnClickListener {
            (parentFragment as? SendDataToFirstBottomSheet)?.changeFlag("Тенге, Казахстан", R.drawable.kz)
            dismiss()
        }

        euroFlag.setOnClickListener {
            (parentFragment as? SendDataToFirstBottomSheet)?.changeFlag("Евро, EC", R.drawable.eu)
            dismiss()
        }

        americaFlag.setOnClickListener {
            (parentFragment as? SendDataToFirstBottomSheet)?.changeFlag("Доллары, США", R.drawable.usa)
            dismiss()
        }

        turkeyFlag.setOnClickListener {
            (parentFragment as? SendDataToFirstBottomSheet)?.changeFlag("Лира, Турция", R.drawable.tur)
            dismiss()
        }

    }

    interface SendDataToFirstBottomSheet{
        fun changeFlag(text: String, res: Int)
    }
}

