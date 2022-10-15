package com.example.polushackhatonproject.domain.main.model

import java.sql.Timestamp

data class Task(
    var name: String,
    var description: String,
    var timeStart: Timestamp,
    var timeFinish: Timestamp
)
