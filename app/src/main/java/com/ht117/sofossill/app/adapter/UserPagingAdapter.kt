package com.ht117.sofossill.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ht117.sofossill.R
import com.ht117.sofossill.data.model.UserModel
import kotlinx.android.synthetic.main.item_user.view.*

class UserPagingAdapter(private val callback: ((UserModel?) -> Unit)?): PagingDataAdapter<UserModel, UserPagingAdapter.UserHolder>(DIFFER) {

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserHolder(view)
    }

    inner class UserHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindData(model: UserModel?) {
            model?.run {
                Glide.with(itemView.context)
                    .load(profileImage)
                    .into(itemView.ivAvatar)

                itemView.tvUser.text = displayName
                itemView.tvReputation.text = "$reputation"
                itemView.tvLocation.text = location
            }
            itemView.setOnClickListener {
                callback?.invoke(model)
            }
        }
    }

    companion object {
        val DIFFER = object : DiffUtil.ItemCallback<UserModel>() {
            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
               return oldItem == newItem
            }
        }
    }
}