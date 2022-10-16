package com.example.polushackhatonproject.presentation.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.polushackhatonproject.R
import com.example.polushackhatonproject.databinding.FragmentHistoryBinding
import com.example.polushackhatonproject.domain.main.model.TaskHistory


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainView = inflater.inflate(R.layout.fragment_history, container, false)
        binding = FragmentHistoryBinding.bind(mainView)

        binding.historyList.layoutManager = LinearLayoutManager(context)
        val items = listOf(
            TaskHistory(
                "task",
                "task tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask task",
                ""
            ),
            TaskHistory(
                "task",
                "task tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask task",
                ""
            ),
            TaskHistory(
                "task",
                "task tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask task",
                ""
            ),
            TaskHistory("task", "task task", ""),
            TaskHistory(
                "task",
                "task tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask task",
                ""
            ),
            TaskHistory(
                "task",
                "task tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask tasktask task",
                ""
            )
        )
        val adapter = HistoryAdapter(items)
        binding.historyList.adapter = adapter
        binding.historyList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        return mainView
    }
}