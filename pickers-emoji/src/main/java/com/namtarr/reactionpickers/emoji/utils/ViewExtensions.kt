package com.namtarr.reactionpickers.emoji.utils

import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.findViewHolderForAdapterPosition(position: Int): ViewHolder? {
    val internalRecyclerView = get(0) as RecyclerView
    return internalRecyclerView.findViewHolderForAdapterPosition(position)
}