package com.example.polushackhatonproject.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.polushackhatonproject.R
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
            val email = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (checkIsEntryDataValid()) {
                viewModel.saveUserCreditsToLocalStorage(email, password)
                createAnimationOnButtonClick()
            } else {
                val stringId = viewModel.checkEntryDataValidity(email, password)
                Toast.makeText(
                    this,
                    resources.getString(stringId),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun checkIsEntryDataValid(): Boolean {
        val email = binding.loginEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        return viewModel.checkEntryDataValidity(email, password) == R.string.data_is_valid

    }

    private fun createAnimationOnButtonClick() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.signLayout.animate().translationY(1200f).setDuration(700).start()
            delay(600)
            binding.logoImageView.animate().rotation(720f).setDuration(600).start()
            binding.logoImageView
                .animate()
                .translationY(-500f)
                .translationX(1500f)
                .setDuration(600)
                .start()
            delay(600)
            startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
            finish()
        }
    }
}