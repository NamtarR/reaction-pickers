package com.namtarr.reactionpickers.emoji.model

import com.namtarr.reactionpickers.emoji.R
import com.namtarr.reactionpickers.emoji.data.*

enum class Category(val drawable: Int) {
    PEOPLE(R.drawable.ic_emoji_people_faces),
    ANIMALS(R.drawable.ic_emoji_animals_nature),
    FOOD(R.drawable.ic_emoji_food_drink),
    ACTIVITY(R.drawable.ic_emoji_activity),
    TRAVEL(R.drawable.ic_emoji_travel_places),
    OBJECTS(R.drawable.ic_emoji_objects),
    SYMBOLS(R.drawable.ic_emoji_symbols),
    FLAGS(R.drawable.ic_emoji_flags);

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