package com.example.polushackhatonproject.data.storage

import com.example.polushackhatonproject.domain.signup.model.UserCredits

interface UserCreditsStorage {
    companion object {
        const val EMAIL_KEY = "userEmail"
        const val PASSWORD_KEY = "userPassword"
        const val TOKEN_KEY = "userToken"
        const val EMPTINESS_CASE = "nothing was found"
    }

    fun getUserCredits(): UserCredits

    fun ifAuthenticationPassed(): Boolean

    fun saveUserCredits(userCredits: UserCredits)

    fun saveToken(token: String)

    fun getToken(): String
}