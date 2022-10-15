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
        onSignUpButtonClick()

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun updateButton() {
        if (checkEmptyFieldsExisting()) {
            binding.signupButton.background = resources.getDrawable(
                R.drawable.signup_unlocked_button,
                this.theme
            )

            binding.signupButton.setTextColor(resources.getColor(R.color.hint, this.theme))
            binding.signupButton.isClickable = false
        } else {
            binding.signupButton.background = resources.getDrawable(
                R.drawable.signup_locked_button,
                this.theme
            )

            binding.signupButton.setTextColor(resources.getColor(R.color.white, this.theme))
            binding.signupButton.isClickable = true
        }
    }

    private fun onSignUpButtonClick() {
        binding.signupButton.setOnClickListener {
            if (!checkEmptyFieldsExisting() && isEntryDataValid()) {
                viewModel.saveUserCreditsToLocalStorage(
                    binding.loginEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
                CoroutineScope(Dispatchers.Main).launch {
                    createAnimationOnButtonClick()
                    startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
    }


    private fun checkEmptyFieldsExisting(): Boolean {
        return binding.loginEditText.text.isEmpty() || binding.passwordEditText.text.isEmpty()
    }

    private fun isEntryDataValid(): Boolean {
        var result = true

        val email = binding.loginEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        viewModel.checkEntryDataValidity(email, password)
        viewModel.getValidationResultLiveData().observe(this) {
            if (it.passwordResultId != null) {
                result = false
                binding.passwordErrorTextView.text = resources.getString(it.passwordResultId)
            } else {
                binding.passwordErrorTextView.text = ""
            }
            if (it.emailResulId != null) {
                result = false
                binding.loginErrorTextView.text = resources.getString(it.emailResulId)
            } else {
                binding.loginErrorTextView.text = ""
            }
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