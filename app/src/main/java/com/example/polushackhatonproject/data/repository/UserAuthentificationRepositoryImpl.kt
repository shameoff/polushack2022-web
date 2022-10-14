package com.example.polushackhatonproject.data.repository

import android.content.Context
import com.example.polushackhatonproject.data.storage.SharedPreferencesStorage
import com.example.polushackhatonproject.domain.repository.UserAuthentificationRepository
import com.example.polushackhatonproject.domain.signup.model.UserCredits

class UserAuthentificationRepositoryImpl(context: Context) : UserAuthentificationRepository {

    private val localStorage = SharedPreferencesStorage(context)

    override fun postUserCredits(userCredits: UserCredits) {
        TODO("Not yet implemented")
    }

    override fun getUserCredits(): UserCredits {
        return localStorage.getData()
    }

    override fun saveUserCredits(userCredits: UserCredits) {
        localStorage.saveData(userCredits)
    }

    override fun checkUserCreditsExisting(): Boolean {
        return localStorage.ifAuthenticationPassed()
    }
}