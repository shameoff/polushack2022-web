package com.example.polushackhatonproject.data.repository

import com.example.polushackhatonproject.data.model.CompletedTaskRequest
import com.example.polushackhatonproject.data.model.TaskResponse
import com.example.polushackhatonproject.data.model.UserProfileDataResponse
import com.example.polushackhatonproject.data.service.NetworkService
import com.example.polushackhatonproject.domain.main.model.Task
import com.example.polushackhatonproject.domain.repository.MainUserRepository
import retrofit2.Response

class MainUserRepositoryImpl(private val token: String) : MainUserRepository {
    override suspend fun getTask(): Response<TaskResponse> {
        return NetworkService.apiService.getTask(token)
    }

    override suspend fun getHistory(): Response<ArrayList<Task>> {
        return NetworkService.apiService.getTasksHistory(token)
    }

    override suspend fun putCompletedTask(
        completedTaskRequest: CompletedTaskRequest
    ): Response<Any> {
        return NetworkService.apiService.putCompletedTask(token, completedTaskRequest)
    }

    override suspend fun getUserProfileData(): Response<UserProfileDataResponse> {
        return NetworkService.apiService.getUserProfileData(token)
    }


}