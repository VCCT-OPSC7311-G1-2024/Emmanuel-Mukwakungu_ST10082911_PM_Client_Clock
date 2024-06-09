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
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar
import kotlin.random.Random

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
        updateButton = findViewById(R.id.btn_update_chart)

        setupChart()

        startDateButton.setOnClickListener {
            showDatePickerDialog(startDateEditText)
        }

        endDateButton.setOnClickListener {
            showDatePickerDialog(endDateEditText)
        }

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
        // Use sample data from 1 to 24 hours
        val workHoursData = generateRandomWorkHours(24)

        val entries = workHoursData.map { Entry(it.day.toFloat(), it.hours) }

        val dataSet = LineDataSet(entries, "Hours Worked")
        val lineData = LineData(dataSet)
        lineChart.data = lineData

        val minHours = workHoursData.minByOrNull { it.hours }?.hours ?: 0f
        val maxHours = workHoursData.maxByOrNull { it.hours }?.hours ?: 0f

        val leftAxis: YAxis = lineChart.axisLeft
        val rightAxis: YAxis = lineChart.axisRight
        leftAxis.removeAllLimitLines()
        rightAxis.removeAllLimitLines()

        val minGoal = LimitLine(minHours, "Min Goal").apply {
            lineWidth = 2f
            lineColor = Color.parseColor("#FF018786") // Example color
        }
        leftAxis.addLimitLine(minGoal)

        val maxGoal = LimitLine(maxHours, "Max Goal").apply {
            lineWidth = 2f
            lineColor = Color.parseColor("#FF6200EE") // Example color
        }
        leftAxis.addLimitLine(maxGoal)

        // Customizing X-Axis for day labels
        val xAxis = lineChart.xAxis
        xAxis.valueFormatter = DayAxisValueFormatter()
        xAxis.granularity = 1f // Minimum interval for the X axis values

        // Customizing Y-Axis for hour labels
        leftAxis.valueFormatter = HourAxisValueFormatter()
        leftAxis.granularity = 1f // Minimum interval for the Y axis values

        lineChart.invalidate()
    }

    private fun updateChart() {
        val startDate = startDateEditText.text.toString()
        val endDate = endDateEditText.text.toString()

        val workHoursData = fetchWorkHours(startDate, endDate)

        val minHours = workHoursData.minByOrNull { it.hours }?.hours ?: 0f
        val maxHours = workHoursData.maxByOrNull { it.hours }?.hours ?: 0f

        val entries = workHoursData.map { Entry(it.day.toFloat(), it.hours) }

        val dataSet = LineDataSet(entries, "Hours Worked")
        val lineData = LineData(dataSet)
        lineChart.data = lineData

        val leftAxis: YAxis = lineChart.axisLeft
        val rightAxis: YAxis = lineChart.axisRight
        leftAxis.removeAllLimitLines()
        rightAxis.removeAllLimitLines()

        val minGoal = LimitLine(minHours, "Min Goal").apply {
            lineWidth = 2f
            lineColor = Color.parseColor("#FF018786") // Example color
        }
        leftAxis.addLimitLine(minGoal)

        val maxGoal = LimitLine(maxHours, "Max Goal").apply {
            lineWidth = 2f
            lineColor = Color.parseColor("#FF6200EE") // Example color
        }
        leftAxis.addLimitLine(maxGoal)

        // Customizing X-Axis for day labels
        val xAxis = lineChart.xAxis
        xAxis.valueFormatter = DayAxisValueFormatter()
        xAxis.granularity = 1f // Minimum interval for the X axis values

        // Customizing Y-Axis for hour labels
        leftAxis.valueFormatter = HourAxisValueFormatter()
        leftAxis.granularity = 1f // Minimum interval for the Y axis values

        lineChart.invalidate()
    }

    // Function to generate random work hours
    private fun generateRandomWorkHours(days: Int): List<WorkHour> {
        return List(days) { WorkHour(it + 1, Random.nextFloat() * 23 + 1) }
    }

    // Dummy function to fetch work hours based on date range
    private fun fetchWorkHours(startDate: String, endDate: String): List<WorkHour> {
        // Replace this with actual data fetching logic
        return generateRandomWorkHours(24)
    }

    // Data class to represent work hours
    data class WorkHour(val day: Int, val hours: Float)

    // Custom formatter for day labels on X-Axis
    class DayAxisValueFormatter : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return "Day ${value.toInt()}"
        }
    }

    // Custom formatter for hour labels on Y-Axis
    class HourAxisValueFormatter : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return "${value.toInt()}h"
        }
    }
}
