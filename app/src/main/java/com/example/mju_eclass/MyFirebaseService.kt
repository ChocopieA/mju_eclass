package com.example.mju_eclass

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("kkang", "fcm token$token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("kkang", "fcm token${message.data}")
        Log.d("kkang", "fcm token${message.notification}")

        //Toast.makeText(getApplicationContext(), "서버로부터 메세지 도착 : ${message.data}", Toast.LENGTH_SHORT).show()
    }

}