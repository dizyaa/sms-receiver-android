package dev.dizel.smsreceiver

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendStartUpMessage()

        findViewById<Button>(R.id.vTestButton).setOnClickListener {
            sendTestMessage()
        }
    }

    private fun sendStartUpMessage() {
        val model = Build.MODEL
        val message = "Запущено устройство $model"
        TelegramActions().sendMessage(message)
    }

    private fun sendTestMessage() {
        val model = Build.MODEL
        val message = "Тестовое сообщение с $model"
        TelegramActions().sendMessage(message)
    }
}