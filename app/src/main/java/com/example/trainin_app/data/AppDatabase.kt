package com.example.trainin_app.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TrainingSession::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trainingSessionDao(): TrainingSessionDao
}