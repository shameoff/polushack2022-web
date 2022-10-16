package com.example.polushackhatonproject.domain.signup.usecase

import com.example.polushackhatonproject.R
import com.example.polushackhatonproject.domain.signup.model.ValidatorData
import com.example.polushackhatonproject.domain.signup.model.ValidatorResult
import com.example.polushackhatonproject.domain.signup.validator.SignUpValidator

class ValidateEntryDataUseCase(private val validator: SignUpValidator) {

    fun execute(data: ValidatorData): ValidatorResult {
        return validator.validate(data)
    }
}