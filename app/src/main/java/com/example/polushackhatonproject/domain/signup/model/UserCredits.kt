package com.example.polushackhatonproject.domain.signup.model

import com.google.gson.annotations.SerializedName

data class UserCredits(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)
