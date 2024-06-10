package za.co.varsitycollege.st10082911.clientclockapplication

import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

class Tracker : AppCompatActivity() {
    private lateinit var chronometer: Chronometer
    private lateinit var toggleButton: ToggleButton
    private var running = false
    private var pauseOffset: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracker)

        chronometer = findViewById(R.id.timer)
        toggleButton = findViewById(R.id.toggle_button)
        val backButton: ImageButton = findViewById(R.id.btn_back)

        // Back button to navigate back to the homepage
        backButton.setOnClickListener {
            finish()
        }

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                startChronometer()
            } else {
                pauseChronometer()
            }
        }
    }

    private fun startChronometer() {
        if (!running) {
            chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
            chronometer.start()
            running = true
        }
    }

    private fun pauseChronometer() {
        if (running) {
            chronometer.stop()
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
            running = false
        }
    }
}

