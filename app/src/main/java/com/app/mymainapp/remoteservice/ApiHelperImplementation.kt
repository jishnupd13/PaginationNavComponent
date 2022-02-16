package com.app.mymainapp.remoteservice

import com.app.mymainapp.models.TestApiResponseModel
import com.app.mymainapp.models.users.UserProfileModel
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImplementation @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun getPosts(): Response<List<TestApiResponseModel>> = apiService.getPosts()
    override suspend fun getUserProfile(page: Int): Response<UserProfileModel> = apiService.getUserProfile(page)
}