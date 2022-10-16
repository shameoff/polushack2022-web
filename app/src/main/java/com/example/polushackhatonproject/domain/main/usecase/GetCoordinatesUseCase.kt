package com.example.polushackhatonproject.domain.main.usecase

import com.example.polushackhatonproject.domain.main.model.Coordinate
import com.example.polushackhatonproject.domain.repository.MainUserRepository

class GetCoordinatesUseCase(private val mainUserRepository: MainUserRepository) {
    suspend fun execute(): Coordinate? {
        val response = mainUserRepository.getTask()
        return if (response.isSuccessful) {
            val task = response.body()!!
            Coordinate(task.latitude, task.longitude)
        } else {
            null
        }
    }
}