package com.example.polushackhatonproject.domain.main.usecase

import com.example.polushackhatonproject.data.model.TaskResponse
import com.example.polushackhatonproject.domain.main.model.Task
import com.example.polushackhatonproject.domain.repository.MainUserRepository
import java.util.Date

class GetTaskUseCase(private val mainUserRepository: MainUserRepository) {

    suspend fun execute(): Task? {
        val response = mainUserRepository.getTask()
        return if (response.isSuccessful) {
            convertTaskResponseToTask(response.body()!!)
        } else {
            null
        }
    }

    private fun convertTaskResponseToTask(taskResponse: TaskResponse): Task {
        return Task(
            name = taskResponse.carType + Date(taskResponse.timeFinish.time).toString(),
            description = taskResponse.description,
            timeStart = taskResponse.timeStart,
            timeFinish = taskResponse.timeFinish,
            carType = taskResponse.carType,
            carNumber = taskResponse.carNumber,
            latitude = taskResponse.latitude,
            longitude = taskResponse.longitude
        )
    }
}