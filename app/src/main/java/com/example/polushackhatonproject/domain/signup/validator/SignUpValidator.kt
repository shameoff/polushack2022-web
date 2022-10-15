package com.example.polushackhatonproject.domain.signup.validator

import com.example.polushackhatonproject.R
import com.example.polushackhatonproject.domain.signup.model.ValidatorData
import com.example.polushackhatonproject.domain.signup.model.ValidatorResult

class SignUpValidator {
    fun validate(data: ValidatorData): ValidatorResult{
        return ValidatorResult(
            emailResulId = checkEmail(data.email),
            passwordResultId = checkPassword(data.password)
        )
    }

    private fun checkEmail(email: String): Int? {
        var res: Int? = null

        if(!email.contains("""@""")){
            res = R.string.email_error
        }

        return res
    }

    private fun checkPassword(password: String): Int? {
        var res: Int? = null

        if(password.length <= 8) {
            res = R.string.small_password_error
        }

        return res
    }
 }