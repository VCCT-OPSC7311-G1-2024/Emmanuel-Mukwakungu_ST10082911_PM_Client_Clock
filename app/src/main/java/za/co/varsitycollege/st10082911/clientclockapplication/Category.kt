package za.co.varsitycollege.st10082911.clientclockapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import za.co.varsitycollege.st10082911.clientclockapplication.databinding.ActivityCategoryBinding
import java.util.Calendar
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast

// Category class that extends AppCompatActivity
class Category : AppCompatActivity() {
    // List to hold timesheet entries
    private val timesheetEntries = mutableListOf<createTimesheetEntries>()
    // List to hold categories
    private val categories = mutableListOf<categoryClass>()
    // Binding object to access views in the layout
    private lateinit var binding: ActivityCategoryBinding
    // List to hold selected dates
    private val dateSelected = ArrayList<String>()

    // onCreate function that sets up the activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the date picker and time pickers
        setDate()
        setStartTime()
        setEndTime()

        // Set up the save button
        binding.btnSave.setOnClickListener {
            saveTimesheetEntry()
        }
    }

    // Function to save timesheet entry
    private fun saveTimesheetEntry() {
        // Get user input from EditText fields
        val category = binding.edittextCategory.text.toString()
        val task = binding.editTask.text.toString()
        val description = binding.editDescription.text.toString()
        val date = binding.btnDate.text.toString() // Get the date from the button text
        val startTime = binding.btnStartTime.text.toString() // Get the start time from the button text
        val endTime = binding.btnEndTime.text.toString() // Get the end time from the button text

        // Check if the category is one of the allowed options
        if (category == "Work" || category == "Side-project" || category == "School") {
            // Create a new createTimesheetEntries object
            val timesheetEntry = createTimesheetEntries(category, task, description, date, startTime, endTime)

            // Find the category in the list, or create a new one if it doesn't exist
            var categoryClassInstance = categories.find { it.name == category }
            if (categoryClassInstance == null) {
                categoryClassInstance = categoryClass(category)
                categories.add(categoryClassInstance)
                Toast.makeText(this, "Category created successfully", Toast.LENGTH_SHORT).show()
            }

            // Add the new entry to the category's list
            categoryClassInstance.timesheetEntries.add(timesheetEntry)
            Toast.makeText(this, "Timesheet entry saved successfully", Toast.LENGTH_SHORT).show()

            // Optionally, you can clear the EditText fields after saving
            binding.edittextCategory.text.clear()
            binding.editTask.text.clear()
            binding.editDescription.text.clear()
            binding.btnDate.text = ""
            binding.btnStartTime.text = ""
            binding.btnEndTime.text = ""
        } else {
            // Show an error message if the category is not one of the allowed options
            Toast.makeText(this, "Invalid category. Only enter these 3 options: Work, School & Side-project", Toast.LENGTH_LONG).show()
        }
    }

    // Function to set up the date picker
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

    // Function to set up the start time picker
    private fun setStartTime() {
        binding.btnStartTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                binding.btnStartTime.text = selectedTime
            }, hour, minute, true)
            timePickerDialog.show()
        }
    }

    // Function to set up the end time picker
    private fun setEndTime() {
        binding.btnEndTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                binding.btnEndTime.text = selectedTime
            }, hour, minute, true)
            timePickerDialog.show()
        }
    }
}
//=====================================================end of file - Keemo was here=======================================================