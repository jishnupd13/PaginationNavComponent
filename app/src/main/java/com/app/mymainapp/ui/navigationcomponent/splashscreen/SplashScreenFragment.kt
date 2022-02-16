package com.app.mymainapp.ui.navigationcomponent.splashscreen

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.mymainapp.R
import com.app.mymainapp.databinding.FragmentSplashScreenBinding
import com.app.mymainapp.viewmodels.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen), View.OnClickListener {

    private val binding: FragmentSplashScreenBinding by viewBinding()
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = this
        binding.viewModel = viewModel
        observeAuthToken()

    }

    private fun observeAuthToken(){
        viewModel.authLiveData.observe(viewLifecycleOwner,{
            if(!it)
                findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToAuthNavGraph())
            else
                findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToStateFragment())
        })
    }

    override fun onClick(view: View?) {

    }
}