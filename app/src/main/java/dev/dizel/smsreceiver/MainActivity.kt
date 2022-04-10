package dev.dizel.smsreceiver

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val requestPermissionCode = 103

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendStartUpMessage()

        findViewById<Button>(R.id.vTestButton).setOnClickListener {
            sendTestMessage()
        }

        requestPermissions(
            arrayOf(
                Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_SMS,
            ), requestPermissionCode
        )
    }

    private fun sendStartUpMessage() {
        sendMessage("Starting ${Build.MODEL}")
    }

    private fun sendTestMessage() {
        sendMessage("Test message from ${Build.MODEL}")
    }

    private fun sendMessage(text: String) {
        val message = Message(
            chatId = BuildConfig.TELEGRAM_USER_ID,
            text = text
        )

        lifecycleScope.launch {
            RetrofitBuilder.apiService.sendMessage(message)
        }
    }
}