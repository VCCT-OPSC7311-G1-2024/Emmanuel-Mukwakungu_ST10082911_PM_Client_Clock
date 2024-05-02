package za.co.varsitycollege.st10082911.clientclockapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.content.Intent
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import za.co.varsitycollege.st10082911.clientclockapplication.ui.theme.ClientClockApplicationTheme
import za.co.varsitycollege.st10082911.clientclockapplication.databinding.ActivityMainBinding
import android.widget.Toast

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if (binding.edittextEmail.text.toString() == "user" && binding.edittextPassword.text.toString() == "1234"){
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Category::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        }










    }
}
