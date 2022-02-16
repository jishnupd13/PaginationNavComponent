package com.app.mymainapp.ui.navigationcomponent

import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.mymainapp.databinding.ActivityNavigationComponentBinding
import com.app.mymainapp.viewmodels.NavigationComponentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationComponentActivity : AppCompatActivity(), View.OnClickListener {

    private val binding: ActivityNavigationComponentBinding by viewBinding()
    private val viewModel: NavigationComponentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.listener = this
      
    }

    override fun onClick(view: View?) {

    }
}