package com.example.polushackhatonproject.domain.repository

import com.example.polushackhatonproject.domain.signup.model.UserCredits

interface UserAuthentificationRepository {
    fun postUserCredits(userCredits: UserCredits)

    fun getUserCredits(): UserCredits

    fun saveUserCredits(userCredits: UserCredits)

    fun checkUserCreditsExisting(): Boolean

}
