package com.example.polushackhatonproject.presentation.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.polushackhatonproject.databinding.ActivitySignUpBinding
import com.example.polushackhatonproject.presentation.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySignUpBinding.inflate(this.layoutInflater)
    }

    private val viewModel by lazy {
        SignUpActivityViewModel(this.application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onSignUpButtonClick()
    }

    private fun onSignUpButtonClick() {
        binding.signupButton.setOnClickListener {
            viewModel.saveUserCreditsToLocalStorage(
                email = binding.loginEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )
            createAnimationOnButtonClick()
        }
    }

    private fun createAnimationOnButtonClick() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.signLayout.animate().translationY(1200f).setDuration(1300).start()
            delay(1100)
            binding.logoImageView.animate().rotation(720f).setDuration(1500).start()
            binding.logoImageView
                .animate()
                .translationY(-500f)
                .translationX(1500f)
                .setDuration(1500)
                .start()
            delay(1300)
            startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
            finish()
        }
    }
}