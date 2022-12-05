package com.namtarr.reactionpickers.emoji

import android.view.View
import android.view.ViewGroup
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
        return ViewHolder(EmojiView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as EmojiView).setEmoji(emoji[position].emoji)
    }

    override fun getItemId(position: Int) = getItem(position).emoji.hashCode().toLong()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}