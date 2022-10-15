package com.example.polushackhatonproject.presentation.launch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.polushackhatonproject.data.repository.UserAuthentificationRepositoryImpl
import com.example.polushackhatonproject.domain.launch.usecase.CheckUserCreditExistingUseCase

class LaunchActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userAuthentificationRepositoryImpl by lazy {
        UserAuthentificationRepositoryImpl(application.applicationContext)
    }

    private val checkUserCreditExistingUseCase by lazy {
        CheckUserCreditExistingUseCase(userAuthentificationRepositoryImpl)
    }

    private val isUserCreditsCreated = MutableLiveData<Boolean>()

    private fun getCheckingResult() {
        val result = checkUserCreditExistingUseCase.execute()
        isUserCreditsCreated.value = result
    }

    fun getLiveData(): MutableLiveData<Boolean> {
        getCheckingResult()
        return isUserCreditsCreated
    }
}