package com.example.polushackhatonproject.presentation.launch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.polushackhatonproject.databinding.ActivityLaunchBinding
import com.example.polushackhatonproject.presentation.main.MainActivity
import com.example.polushackhatonproject.presentation.signup.SignUpActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LaunchActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLaunchBinding.inflate(this.layoutInflater)
    }

    private val viewModel by lazy {
        LaunchActivityViewModel(this.application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.logoImageView.animate().rotation(720f).setDuration(1500).start()
        onObserveLiveData()
    }

    private fun onObserveLiveData() {
        viewModel.getLiveData().observe(this) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(1600)
                makeIntent(it)
                finish()
            }
        }
    }

    private fun makeIntent(checkIfAuthenticationPassed: Boolean) {
        if (checkIfAuthenticationPassed) {
            // TODO: del code for test and uncommitted main code
            // for test
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
            // main code
            //startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
            //finish()
        } else {
            startActivity(Intent(this@LaunchActivity, SignUpActivity::class.java))
            finish()
        }
    }
}