package com.example.currencyi.presentation.thirdfragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyi.R
import com.example.currencyi.presentation.thirdfragment.itemtouchhelper.ItemTouchDelegate
import com.example.currencyi.domain.models.Add
import com.example.currencyi.domain.models.Currency
import com.example.currencyi.domain.models.Views

class Adapter(
    private val itemTouchDelegate: ItemTouchDelegate,
    private val scroll: () -> Unit,
    private val sheetDialog: () -> Unit,
    private val changedToolbar: (Currency) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<Views>()
    lateinit var currencyNew: List<Currency>

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val addNewItem: (Currency) -> Unit = { item: Currency -> addItem(item) }
        return when(viewType){
            R.layout.add_item -> AddViewHolder(inflater, parent, addNewItem, scroll, sheetDialog)
            else -> {
                val holder = CurrencyViewHolder(inflater, parent, changedToolbar)
                dragDrop(holder)
                holder
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CurrencyViewHolder -> holder.onBind(data[position] as Currency)
            is AddViewHolder -> holder.onBind()
        }
    }

    override fun getItemViewType(position: Int): Int =
        when(data[position]){
            is Currency -> R.layout.item_currency
            is Add ->  R.layout.add_item
            else ->  R.layout.item_currency
        }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun dragDrop(holder: CurrencyViewHolder) {
        holder.itemView.findViewById<TextView>(R.id.Label)
            .setOnTouchListener { _, motionEvent ->
                if (motionEvent.actionMasked == MotionEvent.ACTION_DOWN) {
                    itemTouchDelegate.startDragging(holder)
                }
                return@setOnTouchListener true
            }
    }

    fun addItem(item: Currency) {
        data.add(data.size-1, item)
        notifyItemInserted(data.size)
    }

    fun sortA() {
        data.removeLast()
        data.sortBy{ (it as Currency).textview }
        data.add(Add())
        notifyDataSetChanged()
    }

    fun sortB() {
        data.removeLast()
        data.sortBy{ (it as Currency).amount }
        data.add(Add())
        notifyDataSetChanged()
    }

    fun noSort() {
        data.removeLast()
        data.sortByDescending{ (it as Currency).id }
        data.add(Add())
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    fun deleteCurrency(currency: Currency) {
        data.removeAt(data.indexOf(currency))
        notifyDataSetChanged()
    }

    fun moveItem(from: Int, to: Int) {
        val value = data[from]
        data.remove(value)
        if (to < from) data.add(to, value)
        else data.add(to - 1, value)
    }

    fun setItems(list: List<Views>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
}