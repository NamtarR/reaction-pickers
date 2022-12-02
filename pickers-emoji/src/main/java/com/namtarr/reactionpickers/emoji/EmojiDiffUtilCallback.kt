package com.namtarr.reactionpickers.emoji

import androidx.recyclerview.widget.DiffUtil
import com.namtarr.reactionpickers.emoji.model.Emoji

object EmojiDiffUtilCallback: DiffUtil.ItemCallback<Emoji>() {
    override fun areItemsTheSame(oldItem: Emoji, newItem: Emoji) = oldItem.emoji == newItem.emoji

    override fun areContentsTheSame(oldItem: Emoji, newItem: Emoji) = true
}