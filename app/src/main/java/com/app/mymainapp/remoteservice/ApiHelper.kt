package com.app.mymainapp.remoteservice

import com.app.mymainapp.models.TestApiResponseModel
import com.app.mymainapp.models.users.UserProfileModel
import retrofit2.Response

interface ApiHelper {
    suspend fun getPosts(): Response<List<TestApiResponseModel>>
    suspend fun getUserProfile(page:Int):Response<UserProfileModel>
}