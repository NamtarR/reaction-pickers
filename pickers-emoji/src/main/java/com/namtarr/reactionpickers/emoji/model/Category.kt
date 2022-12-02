package com.namtarr.reactionpickers.emoji.model

import com.namtarr.reactionpickers.emoji.data.*

enum class Category(val titleEmoji: String, val title: String) {
    PEOPLE("\uD83D\uDE03", "People and faces"),
    ANIMALS("\uD83D\uDC3B", "Animals and nature"),
    FOOD("\uD83C\uDF54", "Food and drink"),
    ACTIVITY("\uD83C\uDFC0", "Activity"),
    TRAVEL("\uD83D\uDE98", "Travel and places"),
    OBJECTS("\uD83D\uDCA1", "Objects"),
    SYMBOLS("\uD83D\uDC96", "Symbols"),
    FLAGS("\uD83C\uDFF3", "Flags");

    fun getEmoji(): Array<Emoji> {
        return when (this) {
            PEOPLE -> smileysAndPeople
            ANIMALS -> animalsAndNature
            FOOD -> foodAndDrink
            ACTIVITY -> activity
            TRAVEL -> travelAndPlaces
            OBJECTS -> objects
            SYMBOLS -> symbols
            FLAGS -> flags
        }
    }
}