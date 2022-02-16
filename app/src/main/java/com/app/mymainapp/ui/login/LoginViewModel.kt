package com.app.mymainapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mymainapp.datastore.UserDataStore
import com.app.mymainapp.repository.AppRepository
import com.app.mymainapp.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: AppRepository
) : ViewModel() {

    var editTextPhoneNumber = SingleLiveEvent<String>()
    var editTextPassword = SingleLiveEvent<String>()


    private var _authStatus = SingleLiveEvent<Boolean>()
    val authLiveData: LiveData<Boolean>
        get() = _authStatus

    fun getAuthStatus() = viewModelScope.launch {
        val userDb = UserDataStore.getUserList()
        var status = false
        userDb.forEach { item ->
            if ((editTextPassword.value == item.password) && (editTextPhoneNumber.value == item.phoneNumber)) {
                status = true
            }
        }
        mainRepository.setAuthStatus(status)
        _authStatus.postValue(status)
    }
}