package za.co.varsitycollege.st10082911.clientclockapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DailWorkFeature : AppCompatActivity() {

    private lateinit var editTextMinGoal: EditText
    private lateinit var editTextMaxGoal: EditText
    private lateinit var buttonSaveGoals: Button
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var textViewSelectedHours: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dail_work_feature)

        editTextMinGoal = findViewById(R.id.editTextMinGoal)
        editTextMaxGoal = findViewById(R.id.editTextMaxGoal)
        buttonSaveGoals = findViewById(R.id.buttonSaveGoals)
        textViewSelectedHours = findViewById(R.id.textViewSelectedHours)
        sharedPreferences = getSharedPreferences("DailyWorkGoals", Context.MODE_PRIVATE)

        buttonSaveGoals.setOnClickListener {
            // Get the selected hours from the EditTexts
            val minHours = editTextMinGoal.text.toString()
            val maxHours = editTextMaxGoal.text.toString()

            // Update the TextView with the selected hours
            val selectedHours = "Selected Hours: Min - $minHours, Max - $maxHours"
            textViewSelectedHours.text = selectedHours
            textViewSelectedHours.visibility = View.VISIBLE // Make the TextView visible
        }

        val backButton: ImageButton = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
            finish()
        }

    }



    private fun saveGoals() {
        val minGoal = editTextMinGoal.text.toString().toFloatOrNull()
        val maxGoal = editTextMaxGoal.text.toString().toFloatOrNull()

        if (minGoal != null && maxGoal != null && minGoal <= maxGoal) {
            sharedPreferences.edit().apply {
                putFloat("minGoal", minGoal)
                putFloat("maxGoal", maxGoal)
                apply()
            }
            Toast.makeText(this, "Goals saved successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please enter valid goals", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadGoals() {
        val minGoal = sharedPreferences.getFloat("minGoal", 0f)
        val maxGoal = sharedPreferences.getFloat("maxGoal", 0f)
        editTextMinGoal.setText(minGoal.toString())
        editTextMaxGoal.setText(maxGoal.toString())
    }

}