package com.app.mymainapp.ui.adapters.paginationlibrary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.mymainapp.R
import com.app.mymainapp.databinding.ItemUserProfileBinding
import com.app.mymainapp.models.users.Data
import com.bumptech.glide.Glide


class PaginationLibraryAdapter : PagingDataAdapter<Data, RecyclerView.ViewHolder>(DiffUtils) {

    inner class UserProfileViewHolder(binding: ItemUserProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        val userHolder = holder as UserProfileViewHolder
        val binding = userHolder.itemBinding
        binding.item = data

        Glide.with(userHolder.itemBinding.imagePerson.context).load(data?.avatar)
            .into(binding.imagePerson)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemUserProfileBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user_profile, parent, false
        )
        return UserProfileViewHolder(binding)
    }

    private companion object DiffUtils : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem == newItem
        }
    }
}