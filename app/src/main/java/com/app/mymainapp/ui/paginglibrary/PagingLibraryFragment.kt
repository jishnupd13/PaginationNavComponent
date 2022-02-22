package com.app.mymainapp.ui.paginglibrary

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.mymainapp.R
import com.app.mymainapp.databinding.FragmentPagingLibraryBinding
import com.app.mymainapp.ui.adapters.paginationlibrary.LoadingAdapter
import com.app.mymainapp.ui.adapters.paginationlibrary.PaginationLibraryAdapter
import com.app.mymainapp.viewmodels.PagingLibraryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class PagingLibraryFragment : Fragment(R.layout.fragment_paging_library), View.OnClickListener {

    private val binding: FragmentPagingLibraryBinding by viewBinding()
    private val viewModel: PagingLibraryViewModel by viewModels()

    private lateinit var paginationLibraryAdapter: PaginationLibraryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.listner = this
        initView()
        observeData()
    }

    private fun initView() {
        paginationLibraryAdapter = PaginationLibraryAdapter()
        paginationLibraryAdapter.withLoadStateFooter(LoadingAdapter())
        binding.recyclerViewPagingLibrary.adapter = paginationLibraryAdapter
    }

    private fun observeData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getUserListData().collectLatest {
                paginationLibraryAdapter.submitData(it)
            }
        }
    }

    override fun onClick(view: View?) {

    }

}