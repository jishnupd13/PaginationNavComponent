package com.app.mymainapp.remoteservice

import com.app.mymainapp.models.TestApiResponseModel
import com.app.mymainapp.models.users.UserProfileModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<TestApiResponseModel>>

    @GET("users")
    suspend fun getUserProfile(
        @Query("page") page: Int? = 1
    ):Response<UserProfileModel>
}