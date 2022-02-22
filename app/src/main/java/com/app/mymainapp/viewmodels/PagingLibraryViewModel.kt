package com.app.mymainapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.app.mymainapp.paging.UserPagingSource
import com.app.mymainapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagingLibraryViewModel @Inject constructor(
    private val mainRepository: AppRepository
) : ViewModel() {

    fun getUserListData() = Pager(config = PagingConfig(
        initialLoadSize = 10, pageSize = 10,
        prefetchDistance = 12, enablePlaceholders = false
    ),
        pagingSourceFactory = { UserPagingSource(mainRepository) }
    ).flow

}