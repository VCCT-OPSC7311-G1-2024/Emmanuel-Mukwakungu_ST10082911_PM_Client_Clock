package za.co.varsitycollege.st10082911.clientclockapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage)

        val categoryButton: CardView = findViewById(R.id.categoryCard)
        categoryButton.setOnClickListener {
            val intent = Intent(this, Category::class.java)
            startActivity(intent)
            finish()
        }

        val goalButton: CardView = findViewById(R.id.goaslsCard)
        goalButton.setOnClickListener {
            val intent = Intent(this, AddGoals::class.java)
            startActivity(intent)
            finish()
        }

        val createSheetButton: CardView = findViewById(R.id.manageSheetCard)
        createSheetButton.setOnClickListener {
            val intent = Intent(this, createTimesheetEntries::class.java)
            startActivity(intent)
            finish()

        }

    }
}