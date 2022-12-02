package com.namtarr.reactionpickers.emoji

import com.namtarr.reactionpickers.emoji.model.Category
import com.namtarr.reactionpickers.emoji.model.Emoji

internal class EmojiPickerDataSource {

    suspend fun groups(): List<Category> {
        TODO("Not yet implemented")
    }

    suspend fun items(category: Category, query: String?): List<Emoji> {
        TODO("Not yet implemented")
    }
}