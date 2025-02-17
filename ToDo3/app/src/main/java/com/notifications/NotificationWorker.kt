package com.notifications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.MainApplication
import com.example.todo.MainActivity
import com.example.todo.R

class NotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        createNotification(applicationContext)
        return Result.success()
    }

    private fun createNotification(context: Context) {
        val notificationManager = MainApplication.notificationManager
        val notification = NotificationCompat.Builder(context, "channel_id")
            .setContentTitle("Reminder")
            .setContentText("Don't forget to complete your tasks!")
            .setSmallIcon(R.drawable.logosec)
            .setAutoCancel(true)
            .setContentIntent(createPendingIntent(context))
            .build()

        notificationManager.notify(200, notification)
    }

    private fun createPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra("data", "Hey!! this is your reminder.")
        }
        return PendingIntent.getActivity(
            context,
            200,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}
