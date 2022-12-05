package com.namtarr.reactionpickers.emoji

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.namtarr.reactionpickers.core.extensions.makeLayoutParams
import com.namtarr.reactionpickers.core.views.HeaderLayout
import com.namtarr.reactionpickers.core.views.PickerView
import com.namtarr.reactionpickers.emoji.model.Category
import com.namtarr.reactionpickers.emoji.model.Emoji

class EmojiPickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): PickerView<Category, Emoji>(context, attrs, defStyleAttr) {

    private val headerLayout = HeaderLayout(context)
    private val viewPager = ViewPager2(context)
    private val emojiDataSource = EmojiDataSource()
    private val viewPagerAdapter = EmojiPickerPagerAdapter(::createAdapter, emojiDataSource)

    init {
        orientation = VERTICAL
        addView(headerLayout, makeLayoutParams())
        addView(viewPager, makeLayoutParams { weight = 1f })

        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(headerLayout.tabLayout, viewPager) { tab, position ->
            tab.setIcon(viewPagerAdapter.get(position).drawable)
            tab.view.setPadding(0, 0, 0, 0)
        }.attach()
    }

    private fun createAdapter(category: Category): EmojiAdapter {
        return EmojiAdapter(emojiDataSource, category)
    }
}