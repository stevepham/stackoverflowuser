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
import com.ht117.sofossill.app.loadAvatar
import com.ht117.sofossill.data.model.UserModel
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(private val callback: ((UserModel) -> Unit)? = null,
                  private val bookmarkCallback: ((Long, Boolean) -> Unit)? = null):
    PagedListAdapter<UserModel, UserAdapter.UserHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        getItem(position)?.let { holder.bindView(it) }
    }

    inner class UserHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindView(user: UserModel) {
            with(user) {
                itemView.tvUser.text = displayName
                itemView.tvReputation.text = reputation?.formatNumber()
                itemView.tvLastAccess.text = lastAccessDate?.displayTime()
                itemView.tvLocation.text = location?: itemView.context.getString(R.string.unknown)
                itemView.ivAvatar.loadAvatar(profileImage)
                itemView.ivBookmarked.isSelected = isBookmarked
                itemView.ivBookmarked.setOnClickListener {
                    itemView.ivBookmarked.isSelected = !itemView.ivBookmarked.isSelected
                    bookmarkCallback?.invoke(userId, itemView.ivBookmarked.isSelected)
                }
                itemView.setOnClickListener {
                    callback?.invoke(this)
                }
            }
        }
    }

    class UserDiffCallback: DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.userId == newItem.userId
        }

    }
}