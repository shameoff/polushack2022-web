package com.example.polushackhatonproject.data.service

import com.example.polushackhatonproject.data.model.DoneTaskRequest
import com.example.polushackhatonproject.data.model.TaskResponse
import com.example.polushackhatonproject.data.model.UserCreditsResponse
import com.example.polushackhatonproject.data.model.UserProfileDataResponse
import com.example.polushackhatonproject.domain.main.model.Task
import com.example.polushackhatonproject.domain.signup.model.UserCredits
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    companion object {
        const val BASE_URL = "baseurl"
    }

    @GET("user/credits")
    suspend fun getUserCredits(@Header("Bearer") token: String): Response<UserCreditsResponse>

    @GET("user/profile")
    suspend fun getUserProfileData(@Header("Bearer") token: String): Response<UserProfileDataResponse>

    @GET("user/request")
    suspend fun getTask(@Header("Bearer") token: String): Response<TaskResponse>

    @GET("user/history")
    suspend fun getTasksHistory(@Header("Bearer") token: String): Response<ArrayList<Task>>

    @POST("user")
    suspend fun postUserCredits(@Body userCredits: UserCredits): Response<UserCreditsResponse>

    @PUT("user/request/complete")
    suspend fun putDoneTask(
        @Header("Bearer") token: String,
        @Body doneTaskRequest: DoneTaskRequest
    ): Response<Any>


}