package com.namtarr.reactionpickers.emoji.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.emoji2.text.EmojiSpan

internal class EmojiView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val textPaint = TextPaint().apply {
        color = Color.BLACK
        isAntiAlias = true
        textSize = 60f
        textAlign = Paint.Align.CENTER
    }

    private var ascent = textPaint.ascent()
    private var descent = textPaint.descent()
    private var ascentInt = ascent.toInt()
    private var descentInt = descent.toInt()

    private var xPosition: Float = 0f
    private var yPosition: Float = 0f
    private var yPositionInt: Int = yPosition.toInt()

    private var emojiSpan: EmojiSpan? = null
    private var emojiCharacter: CharSequence = ""

    private fun setTextSize(size: Float) {
        textPaint.textSize = size
        ascent = textPaint.ascent()
        descent = textPaint.descent()
        ascentInt = ascent.toInt()
        descentInt = descent.toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
        xPosition = measuredWidth / 2f
        yPosition = measuredHeight / 2f - (descent + ascent) / 2f
        yPositionInt = yPosition.toInt()
        setTextSize(measuredWidth / 2f)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        // Draw native emoji
        if (emojiSpan == null) {
            canvas.drawText(
                emojiCharacter, 0, emojiCharacter.length,
                xPosition, yPosition, textPaint
            )
            return
        }

        // Draw replaced emoji
        emojiSpan?.draw(
            canvas, emojiCharacter, 0, emojiCharacter.length,
            xPosition, ascentInt, yPositionInt, descentInt, textPaint
        )
    }

    fun setEmoji(title: CharSequence) {
        emojiCharacter = title
        invalidate()
    }
}