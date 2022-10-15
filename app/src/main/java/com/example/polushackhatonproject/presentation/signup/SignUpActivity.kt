package com.example.polushackhatonproject.presentation.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.polushackhatonproject.R
import com.example.polushackhatonproject.databinding.ActivitySignUpBinding
import com.example.polushackhatonproject.presentation.main.MainActivity
import kotlinx.coroutines.*

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

        binding.loginEditText.addTextChangedListener { updateButton() }
        binding.passwordEditText.addTextChangedListener { updateButton() }

        updateButton()

        binding.signupButton.setOnClickListener {
            if(!isHaveEmptyInputField() && isValidEntryData()) {
                CoroutineScope(Dispatchers.Main).launch {
                    createAnimationOnButtonClick()
                    startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun isHaveEmptyInputField(): Boolean {
        return binding.loginEditText.text.isEmpty() || binding.passwordEditText.text.isEmpty()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun updateButton() {
        if(isHaveEmptyInputField()) {
            binding.signupButton.background = resources.getDrawable(R.drawable.signup_unlocked_button)
            binding.signupButton.setTextColor(resources.getColor(R.color.hint))
            binding.signupButton.isClickable = false
        }
        else {
            binding.signupButton.background = resources.getDrawable(R.drawable.signup_locked_button)
            binding.signupButton.setTextColor(resources.getColor(R.color.white))
            binding.signupButton.isClickable = true
        }
    }

    private fun isValidEntryData(): Boolean {
        var result = true
        val email = binding.loginEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        viewModel.checkEntryDataValidity(email, password)
        val validationResult = viewModel.validatorResult.value!!
        if(validationResult.passwordErrId != null) {
            result = false
            binding.passwordErrorTextView.text =
                resources.getString(validationResult.passwordErrId)
        }
        else {
            binding.passwordErrorTextView.text = ""
        }
        if(validationResult.emailErrId != null) {
            result = false
            binding.loginErrorTextView.text =
                resources.getString(validationResult.emailErrId)
        }
        else {
            binding.loginErrorTextView.text = ""
        }
        return result
    }



    private suspend fun createAnimationOnButtonClick() {
        binding.logoImageView
            .animate()
            .translationY(-1000f)
            .setDuration(600)
            .start()
        delay(300)
        binding.signLayout.animate().translationY(1200f).setDuration(700).start()
        delay(600)
    }
}