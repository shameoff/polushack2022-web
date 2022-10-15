package com.example.polushackhatonproject.presentation.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.polushackhatonproject.data.repository.UserAuthentificationRepositoryImpl
import com.example.polushackhatonproject.domain.signup.model.UserCredits
import com.example.polushackhatonproject.domain.signup.model.ValidatorData
import com.example.polushackhatonproject.domain.signup.model.ValidatorResult
import com.example.polushackhatonproject.domain.signup.usecase.ValidateEntryDataUseCase
import com.example.polushackhatonproject.domain.signup.validator.SignUpValidator

class SignUpActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val validationResultLive: MutableLiveData<ValidatorResult> = MutableLiveData()
    val validatorResult: LiveData<ValidatorResult> = validationResultLive

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

    fun checkEntryDataValidity(email: String, password: String){
        var res = validateEntryDataUseCase.execute(ValidatorData(email, password))
        if(res.emailErrId == null && res.passwordErrId == null) {
            // TODO:: add validation in server
        }
        validationResultLive.value = res
    }


}