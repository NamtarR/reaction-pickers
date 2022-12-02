package com.namtarr.reactionpickers.emoji

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
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
    private val viewPager = ViewPager(context)
    private val viewPagerAdapter = EmojiPickerPagerAdapter(::createAdapter)
    private val emojiDataSource = EmojiDataSource()

    init {
        addView(headerLayout, makeLayoutParams())
        addView(viewPager, makeLayoutParams { weight = 1f })

        viewPager.adapter = viewPagerAdapter
    }

    private fun createAdapter(category: Category): EmojiAdapter {
        return EmojiAdapter(emojiDataSource, category)
    }
}