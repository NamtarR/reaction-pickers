package com.namtarr.reactionpickers.emoji

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.namtarr.reactionpickers.emoji.model.Category

internal class EmojiPickerPagerAdapter(
    private val adapterFactory: (Category) -> EmojiAdapter
): PagerAdapter() {

    private val adapters = mutableMapOf<Category, EmojiAdapter>()
    private val categories = mutableListOf<Category>()

    override fun getCount() = categories.size

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val category = categories[position]
        val recyclerView = RecyclerView(container.context)
        recyclerView.adapter = getOrCreateAdapter(category)
        container.addView(recyclerView)
        return recyclerView
    }

    private fun getOrCreateAdapter(category: Category): EmojiAdapter {
        return adapters.getOrPut(category) {
            adapterFactory.invoke(category)
        }
    }
}