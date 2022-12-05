package com.namtarr.reactionpickers.emoji

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.emoji2.text.EmojiCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.namtarr.reactionpickers.emoji.model.Category
import com.namtarr.reactionpickers.emoji.model.Emoji
import com.namtarr.reactionpickers.emoji.views.EmojiView

internal class EmojiAdapter(
    private val dataSource: EmojiDataSource,
    private val category: Category
) : ListAdapter<Emoji, EmojiAdapter.ViewHolder>(EmojiDiffUtilCallback) {

    private val emoji = dataSource.emojis(category)

    init {
        setHasStableIds(true)
        submitList(emoji)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("EMOJIADAPTER", "creating view holder")
        return ViewHolder(EmojiView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as EmojiView).setEmoji(emoji[position].emoji)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}