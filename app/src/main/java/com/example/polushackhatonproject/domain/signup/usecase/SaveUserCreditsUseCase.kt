package com.example.polushackhatonproject.domain.signup.usecase

import com.example.polushackhatonproject.data.repository.UserAuthentificationRepositoryImpl
import com.example.polushackhatonproject.domain.signup.model.UserCredits

class SaveUserCreditsUseCase(
    private val userAuthentificationRepositoryImpl: UserAuthentificationRepositoryImpl
) {
    fun execute(userCredits: UserCredits) {
        userAuthentificationRepositoryImpl.saveLocalUserCredits(userCredits)
    }
}