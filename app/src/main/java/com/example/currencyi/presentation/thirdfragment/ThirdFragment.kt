package com.example.currencyi.presentation.thirdfragment

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.*
import com.example.currencyi.R
import com.example.currencyi.domain.models.Add
import com.example.currencyi.domain.models.Currency
import com.example.currencyi.presentation.thirdfragment.dialog.BottomSheetDialog
import com.example.currencyi.presentation.thirdfragment.dialog.FirstDialogCallBack
import com.example.currencyi.presentation.thirdfragment.dialog.FirstDialogFragment
import com.example.currencyi.presentation.thirdfragment.dialog.SelectCurrencyBottomSheet
import com.example.currencyi.presentation.thirdfragment.itemtouchhelper.DragDrop
import com.example.currencyi.presentation.thirdfragment.itemtouchhelper.ItemTouchDelegate
import com.example.currencyi.presentation.thirdfragment.itemtouchhelper.SwipeRight
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ThirdFragment : Fragment(R.layout.fragment_third), ItemTouchDelegate, FirstDialogCallBack, BottomSheetDialog.NewBottomSheet {
    private var currencyList = mutableListOf(
        Currency("Тенге, Казахстан", 150000, 10, R.drawable.kz, 1.0F),
        Currency("Евро, ОС", 70000, 9, R.drawable.eu, 500.0F),
        Currency("Лира, Турция", 15000, 8, R.drawable.tur, 30.0F),
        Currency("Доллары, США", 120000, 7, R.drawable.usa, 440.0F),
        Currency("Евро, ОС", 45000, 6, R.drawable.eu, 500.0F),
        Currency("Доллары, США", 67000, 5, R.drawable.usa, 440.0F),
        Currency("Тенге, Казахстан", 250000, 4, R.drawable.kz, 1.0F),
        Currency("Лира, Турция", 350000, 3, R.drawable.tur, 30.0F),
        Add()
    )

    private val thirdViewModel: ThirdViewModel by sharedViewModel()
    private lateinit var defaultToolbar: androidx.appcompat.widget.Toolbar
    private  var currencyAdapter: Adapter? = null
    private var currencyManager: LinearLayoutManager? = null
    private var dragDrop: ItemTouchHelper? = null
    private lateinit var openDialog: DialogFragment
    private lateinit var deleteCurrency: Currency
    private var index = 1
    private var snapPosition = RecyclerView.NO_POSITION
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defaultToolbar = view.findViewById(R.id.toolbar)
        defaultToolbar.inflateMenu(R.menu.menu_with_submenu)
        onOptionsItemSelected()
        setupCurrency()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_with_submenu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val chosenIndex = when (index) {
            1 -> R.id.sort1
            2 -> R.id.sort2
            else -> 1
        }
        menu.findItem(chosenIndex).isChecked = true
        super.onPrepareOptionsMenu(menu)
    }

    private fun onOptionsItemSelected() {
        defaultToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.sort1 -> {
                    currencyAdapter?.sortA()
                    index = 1
                    true
                }
                R.id.sort2 -> {
                    currencyAdapter?.sortB()
                    index = 2
                    true
                }
                R.id.no_sort -> {
                    currencyAdapter?.noSort()
                    true
                }
                R.id.deleteCurrency -> {
                    openDialog = FirstDialogFragment()
                    openDialog.show(childFragmentManager, null)
                    setupToolbar()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupCurrency() {
        currencyManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val scroll: ()-> Unit = {
            val smoothScroller = object : LinearSmoothScroller(requireContext()) {
                override fun getVerticalSnapPreference(): Int = SNAP_TO_START
            }
            smoothScroller.targetPosition = currencyAdapter?.itemCount ?: 0
            currencyManager?.startSmoothScroll(smoothScroller)
        }
        val sheetDialog: () -> Unit = {
            BottomSheetDialog().show(childFragmentManager, null)
        }
        currencyAdapter = Adapter(this, scroll, sheetDialog, changedToolbar())

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView?.apply {
            adapter = currencyAdapter
            layoutManager = currencyManager
            itemAnimator = DefaultItemAnimator()
        }

        val swipeRight = ItemTouchHelper(SwipeRight(currencyAdapter))
        dragDrop = ItemTouchHelper(DragDrop())

        swipeRight.attachToRecyclerView(recyclerView)
        dragDrop?.attachToRecyclerView(recyclerView)

        currencyAdapter?.setItems(currencyList)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                snapHelper.findSnapView(currencyManager)?.let {
                    position = currencyManager?.getPosition(it)!!
                    if (snapPosition != position) {
                        snapPosition = position
                    }
                }
            }
        })

    }

    private fun changedToolbar(): (Currency) -> Unit {
        val newToolbar: (Currency) -> Unit = { currency ->
            defaultToolbar.title = "Item selected"
            defaultToolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.new_background))
            defaultToolbar.menu.findItem(R.id.sort).isVisible = false
            defaultToolbar.menu.findItem(R.id.no_sort).isVisible = false
            defaultToolbar.menu.findItem(R.id.deleteCurrency).isVisible = true
            deleteCurrency = currency
        }
        return newToolbar
    }

    override fun openNewBottomSheet(fragmentManager: FragmentManager) {
        SelectCurrencyBottomSheet().show(fragmentManager, null)
    }

    override fun addItemFromBottomSheet(
        typeCurrencyType: TextInputEditText,
        typeCurrencyCost: TextInputEditText,
        res: Int
    ) {
        val newCurrencyAdd = Currency(typeCurrencyType.text.toString(), Integer.parseInt(typeCurrencyCost.text.toString()), 1, res, 5.0F)
        currencyAdapter?.addItem(newCurrencyAdd)
    }


    private fun setupToolbar() {
        defaultToolbar.title = "Convertor"
        defaultToolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        defaultToolbar.menu.findItem(R.id.sort).isVisible = true
        defaultToolbar.menu.findItem(R.id.no_sort).isVisible = true
        defaultToolbar.menu.findItem(R.id.deleteCurrency).isVisible = false
    }

    override fun startDragging(viewHolder: RecyclerView.ViewHolder) {
        dragDrop?.startDrag(viewHolder)
    }

    override fun onCurrencyRemove() {
        currencyAdapter?.deleteCurrency(deleteCurrency)
        openDialog.dismiss()
    }

}