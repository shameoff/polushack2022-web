package com.example.polushackhatonproject.presentation.launch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.polushackhatonproject.data.repository.UserAuthentificationRepositoryImpl
import com.example.polushackhatonproject.domain.launch.usecase.CheckTokenExpirationUseCase
import com.example.polushackhatonproject.domain.launch.usecase.CheckUserCreditExistingUseCase
import kotlinx.coroutines.launch

class LaunchActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userAuthentificationRepositoryImpl by lazy {
        UserAuthentificationRepositoryImpl(application.applicationContext)
    }

    private val checkUserCreditExistingUseCase by lazy {
        CheckUserCreditExistingUseCase(userAuthentificationRepositoryImpl)
    }

    private val checkTokenExpirationUseCase by lazy {
        CheckTokenExpirationUseCase(userAuthentificationRepositoryImpl)
    }

    private val isUserCreditsCreatedLiveData = MutableLiveData<Boolean>()

    private fun getUserCreditsCheckingResult() {
        val result = checkUserCreditExistingUseCase.execute()
        isUserCreditsCreatedLiveData.value = result
    }

    fun getIsUserCreditsCreatedLiveData(): MutableLiveData<Boolean> {
        getUserCreditsCheckingResult()
        return isUserCreditsCreatedLiveData
    }

    private val isTokenExpiredLiveData = MutableLiveData<Boolean>()

    private fun getTokenCheckingResults() {
        viewModelScope.launch {
            val result = checkTokenExpirationUseCase.execute()
            isTokenExpiredLiveData.value = result
        }
    }

    fun getIsTokenExpiredLiveData(): MutableLiveData<Boolean> {
        getTokenCheckingResults()
        return isTokenExpiredLiveData
    }

}