package com.example.polushackhatonproject.domain.mapper

import com.example.polushackhatonproject.data.model.TaskResponse
import com.example.polushackhatonproject.domain.main.model.Task
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ApiSuccessModelMapper

class SuccessTaskMapper : ApiSuccessModelMapper<TaskResponse, Task> {
    override fun map(apiErrorResponse: ApiResponse.Success<TaskResponse>): Task {
        val response = apiErrorResponse.data
        return Task(response.name, response.description)
    }
}