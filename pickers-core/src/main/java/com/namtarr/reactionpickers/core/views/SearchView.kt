package com.namtarr.reactionpickers.core.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.namtarr.reactionpickers.core.extensions.dp
import com.namtarr.reactionpickers.core.extensions.makeLayoutParams

class SearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val inputLayout: TextInputLayout
    private val editText: TextInputEditText
    private val button: AppCompatImageButton

    private var onExpandListener: (() -> Unit)? = null
    private var onCollapseListener: (() -> Unit)? = null

    init {
        orientation = HORIZONTAL
        setBackgroundColor(Color.WHITE)

        button = makeButton()
        addView(button, makeLayoutParams(48.dp, 48.dp))

        inputLayout = makeTextInput()
        addView(
            inputLayout,
            makeLayoutParams(0, 40.dp, 4.dp, 4.dp, 4.dp, 4.dp) { weight = 1f }
        )
        inputLayout.visibility = View.GONE

        editText = makeEditText()
        inputLayout.addView(
            editText,
            makeLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        )

        button.setOnClickListener {
            inputLayout.visibility = View.VISIBLE
            button.visibility = View.GONE
            editText.requestFocus()
            onExpandListener?.invoke()
        }

        inputLayout.setEndIconOnClickListener {
            inputLayout.visibility = View.GONE
            button.visibility = View.VISIBLE
            editText.clearFocus()
            onCollapseListener?.invoke()
        }
    }

    private fun makeButton(): AppCompatImageButton {
        return AppCompatImageButton(context).apply {
            background = null
            setImageResource(androidx.appcompat.R.drawable.abc_ic_search_api_material)
            scaleType = ImageView.ScaleType.CENTER
            imageTintList = ColorStateList.valueOf(Color.CYAN)
            background = RippleDrawable(ColorStateList.valueOf(Color.LTGRAY), null, null)
        }
    }

    private fun makeTextInput(): TextInputLayout {
        return TextInputLayout(context).apply {
            boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
            boxBackgroundColor = Color.TRANSPARENT
            isHintEnabled = false
            boxCollapsedPaddingTop = 0
            boxStrokeColor = Color.CYAN
            boxStrokeWidth = 1.dp
            setBoxCornerRadii(4f.dp, 4f.dp, 4f.dp, 4f.dp)
            endIconMode = TextInputLayout.END_ICON_CUSTOM
            setEndIconDrawable(androidx.appcompat.R.drawable.abc_ic_clear_material)
        }
    }

    private fun makeEditText(): TextInputEditText {
        return TextInputEditText(context).apply {
            setPadding(12.dp, 0, 12.dp, 0)
            background = null
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            isSingleLine = true
            imeOptions = EditorInfo.IME_ACTION_SEARCH
            hint = "Search"
        }
    }

    fun setOnSearchExpand(onExpand: () -> Unit) {
        onExpandListener = onExpand
    }

    fun setOnSearchCollapse(onCollapse: () -> Unit) {
        onCollapseListener = onCollapse
    }

    fun setOnSearch(onSearch: (String?) -> Unit) {
        editText.addTextChangedListener {
            onSearch.invoke(it?.toString())
        }
    }

    fun clearQuery() {
        editText.text = null
    }
}