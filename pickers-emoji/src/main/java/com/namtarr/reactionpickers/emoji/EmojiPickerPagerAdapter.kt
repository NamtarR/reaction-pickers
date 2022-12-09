package com.namtarr.reactionpickers.emoji

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.namtarr.reactionpickers.emoji.model.Category

internal class EmojiPickerPagerAdapter(
    private val adapterFactory: (Category) -> EmojiAdapter
): ListAdapter<Category, EmojiPickerPagerAdapter.ViewHolder>(CategoryDiffUtilCallback) {

    private val pool = RecycledViewPool().apply {
        setMaxRecycledViews(0, 128)
    }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val recyclerView = RecyclerView(parent.context)
        recyclerView.layoutManager = GridLayoutManager(parent.context, 8).apply {
            recycleChildrenOnDetach = true
        }
        recyclerView.setRecycledViewPool(pool)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        return ViewHolder(recyclerView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as RecyclerView).adapter = adapterFactory.invoke(getItem(position))
    }

    override fun getItemId(position: Int) = getItem(position).ordinal.toLong() * position

    fun get(position: Int): Category = getItem(position)

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}