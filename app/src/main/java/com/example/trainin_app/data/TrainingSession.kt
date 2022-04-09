package com.example.trainin_app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "training_session")
data class TrainingSession(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "ran") val ran: Double,
    @ColumnInfo(name = "swam") val swam: Double,
    @ColumnInfo(name = "calories") val calories: Double
    )