package com.example.polushackhatonproject.data.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class TaskResponse(
    @SerializedName("id")
    var taskId: String,
    @SerializedName("latitude")
    var latitude: Float,
    @SerializedName("longitude")
    var logitude: Float,
    @SerializedName("time_start")
    var timeStart: Timestamp,
    @SerializedName("time_end")
    var timeFinish: Timestamp,
    @SerializedName("description")
    var description: String,
    @SerializedName("car_number")
    var carNumber: String,
    @SerializedName("car_type")
    var carType: String

)
