package com.app.mymainapp.ui.login

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.mymainapp.R
import com.app.mymainapp.databinding.FragmentLoginBinding
import com.app.mymainapp.utils.hideKeyboard
import com.app.mymainapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login), View.OnClickListener {

    private val binding: FragmentLoginBinding by viewBinding()
    private val viewModel: LoginViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = this
        binding.viewModel = viewModel
        observeAuthenticationStatus()
    }

    private fun observeAuthenticationStatus() {
        viewModel.authLiveData.observe(viewLifecycleOwner, {
            if (it)
                findNavController().navigate(R.id.global_action_to_state_fragment)
            else
                showToast("User authentication failure")
        })
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.buttonLogin -> {
                hideKeyboard()
                viewModel.getAuthStatus()
            }
        }
    }
}