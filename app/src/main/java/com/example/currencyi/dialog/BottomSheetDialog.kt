package com.example.currencyi.dialog


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.example.currencyi.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText

class BottomSheetDialog: BottomSheetDialogFragment(), SelectCurrencyBottomSheet.SendDataToFirstBottomSheet{

    private lateinit var typeCurrencyType: TextInputEditText
    var respectiveFlag: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.bottom_sheet_add_currency, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        typeCurrencyType = view.findViewById(R.id.typeCurrencyType)
        val typeCurrencyCost: TextInputEditText = view.findViewById(R.id.typeCurrencyCost)
        val selectCurrencyFlag = view.findViewById<Button>(R.id.selectCurrencyFlag)
        val addNewCurrency = view.findViewById<Button>(R.id.addNewCurrency)


        selectCurrencyFlag.setOnClickListener {
            (parentFragment as? NewBottomSheet)?.openNewBottomSheet(childFragmentManager)
        }

        addNewCurrency.setOnClickListener {
            (parentFragment as? NewBottomSheet)?.addItemFromBottomSheet(typeCurrencyType, typeCurrencyCost, respectiveFlag!!)
            dismiss()
        }

    }

    override fun changeFlag(text: String, res: Int) {
        Log.i("Tag","$res")
        typeCurrencyType.setText(text)
        respectiveFlag = res
    }

    interface NewBottomSheet{
        fun openNewBottomSheet(fragmentManager: FragmentManager)
        fun addItemFromBottomSheet(typeCurrencyType: TextInputEditText, typeCurrencyCost:TextInputEditText, res: Int)
    }

}