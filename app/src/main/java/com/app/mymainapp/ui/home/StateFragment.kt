package com.app.mymainapp.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.mymainapp.R
import com.app.mymainapp.baseresult.BaseResult
import com.app.mymainapp.databinding.FragmentStateBinding
import com.app.mymainapp.listeners.OnItemClickListener
import com.app.mymainapp.models.users.Data
import com.app.mymainapp.models.users.PaginationStatus
import com.app.mymainapp.ui.adapters.pagination.UserProfileAdapter
import com.app.mymainapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class StateFragment : Fragment(R.layout.fragment_state), View.OnClickListener {

    private val binding: FragmentStateBinding by viewBinding()
    private val viewModel: StateFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = this
        binding.viewModel = viewModel
        initView()
    }

    private fun initView() {

        val listener=object :OnItemClickListener{
            override fun onItemClick(key: String, item: Any) {
                findNavController().navigate(StateFragmentDirections.actionStateFragmentToProfileFragment())
            }

        }

        val userProfileAdapter = UserProfileAdapter(requireContext(),listener)
        binding.recyclerViewUser.adapter = userProfileAdapter

        viewModel.userProfileResponseLiveData.observe(this, Observer {

            when (it.status) {
                BaseResult.Status.SUCCESS -> {
                    it.data?.let { it1 -> userProfileAdapter.submitList(it1) }
                    viewModel.isPaginationEnabled.value = false
                }
                BaseResult.Status.ERROR -> {
                    Timber.e("error ${it.message}")
                    showToast(it.message ?: "")
                }
                BaseResult.Status.LOADING -> {

                }
            }
        })

        binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v?.getChildAt(0)?.measuredHeight?.minus(v.measuredHeight)) {
                if (viewModel.isPaginationEnabled.value == false) {
                    viewModel.isPaginationEnabled.value = true
                    if (viewModel.currentPage.value ?: 1 <= viewModel.lastPage.value ?: 1) {
                        viewModel.userProfileList.add(Data(paginationStatus = PaginationStatus.LOADING))
                        userProfileAdapter.submitList(viewModel.userProfileList.map { it.copy() })
                        userProfileAdapter.notifyItemChanged((viewModel.userProfileList.size - 1))
                        Handler(Looper.getMainLooper()).postDelayed({
                            viewModel.getUserProfile(viewModel.currentPage.value ?: 1)
                        }, 1000)
                    }
                }
            }
        })
    }

    override fun onClick(view: View?) {

    }
}