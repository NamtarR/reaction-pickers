package com.namtarr.reactionpickers.emoji

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.namtarr.reactionpickers.core.extensions.dp
import com.namtarr.reactionpickers.core.extensions.makeLayoutParams
import com.namtarr.reactionpickers.core.views.PickerView
import com.namtarr.reactionpickers.core.views.SearchView
import com.namtarr.reactionpickers.emoji.model.Category
import com.namtarr.reactionpickers.emoji.model.Emoji
import com.namtarr.reactionpickers.emoji.utils.findViewHolderForAdapterPosition

class EmojiPickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): PickerView<Category, Emoji>(context, attrs, defStyleAttr) {

    private val searchView = SearchView(context)
    private val tabLayout = TabLayout(context)
    private val viewPager = ViewPager2(context)
    private val emojiDataSource = EmojiDataSource()
    private val viewPagerAdapter = EmojiPickerPagerAdapter(::createAdapter)
    private val emojiAdapters = mutableMapOf<Category, EmojiAdapter>()

    init {
        orientation = VERTICAL

        val headerLayout = LinearLayout(context).apply {
            orientation = HORIZONTAL
        }
        headerLayout.addView(searchView, makeLayoutParams(width = LayoutParams.WRAP_CONTENT))
        headerLayout.addView(tabLayout, makeLayoutParams(height = LayoutParams.MATCH_PARENT, marginStart = 8.dp))

        addView(headerLayout, makeLayoutParams())
        addView(viewPager, makeLayoutParams { weight = 1f })

        viewPager.adapter = viewPagerAdapter.apply {
            submitList(emojiDataSource.categories())
        }

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(viewPagerAdapter.get(position).drawable)
            tab.view.setPadding(0, 0, 0, 0)
        }.attach()

        searchView.setOnSearchExpand {
            tabLayout.isVisible = false
            searchView.updateLayoutParams<LayoutParams> {
                width = LayoutParams.MATCH_PARENT
            }
            viewPager.isUserInputEnabled = false
        }

        searchView.setOnSearchCollapse {
            tabLayout.isVisible = true
            searchView.updateLayoutParams<LayoutParams> {
                width = LayoutParams.WRAP_CONTENT
            }
            viewPager.isUserInputEnabled = true
            searchView.clearQuery()
        }

        searchView.setOnSearch {
            val position = viewPager.currentItem
            val categories = emojiDataSource.categories()
            val category = categories[position]
            val adapter = emojiAdapters[category] ?: return@setOnSearch
            val result = emojiDataSource.emojis(category, it)
            val viewHolder = viewPager.findViewHolderForAdapterPosition(position)
            val recyclerView = viewHolder?.itemView as? RecyclerView

            adapter.submitList(result) {
                recyclerView?.scrollToPosition(0)
            }
        }
    }

    private fun createAdapter(category: Category): EmojiAdapter {
        return emojiAdapters.getOrPut(category) {
            EmojiAdapter().apply {
                submitList(emojiDataSource.emojis(category, null))
            }
        }
    }
}