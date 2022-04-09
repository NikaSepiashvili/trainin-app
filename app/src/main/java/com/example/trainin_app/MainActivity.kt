package com.example.trainin_app
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.room.Room
import com.example.trainin_app.data.AppDatabase
import com.example.trainin_app.data.TrainingSession
import com.example.trainin_app.data.TrainingSessionDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var trainingSessionDao: TrainingSessionDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "training_session"
        ).build()

        trainingSessionDao = database.trainingSessionDao()

        val button: ImageButton = findViewById(R.id.addButton)
        button.setOnClickListener {
            val ran: EditText = findViewById(R.id.ranEdit)
            val swam: EditText = findViewById(R.id.swamEdit)
            val calories: EditText = findViewById(R.id.caloriesEdit)

            onAdd(
                ran.text.toString().toDouble(),
                swam.text.toString().toDouble(),
                calories.text.toString().toDouble()
            )

            ran.text.clear()
            swam.text.clear()
            calories.text.clear()
        }

    }

    private fun onAdd(ran: Double, swam: Double, calories: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            trainingSessionDao.insert(
                TrainingSession(0,ran,swam,calories)
            )
            getData()
        }
    }

    private fun getData() {
        val ranDistance = trainingSessionDao.averageRanDistance()
        val swamDistance = trainingSessionDao.averageSwamDistance()
        val takenCalories = trainingSessionDao.averageTakenCalories()

        val totalRanDistance = trainingSessionDao.totalRanDistance()

        val outputView: TextView = findViewById(R.id.outputView)


        val outputText = "average ran: $ranDistance, average swam: $swamDistance, average calories taken: $takenCalories \n total distance: $totalRanDistance"

        outputView.text = outputText

        println("average ran: $ranDistance, average swam: $swamDistance, average calories taken: $takenCalories \n total distance: $totalRanDistance")
    }
}