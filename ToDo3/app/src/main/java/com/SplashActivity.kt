import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.MainActivity
import com.example.todo.R
import com.example.todo.ui.theme.AppTheme
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                SplashScreen()
            }
        }
    }

    @Composable
    fun SplashScreen() {
        // Define the delay time in milliseconds
        val splashScreenDelay = 2000L // 2 seconds

        LaunchedEffect(Unit) {
            delay(splashScreenDelay)
            navigateToMainActivity() // Navigate to MainActivity after the delay
        }

        Surface(color = MaterialTheme.colorScheme.background) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter // Align the logo and text to the top center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 300.dp), // 300dp from the top
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.todo), // Your app logo
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape) // Circular splash image
                    )
                    Spacer(modifier = Modifier.height(20.dp)) // Space between logo and text
                    Text(
                        text = "TODO",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                        fontWeight = FontWeight.Bold
                    )
                }

                // Text at the bottom of the page
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(
                        text = "Designed by Navdeep Kaur",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp)
                    )
                }
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Finish SplashActivity so that it’s removed from the back stack
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewSplashScreen() {
        AppTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
                SplashScreen()
            }
        }
    }
}

