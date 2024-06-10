package za.co.varsitycollege.st10082911.clientclockapplication

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Locale

class TimesheetManager {

    private val db = FirebaseFirestore.getInstance()

    fun getTasksByDateRange(startDate: String, endDate: String, callback: (List<TimesheetEntry>) -> Unit) {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val start = sdf.parse(startDate)
        val end = sdf.parse(endDate)
        Log.i("TimesheetManager", "Parsed Start Date: $start, End Date: $end")

        db.collection("categories")
            .get()
            .addOnSuccessListener { documents ->
                val entries = mutableListOf<TimesheetEntry>()
                for (document in documents) {
                    val category = document.getString("name") ?: ""
                    val timesheetEntries = document.toObject(CategoryClass::class.java).timesheetEntries
                    Log.i("TimesheetManager", "Retrieved ${timesheetEntries.size} entries for category: $category")
                    timesheetEntries.forEach {
                        val taskDate = sdf.parse(it.startDate)
                        Log.i("TimesheetManager", "Parsed Task Date: $taskDate")
                        if (taskDate != null && taskDate in start..end) {
                            entries.add(it)
                        }
                    }
                }
                Log.i("TimesheetManager", "Filtered Entries: ${entries.size}")
                callback(entries)
            }
            .addOnFailureListener { exception ->
                Log.e("TimesheetManager", "Error getting documents: $exception")
                callback(emptyList())
            }
    }
}
