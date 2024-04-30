package za.co.varsitycollege.st10082911.clientclockapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import za.co.varsitycollege.st10082911.clientclockapplication.databinding.ActivityCategoryBinding
import java.util.Calendar
import android.app.DatePickerDialog


class Category : AppCompatActivity() {
    private val timesheetEntries = mutableListOf<createTimesheetEntries>()
    private lateinit var binding: ActivityCategoryBinding
    private val dateSelected = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // Access the button using its ID and set an OnClickListener to it
        binding.btnSave.setOnClickListener {
            saveTimesheetEntry()
        }

        // Set up the date picker dialog
        setDate()



    }

    private fun saveTimesheetEntry() {
        // Get user input from EditText fields
        val category = binding.edittextCategory.text.toString()
        val task = binding.editTask.text.toString()
        val description = binding.editDescription.text.toString()
        val date = binding.btnDate.text.toString() // Get the date from the button text
        val startTime = binding.editStartTime.text.toString()
        val endTime = binding.editEndTime.text.toString()

        // Create a new createTimesheetEntries object
        val timesheetEntry = createTimesheetEntries(category, task, description, date, startTime, endTime)

        // Add the new entry to the ArrayList
        timesheetEntries.add(timesheetEntry)

        // Optionally, you can clear the EditText fields after saving
        binding.edittextCategory.text.clear()
        binding.editTask.text.clear()
        binding.editDescription.text.clear()
        binding.btnDate.text = ""
        binding.editStartTime.text.clear()
        binding.editEndTime.text.clear()
    }

    private fun setDate() {
        // Set an OnClickListener for the button
        binding.btnDate.setOnClickListener {
            // Create a DatePickerDialog
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog =
                DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                    // When date is set, convert it to String and add it to the ArrayList
                    val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    dateSelected.add(selectedDate)
                    // Set the selected date as button text
                    binding.btnDate.text = selectedDate
                }, year, month, day)
            datePickerDialog.show()
        }
    }

}


