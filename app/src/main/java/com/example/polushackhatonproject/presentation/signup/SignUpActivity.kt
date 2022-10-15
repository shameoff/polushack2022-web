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


    private fun updateButton() {
        if (checkEmptyFieldsExisting()) {
            colorizeSigUpButton(R.drawable.signup_unlocked_button, R.color.hint)
            binding.signupButton.isClickable = false
        } else {
            colorizeSigUpButton(R.drawable.signup_locked_button, R.color.white)
            binding.signupButton.isClickable = true
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun colorizeSigUpButton(drawableId: Int, textColorId: Int) {
        binding.signupButton.background = resources.getDrawable(drawableId, this.theme)
        binding.signupButton.setTextColor(resources.getColor(textColorId, this.theme))
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
            result = onPasswordValidityResultChange(it.passwordResultId)
            result = onEmailValidityResultChange(it.emailResultId, result)
        }

        return result
    }

    private fun onEmailValidityResultChange(emailResultId: Int?, oldResult: Boolean): Boolean {
        var result = oldResult
        if (emailResultId != null) {
            result = false
            binding.loginErrorTextView.text = resources.getString(emailResultId)
        } else {
            binding.loginErrorTextView.text = ""
        }
        return result
    }

    private fun onPasswordValidityResultChange(passwordResultId: Int?): Boolean {
        var result = true
        if (passwordResultId != null) {
            result = false
            binding.passwordErrorTextView.text = resources.getString(passwordResultId)
        } else {
            binding.passwordErrorTextView.text = ""
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