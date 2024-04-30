package za.co.varsitycollege.st10082911.clientclockapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import za.co.varsitycollege.st10082911.clientclockapplication.databinding.ActivityCategoryBinding


class Category : AppCompatActivity() {
    private val timesheetEntries = mutableListOf<createTimesheetEntries>()
    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // Access the button using its ID and set an OnClickListener to it
        binding.btnSave.setOnClickListener {
            saveTimesheetEntry()
        }

    }

    private fun saveTimesheetEntry() {
        // Get user input from EditText fields
        val category = binding.edittextCategory.text.toString()
        val task = binding.editTask.text.toString()
        val description = binding.editDescription.text.toString()
        val Date = binding.editDate.text.toString()
        val startTime = binding.editStartTime.text.toString()
        val endTime = binding.editEndTime.text.toString()

        // Create a new TimesheetEntry object
        val timesheetEntry = createTimesheetEntries(category, task, description, Date, startTime, endTime)

        // Add the new entry to the ArrayList
        timesheetEntries.add(timesheetEntry)

        // Optionally, you can clear the EditText fields after saving
        binding.edittextCategory.text.clear()
        binding.editTask.text.clear()
        binding.editDescription.text.clear()
        binding.editDate.text.clear()
        binding.editStartTime.text.clear()
        binding.editEndTime.text.clear()


    }

}


