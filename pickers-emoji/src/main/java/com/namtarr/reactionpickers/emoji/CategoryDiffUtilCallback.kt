package com.namtarr.reactionpickers.emoji

import androidx.recyclerview.widget.DiffUtil
import com.namtarr.reactionpickers.emoji.model.Category

object CategoryDiffUtilCallback: DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Category, newItem: Category) = true
}