package com.example.polushackhatonproject.presentation.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.polushackhatonproject.data.repository.UserAuthentificationRepositoryImpl
import com.example.polushackhatonproject.domain.signup.model.UserCredits

class SignUpActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userAuthentificationRepositoryImpl by lazy {
        UserAuthentificationRepositoryImpl(application.applicationContext)
    }

    fun saveUserCreditsToLocalStorage(email: String, password: String) {
        userAuthentificationRepositoryImpl.saveUserCredits(UserCredits(
            email,
            password
        ))
    }


}