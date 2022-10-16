package com.example.polushackhatonproject.presentation.main.fragment.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.polushackhatonproject.R
import com.example.polushackhatonproject.databinding.FragmentTaskBinding


class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    private val viewModel by lazy {
        TaskFragmentViewModel(activity?.application!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainView = inflater.inflate(R.layout.fragment_task, container, false)
        binding = FragmentTaskBinding.bind(mainView)


        return mainView
    }

    private fun onTaskLiveDataObserve() {
        viewModel.getTaskLiveData().observe(this) {
            // TODO: в it лежит Таск, так что перевели его во вью
        }
    }


}