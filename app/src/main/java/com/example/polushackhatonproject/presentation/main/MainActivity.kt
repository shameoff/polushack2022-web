package com.example.polushackhatonproject.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.polushackhatonproject.R
import com.example.polushackhatonproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
