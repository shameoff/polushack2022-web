package com.example.polushackhatonproject.presentation.main.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.polushackhatonproject.data.repository.MainUserRepositoryImpl
import com.example.polushackhatonproject.data.repository.UserAuthentificationRepositoryImpl
import com.example.polushackhatonproject.domain.main.model.Coordinate
import com.example.polushackhatonproject.domain.main.usecase.GetCoordinatesUseCase
import com.example.polushackhatonproject.domain.main.usecase.GetTokenUseCase
import kotlinx.coroutines.launch

class MapFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val userAuthentificationRepositoryImpl by lazy {
        UserAuthentificationRepositoryImpl(application.applicationContext)
    }

    private val getTokenUseCase by lazy {
        GetTokenUseCase(userAuthentificationRepositoryImpl)
    }

    private val mainUserRepositoryImpl by lazy {
        MainUserRepositoryImpl(getTokenUseCase.execute())
    }

    private val getCoordinatesUseCase by lazy {
        GetCoordinatesUseCase(mainUserRepositoryImpl)
    }

    private val coordinatesLiveData = MutableLiveData<Coordinate>()

    private fun getTask() {
        viewModelScope.launch {
            val coordinates = getCoordinatesUseCase.execute()
            coordinatesLiveData.value = coordinates!!
        }
    }

    fun getCoordinatesLiveData(): MutableLiveData<Coordinate> {
        getTask()
        return coordinatesLiveData
    }
}