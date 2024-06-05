package za.co.varsitycollege.st10082911.clientclockapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class CreateGraph : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_graph)

        val backButton: ImageButton = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            val intent = Intent(this,Homepage::class.java)
            startActivity(intent)
            finish()
        }




        }
    }
}