package com.rakib.commitviewapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.rakib.commitviewapp.databinding.ItemCommitBinding
import com.rakib.commitviewapp.repository.model.CommitModelItem
import com.rakib.commitviewapp.utils.SimpleCallback
import com.rakib.commitviewapp.utils.TimeUtils
import javax.inject.Inject

class CommitViewAdapter @Inject constructor() :
    PagingDataAdapter<CommitModelItem, CommitViewAdapter.CommitViewHolder>(Comparator) {

    var clickListener: SimpleCallback<CommitModelItem>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        val layOutBinding =
            ItemCommitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommitViewHolder(layOutBinding)
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        getItem(position)?.let {
            val inputFormat = "yyyy-MM-dd'T'HH:mm:ss"
            val outputFormat = "MMM dd, yyyy"
            val formattedDate: String =
                TimeUtils.formatDateFromString(it.commit.author.date, inputFormat, outputFormat)
            holder.binding.txtCommitValue.text = it.commit.message
            holder.binding.dateTime.text = formattedDate
            holder.binding.authorName.text = it.commit.author.name
            Glide
                .with(holder.binding.root)
                .load(it.committer.avatar_url)
                .centerCrop()
                .into(holder.binding.imgProfile)
//            Glide
//                .with(holder.binding.root)
//                .load(it.committer.events_url)
//                .centerCrop()
//                .into(holder.binding.imageMark);
            holder.binding.root.setOnClickListener { view -> clickListener?.callback(it) }


        }

    }

    inner class CommitViewHolder(val binding: ItemCommitBinding) : ViewHolder(binding.root) {
    }

    companion object {
        private val Comparator =
            object : DiffUtil.ItemCallback<CommitModelItem>() {
                override fun areItemsTheSame(
                    oldItem: CommitModelItem,
                    newItem: CommitModelItem
                ): Boolean {
                    return oldItem.node_id == newItem.node_id
                }

                override fun areContentsTheSame(
                    oldItem: CommitModelItem,
                    newItem: CommitModelItem
                ): Boolean {
                    return oldItem == newItem
                }

            }

    }
}