package com.namtarr.reactionpickers.core.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.namtarr.reactionpickers.core.extensions.makeLayoutParams

abstract class PickerView<Category, Item> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {

}