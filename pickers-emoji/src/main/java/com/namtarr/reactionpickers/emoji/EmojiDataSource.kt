package com.namtarr.reactionpickers.emoji

import com.namtarr.reactionpickers.emoji.model.Category
import com.namtarr.reactionpickers.emoji.model.Emoji

class EmojiDataSource {

    fun categories() = Category.values().toList()

    fun emojis(category: Category, query: String?): List<Emoji> {
        if (query.isNullOrBlank()) {
            return category.getEmoji().toList()
        }
        val results = categories().flatMap {
            it.getEmoji().filter { it.description.contains(query, ignoreCase = true) }
        }
        return results
    }
}