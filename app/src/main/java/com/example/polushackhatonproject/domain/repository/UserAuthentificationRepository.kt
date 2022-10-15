package com.example.polushackhatonproject.domain.repository

import com.example.polushackhatonproject.data.model.UserCreditsResponse
import com.example.polushackhatonproject.domain.signup.model.UserCredits
import retrofit2.Response

interface UserAuthentificationRepository {

    suspend fun postUserCredits(userCredits: UserCredits): Response<UserCreditsResponse>

    fun getLocalUserCredits(): UserCredits

    fun saveLocalUserCredits(userCredits: UserCredits)

    fun checkUserCreditsExisting(): Boolean

    suspend fun getUserCredits(token: String): Response<UserCreditsResponse>

    fun saveLocalToken(token: String)

    fun getLocalToken(): String

}
