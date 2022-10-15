package com.example.polushackhatonproject.domain.launch.usecase

import com.example.polushackhatonproject.data.repository.UserAuthentificationRepositoryImpl

class CheckUserCreditExistingUseCase(
    private val userAuthentificationRepositoryImpl: UserAuthentificationRepositoryImpl
) {
    fun execute(): Boolean {
        return userAuthentificationRepositoryImpl.checkUserCreditsExisting()
    }
}