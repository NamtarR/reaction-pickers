package com.namtarr.reactionpickers.emoji

import com.namtarr.reactionpickers.emoji.model.Category
import com.namtarr.reactionpickers.emoji.model.Emoji

class EmojiDataSource {

    fun categories() = Category.values().toList()

    fun emojis(category: Category): List<Emoji> = category.getEmoji().toList()
}