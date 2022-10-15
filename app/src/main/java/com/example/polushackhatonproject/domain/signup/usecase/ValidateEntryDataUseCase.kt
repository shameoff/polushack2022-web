package com.example.polushackhatonproject.domain.signup.usecase

import com.example.polushackhatonproject.R
import com.example.polushackhatonproject.domain.signup.validator.SignUpValidator

class ValidateEntryDataUseCase {
    private val signUpValidator = SignUpValidator()

    fun execute(email: String, password: String): Int {
        return if (signUpValidator.checkEmailValidity(email)) {
            checkPassword(password)
        } else {
            if (checkIfEmailEmpty(email)) {
                R.string.emptiness_error
            } else {
                R.string.email_error
            }
        }
    }

    private fun checkPassword(password: String): Int {
        return if (signUpValidator.checkPasswordValidity(password)) {
            R.string.data_is_valid
        } else {
            R.string.emptiness_error
        }
    }

    private fun checkIfEmailEmpty(email: String): Boolean {
        return email.isEmpty()
    }
}