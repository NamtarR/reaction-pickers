package com.namtarr.reactionpickers.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams
import com.google.android.material.tabs.TabLayout
import com.namtarr.reactionpickers.core.extensions.dp
import com.namtarr.reactionpickers.core.extensions.makeLayoutParams

class HeaderLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val searchView = SearchView(context)
    val tabLayout = TabLayout(context)

    init {
        orientation = HORIZONTAL

        addView(searchView, makeLayoutParams(width = LayoutParams.WRAP_CONTENT))
        addView(tabLayout, makeLayoutParams(height = LayoutParams.MATCH_PARENT, marginStart = 8.dp))

        searchView.setOnSearchExpand {
            tabLayout.visibility = View.GONE
            searchView.updateLayoutParams<LayoutParams> {
                width = LayoutParams.MATCH_PARENT
            }
        }

        searchView.setOnSearchCollapse {
            tabLayout.visibility = View.VISIBLE
            searchView.updateLayoutParams<LayoutParams> {
                width = LayoutParams.WRAP_CONTENT
            }
        }
    }
}