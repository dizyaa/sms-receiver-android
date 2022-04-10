package dev.dizel.smsreceiver

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val requestPermissionCode = 103
    private lateinit var storage: DataStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storage = DataStorage(applicationContext)

        sendStartUpMessage()
        setUpView()
        requestPermissions()
    }

    private fun setUpView() {
        findViewById<Button>(R.id.vTestButton).setOnClickListener { sendTestMessage() }
        findViewById<Button>(R.id.buttonSaveData).setOnClickListener { saveData() }

        findViewById<EditText>(R.id.editTextToken).setText(storage.getToken() ?: "")
        findViewById<EditText>(R.id.editTextUserId).setText(storage.getUserId() ?: "")
    }

    private fun requestPermissions() {
        requestPermissions(
            arrayOf(
                Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_SMS,
            ), requestPermissionCode
        )
    }

    private fun saveData() {
        val token = findViewById<EditText>(R.id.editTextToken).text.toString()
        storage.saveToken(token)

        val userId = findViewById<EditText>(R.id.editTextUserId).text.toString()
        storage.saveUserId(userId)

        Toast.makeText(this, "Successful!!", Toast.LENGTH_SHORT).show()
    }

    private fun sendStartUpMessage() {
        sendMessage("Starting ${Build.MODEL}")
    }

    private fun sendTestMessage() {
        sendMessage("Test message from ${Build.MODEL}")
    }

    private fun sendMessage(text: String) {
        val token = storage.getToken() ?: return
        val userId = storage.getUserId() ?: return

        val message = Message(
            chatId = userId,
            text = text
        )

        lifecycleScope.launch {
            try {
                RetrofitBuilder.apiService.sendMessage(token, message)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}