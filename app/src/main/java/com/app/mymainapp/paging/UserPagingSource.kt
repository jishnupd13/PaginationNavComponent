package com.app.mymainapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.mymainapp.baseresult.ResultWrapper
import com.app.mymainapp.models.users.Data
import com.app.mymainapp.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.wait

class UserPagingSource(
    private val repository: AppRepository
) : PagingSource<Int, Data>() {

    private var totalPages = 0

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val position = params.key ?: 1

        return try {

            withContext(Dispatchers.IO) {

                when(val response =   repository.getUserProfile(position)){

                    is ResultWrapper.Success->{
                        LoadResult.Page(
                            data = response.data.data ?: mutableListOf(),
                            prevKey = null,
                            nextKey = if (position >= totalPages) null else position + 1
                        )
                    }

                    is  ResultWrapper.Failure->{
                        LoadResult.Error(Throwable(response.message))
                    }
                }

            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}