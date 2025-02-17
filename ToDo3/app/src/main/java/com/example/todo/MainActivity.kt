package com.example.todo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.todo.ui.theme.ToDoTheme
import com.ToDoListPage
import com.ToDoViewModel
import com.notifications.ShowNotificationScreen
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.notifications.NotificationWorker
import java.util.concurrent.TimeUnit


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        enableEdgeToEdge()
        setContent {
            scheduleNotificationWorker(applicationContext)
            ShowNotificationScreen()
            ToDoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ToDoListPage(todoViewModel)
                }
            }
        }
    }

    companion object {
        fun navigate(fromActivity: ComponentActivity) {
            val intent = createIntent(fromActivity)
            intent?.let {
                fromActivity.startActivity(it)
            }
        }

        private fun createIntent(fromActivity: ComponentActivity): Intent {
            return Intent(fromActivity, MainActivity::class.java)
        }
    }
}
private fun scheduleNotificationWorker(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(
        2, TimeUnit.HOURS // Repeat every 2 hours
    ).build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "NotificationWork",
        androidx.work.ExistingPeriodicWorkPolicy.KEEP,
        workRequest
    )
}


