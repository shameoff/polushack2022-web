package com.example.polushackhatonproject.data.model

import com.google.gson.annotations.SerializedName

data class UserCreditsResponse(
    @SerializedName("name")
    var name: String,
    @SerializedName("last_name")
    var surname: String,
    @SerializedName("header")
    var token: String
)