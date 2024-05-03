package za.co.varsitycollege.st10082911.clientclockapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import za.co.varsitycollege.st10082911.clientclockapplication.databinding.ActivityDisplayTimesheetBinding

class DisplayTimesheet : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayTimesheetBinding
    private lateinit var adapter: TimesheetAdapter
    private lateinit var timeSheetManager: TimeSheetManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayTimesheetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timeSheetManager = TimeSheetManager(Category.timesheetEntries) // Assuming Category class manages all entries
        adapter = TimesheetAdapter(listOf())
        binding.recyclerViewTable.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTable.adapter = adapter

        binding.searchButton.setOnClickListener {
            val startDate = binding.startDateEditText.text.toString()
            val endDate = binding.endDateEditText.text.toString()
            updateEntries(startDate, endDate)
        }
    }

    private fun updateEntries(startDate: String, endDate: String) {
        val filteredEntries = timeSheetManager.getTasksByDateRange(startDate, endDate)
        adapter.updateEntries(filteredEntries)
    }
}
