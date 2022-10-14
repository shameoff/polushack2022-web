package com.example.polushackhatonproject.data.storage

import com.example.polushackhatonproject.domain.signup.model.UserCredits

interface UserCreditsStorage {
    companion object {
        const val EMAIL_KEY = "userEmail"
        const val PASSWORD_KEY = "userPassword"
        const val EMPTINESS_CASE = "nothing was found"
    }

    fun getData(): UserCredits

    fun ifAuthenticationPassed(): Boolean

    fun saveData(userCredits: UserCredits)
}