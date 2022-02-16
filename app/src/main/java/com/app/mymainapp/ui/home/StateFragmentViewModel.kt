package com.app.mymainapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mymainapp.baseresult.BaseResult
import com.app.mymainapp.baseresult.ResultWrapper
import com.app.mymainapp.models.users.Data
import com.app.mymainapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StateFragmentViewModel @Inject constructor(
    private val mainRepository: AppRepository
) : ViewModel() {

    var userProfileList = arrayListOf<Data>()
    var currentPage = MutableLiveData<Int>().apply {
        value = 1
    }

    var lastPage = MutableLiveData<Int>().apply {
        value = 1
    }

    var isPaginationEnabled = MutableLiveData<Boolean>().apply {
        value = false
    }


    private val _user_profile_response = MutableLiveData<BaseResult<List<Data>>>()
    val userProfileResponseLiveData: LiveData<BaseResult<List<Data>>>
        get() = _user_profile_response

    init {
        getUserProfile(currentPage.value ?: 1)
    }

    fun getUserProfile(page: Int) = viewModelScope.launch {

        when (val response = mainRepository.getUserProfile(page)) {
            is ResultWrapper.Success -> {
                lastPage.postValue(response.data.totalPages)
                Timber.e("currentPage08 ${currentPage.value}  lastPage ${lastPage.value}")

                if (isPaginationEnabled.value == true) {
                    //lets remove the last item
                    userProfileList.removeAt(userProfileList.size - 1)
                }

                response.data.data?.let { userProfileList.addAll(it) }
                _user_profile_response.postValue(
                    BaseResult.success(
                        userProfileList
                    )
                )

                Timber.e("currentPageValueBefore  ${currentPage.value}")

                val currentValue = (currentPage.value ?: 1) + 1
                currentPage.value = currentValue

                Timber.e("$currentValue currentPageValueAfter  ${currentPage.value}")

            }
            is ResultWrapper.Failure ->
                _user_profile_response.postValue(
                    BaseResult.error(
                        response.message
                    )
                )

        }
    }
}