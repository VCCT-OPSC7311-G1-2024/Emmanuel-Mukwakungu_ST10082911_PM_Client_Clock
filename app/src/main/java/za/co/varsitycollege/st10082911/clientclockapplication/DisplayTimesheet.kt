package za.co.varsitycollege.st10082911.clientclockapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import za.co.varsitycollege.st10082911.clientclockapplication.databinding.ActivityDisplayTimesheetBinding
import android.content.Intent

class DisplayTimesheet : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayTimesheetBinding
    private lateinit var adapter: TimesheetAdapter
    private lateinit var timeSheetManager: TimesheetManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayTimesheetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timeSheetManager = TimesheetManager()
        adapter = TimesheetAdapter(listOf())
        binding.recyclerViewTable.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTable.adapter = adapter

        binding.searchButton.setOnClickListener {
            val startDate = binding.startDateEditText.text.toString()
            val endDate = binding.endDateEditText.text.toString()
            Log.i("DisplayTimesheet", "Search button clicked with start date: $startDate and end date: $endDate")
            updateEntries(startDate, endDate)
        }

        // Set click listener for btnCreateTimesheet
        binding.btnCreateTimesheet.setOnClickListener {
            val intent = Intent(this, Category::class.java)
            startActivity(intent)
        }

        // Set click listener for btnBack
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
        }
    }

    private fun updateEntries(startDate: String, endDate: String) {
        timeSheetManager.getTasksByDateRange(startDate, endDate) { filteredEntries ->
            Log.i("DisplayTimesheet", "Filtered entries received: ${filteredEntries.size}")
            adapter.updateEntries(filteredEntries)
        }
    }
}
