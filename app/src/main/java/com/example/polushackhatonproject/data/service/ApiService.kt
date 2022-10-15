package com.example.polushackhatonproject.data.service

import com.example.polushackhatonproject.data.model.UserCreditsResponse
import com.example.polushackhatonproject.data.model.UserProfileDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("user/credits")
    fun getUserCredits(@Header("Bearer") token: String): Response<UserCreditsResponse>

    @GET("user/profile")
    fun getUserProfileData(@Header("Bearer") token: String): Response<UserProfileDataResponse>


}