package com.example.polushackhatonproject.data.service

import com.example.polushackhatonproject.data.model.TaskResponse
import com.example.polushackhatonproject.data.model.UserDataResponse
import com.example.polushackhatonproject.domain.signup.model.UserCredits
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("user/?role")
    fun getUserData(@Body userCredits: UserCredits): ApiResponse<UserDataResponse>

    @GET("request/:id")
    fun getTask(): ApiResponse<TaskResponse>

}