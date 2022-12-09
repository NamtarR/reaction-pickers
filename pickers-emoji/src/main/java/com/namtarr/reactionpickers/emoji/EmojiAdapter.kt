package com.namtarr.reactionpickers.emoji

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.namtarr.reactionpickers.emoji.model.Emoji
import com.namtarr.reactionpickers.emoji.views.EmojiView

internal class EmojiAdapter : ListAdapter<Emoji, EmojiAdapter.ViewHolder>(EmojiDiffUtilCallback) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EmojiView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as EmojiView).setEmoji(getItem(position).emoji)
    }

    override fun getItemId(position: Int) = getItem(position).emoji.hashCode().toLong()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}