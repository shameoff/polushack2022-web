package com.example.polushackhatonproject.data.model

import com.google.gson.annotations.SerializedName

data class UserDataResponse(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("last_name")
    var surname: String = "",
    @SerializedName("email")
    var email: String = "",
    @SerializedName("token")
    var token: String = ""
)
