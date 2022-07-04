package com.example.currencyi.presentation.thirdfragment.itemtouchhelper

import androidx.recyclerview.widget.RecyclerView

interface ItemTouchDelegate {
    fun startDragging(viewHolder: RecyclerView.ViewHolder)
}