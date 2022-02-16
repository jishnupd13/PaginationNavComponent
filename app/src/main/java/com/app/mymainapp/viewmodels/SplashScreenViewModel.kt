package com.app.mymainapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mymainapp.repository.AppRepository
import com.app.mymainapp.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val mainRepository: AppRepository
) : ViewModel() {

    private var _auth_status_data = SingleLiveEvent<Boolean>()
    val  authLiveData: LiveData<Boolean>
    get() = _auth_status_data

    init {
        getAuthStatus()
    }

    fun getAuthStatus() = viewModelScope.launch {
        _auth_status_data.postValue(mainRepository.getAuthStatus())
    }

}