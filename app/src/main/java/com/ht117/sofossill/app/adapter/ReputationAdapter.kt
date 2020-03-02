package com.ht117.sofossill.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ht117.sofossill.R
import com.ht117.sofossill.app.displayTime
import com.ht117.sofossill.app.formatNumber
import com.ht117.sofossill.data.model.ReputationModel
import kotlinx.android.synthetic.main.item_reputation.view.*

class ReputationAdapter: PagedListAdapter<ReputationModel, ReputationAdapter.ReputationHolder>(ReputationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReputationHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reputation, parent, false)
        return ReputationHolder(view)
    }

    override fun onBindViewHolder(holder: ReputationHolder, position: Int) {
        getItem(position)?.let { holder.bindView(it) }
    }

    inner class ReputationHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindView(model: ReputationModel) {
            with(model) {
                itemView.tvType.text = historyType.toUpperCase()
                itemView.tvChange.text = change.formatNumber()
                itemView.tvCreateAt.text = creationDate.displayTime()
                itemView.tvPostId.text = postId.toString()
            }
        }
    }

    class ReputationDiffCallback: DiffUtil.ItemCallback<ReputationModel>() {
        override fun areItemsTheSame(oldItem: ReputationModel, newItem: ReputationModel): Boolean {
            return oldItem.creationDate == newItem.creationDate &&
                    oldItem.postId == newItem.postId
        }

        override fun areContentsTheSame(oldItem: ReputationModel, newItem: ReputationModel): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }

    }
}