package com.notifications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import com.MainApplication
import com.example.todo.MainActivity
import com.example.todo.R

@Composable
fun ShowNotificationScreen(){
    val context=LocalContext.current
    val notificationLauncher=rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()){

    }
  LaunchedEffect(key1 = true) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
          notificationLauncher.launch(input = android.Manifest.permission.POST_NOTIFICATIONS)
  }}
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center ){
        Button(onClick = {
createNotifiction(context)
        }) {
            Text(text = "show notifications")
        }
    }

}

private fun createNotifiction(
    context: Context
){
    val notificationManager= MainApplication.notificationManager
    val notification= NotificationCompat.Builder(context,"channel_id")
    .setContentTitle("Notification")
        .setContentTitle("This is a notification")
        .setSmallIcon(R.drawable.logosec)
        .setAutoCancel(true)
        .addAction(0,"start", createPendingIntent(context))
        .setContentIntent(createPendingIntent(context))
        .build()
    notificationManager.notify(100,notification)
}

private fun createPendingIntent(
    context: Context
): PendingIntent {
    val intent= Intent(context,MainActivity::class.java).apply {
        putExtra("data","Hey!! this is notification")
    }
    return PendingIntent.getActivity(
        context,
        100,
        intent,
        PendingIntent.FLAG_IMMUTABLE)
    }