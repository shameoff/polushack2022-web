package com.example.polushackhatonproject.presentation.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.polushackhatonproject.data.repository.UserAuthentificationRepositoryImpl
import com.example.polushackhatonproject.domain.signup.model.UserCredits
import com.example.polushackhatonproject.domain.signup.usecase.ValidateEntryDataUseCase

class SignUpActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userAuthentificationRepositoryImpl by lazy {
        UserAuthentificationRepositoryImpl(application.applicationContext)
    }

    private val validateEntryDataUseCase by lazy {
        ValidateEntryDataUseCase()
    }

    fun saveUserCreditsToLocalStorage(email: String, password: String) {
        userAuthentificationRepositoryImpl.saveUserCredits(
            UserCredits(
                email,
                password
            )
        )
    }

    fun checkEntryDataValidity(email: String, password: String): Int {
        return validateEntryDataUseCase.execute(email, password)
    }


}