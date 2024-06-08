package za.co.varsitycollege.st10082911.clientclockapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val categoryButton: CardView = findViewById(R.id.categoryCard)
        categoryButton.setOnClickListener {
            val intent = Intent(this, Category::class.java)
            startActivity(intent)
        }

        val goalButton: CardView = findViewById(R.id.goaslsCard)
        goalButton.setOnClickListener {
            val intent = Intent(this, AddGoals::class.java)
            startActivity(intent)
        }

        val manageTimesheetButton: CardView = findViewById(R.id.manageSheetCard)
        manageTimesheetButton.setOnClickListener {
            val intent = Intent(this, DisplayTimesheet::class.java)
            startActivity(intent)
        }

        val graphButton: CardView = findViewById(R.id.reportCard)
       graphButton.setOnClickListener {
            val intent = Intent(this, CreateGraph::class.java)
            startActivity(intent)
        }
    }
}

