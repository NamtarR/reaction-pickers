package com.namtarr.reactionpickers.core.extensions

import android.content.res.Resources
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams

fun makeLayoutParams(
    width: Int = LinearLayout.LayoutParams.MATCH_PARENT,
    height: Int = LinearLayout.LayoutParams.WRAP_CONTENT,
    marginTop: Int = 0,
    marginBottom: Int = 0,
    marginStart: Int = 0,
    marginEnd: Int = 0,
    block: LinearLayout.LayoutParams.() -> Unit = {}
): LinearLayout.LayoutParams {
    return LinearLayout.LayoutParams(width, height).apply {
        topMargin = marginTop
        bottomMargin = marginBottom
        setMarginStart(marginStart)
        setMarginEnd(marginEnd)
        block.invoke(this)
    }
}

val Int.dp: Int get() =
    (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.dp: Float get() =
    this * Resources.getSystem().displayMetrics.density