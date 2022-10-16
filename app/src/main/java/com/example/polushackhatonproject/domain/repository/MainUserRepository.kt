package com.example.polushackhatonproject.domain.repository

import com.example.polushackhatonproject.data.model.CompletedTaskRequest
import com.example.polushackhatonproject.data.model.TaskResponse
import com.example.polushackhatonproject.data.model.UserProfileDataResponse
import com.example.polushackhatonproject.domain.main.model.Task
import retrofit2.Response

interface MainUserRepository {
    suspend fun getTask(): Response<TaskResponse>

    suspend fun getHistory(): Response<ArrayList<Task>>

    suspend fun putCompletedTask(completedTaskRequest: CompletedTaskRequest): Response<Any>

    suspend fun getUserProfileData(): Response<UserProfileDataResponse>
}