package com.example.polushackhatonproject.presentation.main.fragment.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.polushackhatonproject.data.repository.MainUserRepositoryImpl
import com.example.polushackhatonproject.data.repository.UserAuthentificationRepositoryImpl
import com.example.polushackhatonproject.domain.main.model.Task
import com.example.polushackhatonproject.domain.main.usecase.GetTaskUseCase
import com.example.polushackhatonproject.domain.main.usecase.GetTokenUseCase
import kotlinx.coroutines.launch


class TaskFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val userAuthentificationRepositoryImpl by lazy {
        UserAuthentificationRepositoryImpl(application.applicationContext)
    }

    private val getTokenUseCase by lazy {
        GetTokenUseCase(userAuthentificationRepositoryImpl)
    }

    private val mainUserRepositoryImpl by lazy {
        MainUserRepositoryImpl(getTokenUseCase.execute())
    }

    private val getTaskUseCase by lazy {
        GetTaskUseCase(mainUserRepositoryImpl)
    }

    private val taskLiveData = MutableLiveData<Task>()

    private fun getTask() {
        viewModelScope.launch {
            val task = getTaskUseCase.execute()
            taskLiveData.value = task!!
        }
    }

    fun getTaskLiveData(): MutableLiveData<Task> {
        getTask()
        return taskLiveData
    }
}