package com.example.polushackhatonproject.data.repository

import android.content.Context
import com.example.polushackhatonproject.data.model.UserCreditsResponse
import com.example.polushackhatonproject.data.service.NetworkService
import com.example.polushackhatonproject.data.storage.SharedPreferencesStorage
import com.example.polushackhatonproject.domain.repository.UserAuthentificationRepository
import com.example.polushackhatonproject.domain.signup.model.UserCredits
import retrofit2.Response

class UserAuthentificationRepositoryImpl(context: Context) : UserAuthentificationRepository {

    private val localStorage = SharedPreferencesStorage(context)


    override fun postUserCredits(userCredits: UserCredits): Response<UserCreditsResponse> {
        return NetworkService.apiService.postUserCredits(userCredits)
    }

    override fun getLocalUserCredits(): UserCredits {
        return localStorage.getUserCredits()
    }

    override fun saveLocalUserCredits(userCredits: UserCredits) {
        localStorage.saveUserCredits(userCredits)
    }

    override fun checkUserCreditsExisting(): Boolean {
        return localStorage.ifAuthenticationPassed()
    }

    override fun getUserCredits(token: String): Response<UserCreditsResponse> {
        return NetworkService.apiService.getUserCredits(token)
    }

    override fun saveLocalToken(token: String) {
        localStorage.saveToken(token)
    }

    override fun getLocalToken(): String {
        return localStorage.getToken()
    }
}