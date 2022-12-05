package com.namtarr.reactionpickers.emoji

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.namtarr.reactionpickers.emoji.model.Category

internal class EmojiPickerPagerAdapter(
    private val adapterFactory: (Category) -> EmojiAdapter,
    private val dataSource: EmojiDataSource
): ListAdapter<Category, EmojiPickerPagerAdapter.ViewHolder>(CategoryDiffUtilCallback) {

    private val adapters = mutableMapOf<Category, EmojiAdapter>()
    private val pool = RecyclerView.RecycledViewPool().apply {
        setMaxRecycledViews(0, 100)
    }

    init {
        submitList(dataSource.categories())
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
        (holder.itemView as RecyclerView).adapter = getOrCreateAdapter(getItem(position))
    }

    private fun getOrCreateAdapter(category: Category): EmojiAdapter {
        return adapters.getOrPut(category) {
            adapterFactory.invoke(category)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}