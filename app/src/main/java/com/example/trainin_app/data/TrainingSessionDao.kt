package com.example.trainin_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TrainingSessionDao {

    @Insert()
    fun insert(session: TrainingSession)

    @Query("SELECT * FROM training_session ORDER BY id ASC")
    fun readAllData(): LiveData<List<TrainingSession>>

    @Query("SELECT AVG(ran) FROM training_session")
    fun averageRanDistance(): Double

    @Query("SELECT Avg(swam) FROM training_session")
    fun averageSwamDistance(): Double

    @Query("SELECT Avg(calories) FROM training_session")
    fun averageTakenCalories(): Double

    @Query("SELECT Sum(ran) FROM training_session")
    fun totalRanDistance(): Double
}