package com.example.polushackhatonproject.presentation.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.polushackhatonproject.data.repository.UserAuthentificationRepositoryImpl
import com.example.polushackhatonproject.domain.signup.model.UserCredits
import com.example.polushackhatonproject.domain.signup.model.ValidatorData
import com.example.polushackhatonproject.domain.signup.model.ValidatorResult
import com.example.polushackhatonproject.domain.signup.usecase.ValidateEntryDataUseCase
import com.example.polushackhatonproject.domain.signup.validator.SignUpValidator

class SignUpActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val validationResultLiveData = MutableLiveData<ValidatorResult>()

    private val userAuthentificationRepositoryImpl by lazy {
        UserAuthentificationRepositoryImpl(application.applicationContext)
    }

    private val validateEntryDataUseCase by lazy {
        ValidateEntryDataUseCase(
            SignUpValidator()
        )
    }

    fun saveUserCreditsToLocalStorage(email: String, password: String) {
        userAuthentificationRepositoryImpl.saveUserCredits(
            UserCredits(
                email,
                password
            )
        )
    }

    fun checkEntryDataValidity(email: String, password: String) {
        val res = validateEntryDataUseCase.execute(ValidatorData(email, password))
        if (res.emailResultId == null && res.passwordResultId == null) {
            // TODO:: add validation in server
        }
        validationResultLiveData.value = res
    }

    fun getValidationResultLiveData(): MutableLiveData<ValidatorResult> {
        return validationResultLiveData
    }


}