package za.co.varsitycollege.st10082911.clientclockapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import za.co.varsitycollege.st10082911.clientclockapplication.databinding.ActivityCategoryBinding
import java.util.Calendar
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.BitmapFactory
import android.provider.MediaStore
import java.io.IOException

class Category : AppCompatActivity() {
    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        binding.btnBack.setOnClickListener { finish() }
        binding.btnUploadPhoto.setOnClickListener {
            startActivityForResult(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), PICK_IMAGE_REQUEST)
        }

        setDate()
        setStartTime()
        setEndTime()

        binding.btnSave.setOnClickListener { saveTimesheetEntry() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            data.data?.let { uri ->
                try {
                    BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
                    Toast.makeText(this, "Photo selected successfully", Toast.LENGTH_SHORT).show()
                } catch (e: IOException) {
                    Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveTimesheetEntry() {
        val category = binding.edittextCategory.text.toString()
        val task = binding.editTask.text.toString()
        val description = binding.editDescription.text.toString()
        val date = binding.btnDate.text.toString()
        val startTime = binding.btnStartTime.text.toString()
        val endTime = binding.btnEndTime.text.toString()

        if (category in listOf("Work", "Side-project", "School")) {
            val newEntry = TimesheetEntry(category, task, description, date, startTime, endTime)
            val categoryDocRef = db.collection("categories").document(category)

            categoryDocRef.get().addOnSuccessListener { document ->
                if (document.exists()) {
                    val existingCategory = document.toObject(CategoryClass::class.java)
                    existingCategory?.let {
                        val updatedEntries = it.timesheetEntries.toMutableList()
                        updatedEntries.add(newEntry)
                        categoryDocRef.set(CategoryClass(category, updatedEntries))
                            .addOnSuccessListener {
                                Toast.makeText(this, "Timesheet entry saved successfully", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error saving entry: $e", Toast.LENGTH_SHORT).show()
                            }
                    }
                } else {
                    val newCategory = CategoryClass(category, listOf(newEntry))
                    categoryDocRef.set(newCategory)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Timesheet entry saved successfully", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Error saving entry: $e", Toast.LENGTH_SHORT).show()
                        }
                }
            }.addOnFailureListener { e ->
                Toast.makeText(this, "Error fetching category: $e", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Invalid category. Only enter these 3 options: Work, School, Side-project", Toast.LENGTH_LONG).show()
        }
    }

    private fun setDate() {
        binding.btnDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                binding.btnDate.text = selectedDate
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun setStartTime() {
        binding.btnStartTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(this, { _, hourOfDay, minute ->
                binding.btnStartTime.text = String.format("%02d:%02d", hourOfDay, minute)
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }
    }

    private fun setEndTime() {
        binding.btnEndTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(this, { _, hourOfDay, minute ->
                binding.btnEndTime.text = String.format("%02d:%02d", hourOfDay, minute)
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }
    }
}
