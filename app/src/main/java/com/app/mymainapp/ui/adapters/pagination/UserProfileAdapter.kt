package com.app.mymainapp.ui.adapters.pagination

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.mymainapp.R
import com.app.mymainapp.databinding.ItemPaginationBinding
import com.app.mymainapp.databinding.ItemUserProfileBinding
import com.app.mymainapp.listeners.OnItemClickListener
import com.app.mymainapp.models.users.Data
import com.app.mymainapp.models.users.PaginationStatus
import com.bumptech.glide.Glide
import timber.log.Timber

class UserProfileAdapter(val context: Context,val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class UserProfileViewHolder(binding: ItemUserProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
    }

    inner class PaginationViewHolder(binding: ItemPaginationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val binding: ItemUserProfileBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_user_profile, parent, false
            )
            UserProfileViewHolder(binding)
        } else {
            val binding: ItemPaginationBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_pagination, parent, false
            )
            PaginationViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserProfileViewHolder -> {
                val data = differ.currentList[position]
                Timber.e("hello")
                holder.itemBinding.apply {
                    Glide.with(context).load(data.avatar).into(imagePerson)
                    item = data
                    listener = onItemClickListener
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (differ.currentList[position].paginationStatus == PaginationStatus.NORMAL) {
            0
        } else {
            1
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Data>() {
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

    val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<Data>) {
        differ.submitList(list)
    }
}