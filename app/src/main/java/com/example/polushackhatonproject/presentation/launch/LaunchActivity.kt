package com.example.polushackhatonproject.presentation.launch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.polushackhatonproject.R
import com.example.polushackhatonproject.presentation.signup.SignUpActivity

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        startActivity(Intent(this, SignUpActivity::class.java))
        finish()
    }
}