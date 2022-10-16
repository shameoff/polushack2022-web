package com.example.polushackhatonproject.domain.main.usecase

import com.example.polushackhatonproject.data.repository.UserAuthentificationRepositoryImpl

class GetTokenUseCase(
    private val userAuthentificationRepositoryImpl: UserAuthentificationRepositoryImpl
) {
    fun execute(): String {
        return userAuthentificationRepositoryImpl.getLocalToken()
    }
}