package za.co.varsitycollege.st10082911.clientclockapplication

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar
class CreateGraph : AppCompatActivity() {

    private lateinit var lineChart: LineChart
    private lateinit var startDateEditText: TextInputEditText
    private lateinit var endDateEditText: TextInputEditText
    private lateinit var startDateButton: Button
    private lateinit var endDateButton: Button
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_graph)

        lineChart = findViewById(R.id.lineChart)
        startDateEditText = findViewById(R.id.startDateEditText)
        endDateEditText = findViewById(R.id.endDateEditText)
        startDateButton = findViewById(R.id.startDateButton)
        endDateButton = findViewById(R.id.endDateButton)

        setupChart()

        startDateButton.setOnClickListener {
            showDatePickerDialog(startDateEditText)
        }

        endDateButton.setOnClickListener {
            showDatePickerDialog(endDateEditText)
        }

        updateButton = findViewById(R.id.btn_update_chart)
        updateButton.setOnClickListener {
            updateChart()
        }

        val backButton: ImageButton = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showDatePickerDialog(dateEditText: TextInputEditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
            dateEditText.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun setupChart() {
        // Sample data
        val entries = listOf(
            Entry(1f, 8f),  // Day 1, 8 hours
            Entry(2f, 7f),
            Entry(3f, 6f),
            Entry(4f, 9f),
            Entry(5f, 5f)
        )

        val dataSet = LineDataSet(entries, "Hours Worked")
        val lineData = LineData(dataSet)
        lineChart.data = lineData

        // Adding minimum and maximum goal lines
        val leftAxis: YAxis = lineChart.axisLeft
        val rightAxis: YAxis = lineChart.axisRight
        leftAxis.removeAllLimitLines()
        rightAxis.removeAllLimitLines()

        val minGoal = LimitLine(6f, "Min Goal").apply {
            lineWidth = 2f
            lineColor = Color.parseColor("#FF018786") // Example color
        }
        leftAxis.addLimitLine(minGoal)

        val maxGoal = LimitLine(9f, "Max Goal").apply {
            lineWidth = 2f
            lineColor = Color.parseColor("#FF6200EE") // Example color
        }
        leftAxis.addLimitLine(maxGoal)

        // Refresh the chart
        lineChart.invalidate()
    }

    private fun updateChart() {
        // Get dates from DatePickers
        val startDate = startDateEditText.text.toString()
        val endDate = endDateEditText.text.toString()

        // Filter or generate new data based on selected dates
        // This is where you would update your data set

        // For demonstration, let's just regenerate the same data
        val entries = listOf(
            Entry(1f, 8f),  // Day 1, 8 hours
            Entry(2f, 7f),
            Entry(3f, 6f),
            Entry(4f, 9f),
            Entry(5f, 5f)
        )

        val dataSet = LineDataSet(entries, "Hours Worked")
        val lineData = LineData(dataSet)
        lineChart.data = lineData

        // Refresh the chart
        lineChart.invalidate()
    }
}
