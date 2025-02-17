package com

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.room.Room
import com.db.ToDoDatabase
import com.example.todo.ui.BaseApp.Companion.notificationManager

class MainApplication: Application() {
    companion object {
        lateinit var todoDatabase: ToDoDatabase
        lateinit var notificationManager: NotificationManager
    }

    override fun onCreate() {
        super.onCreate()
        todoDatabase=Room.databaseBuilder(
            applicationContext,
            ToDoDatabase::class.java,
            ToDoDatabase.NAME
        ).build()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel= NotificationChannel(
                "channel_id",
                "channel_name",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.description="Notification to do your Task"
            notificationChannel.enableVibration(true)
            notificationChannel.enableLights(true)

            notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }else{
            notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

    }
    }

