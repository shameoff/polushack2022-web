package com.example.polushackhatonproject.domain.launch.usecase

import com.example.polushackhatonproject.data.repository.UserAuthentificationRepositoryImpl

class CheckTokenExpirationUseCase(
    private val userAuthentificationRepositoryImpl: UserAuthentificationRepositoryImpl
) {
    suspend fun execute(): Boolean {
        val token = userAuthentificationRepositoryImpl.getLocalToken()
        val response = userAuthentificationRepositoryImpl.getUserCredits(token)

        return !response.isSuccessful
    }
}