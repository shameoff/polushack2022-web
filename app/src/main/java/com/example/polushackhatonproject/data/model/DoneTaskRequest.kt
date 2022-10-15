package com.example.polushackhatonproject.data.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class DoneTaskRequest(
    @SerializedName("id")
    var taskId: String,
    @SerializedName("time_end")
    var timeFinish: Timestamp

)
