package com.example.polushackhatonproject.domain.signup.validator

class SignUpValidator {
    fun checkEmailValidity(email: String): Boolean {
        return email.contains("@")
    }

    fun checkPasswordValidity(password: String): Boolean {
        return password.isNotEmpty()
    }
 }