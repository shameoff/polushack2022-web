package com.example.polushackhatonproject.domain.repository

import com.example.polushackhatonproject.data.model.CompletedTaskRequest
import com.example.polushackhatonproject.data.model.TaskResponse
import com.example.polushackhatonproject.data.model.UserProfileDataResponse
import com.example.polushackhatonproject.domain.main.model.Task
import retrofit2.Response

interface MainUserRepository {
    suspend fun getTask(token: String): Response<TaskResponse>

    suspend fun getHistory(token: String): Response<ArrayList<Task>>

    suspend fun putCompletedTask(token: String, completedTaskRequest: CompletedTaskRequest): Response<Any>

    suspend fun getUserProfileData(token: String): Response<UserProfileDataResponse>
}