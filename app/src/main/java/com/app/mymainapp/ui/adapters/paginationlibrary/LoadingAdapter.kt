package com.app.mymainapp.ui.adapters.paginationlibrary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.paging.LoadStateAdapter
import com.app.mymainapp.R


class LoadingAdapter : LoadStateAdapter<LoadingAdapter.LoadStateViewHolder>() {

    class LoadStateViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading, parent, false)
        )
    }

}