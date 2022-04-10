package dev.dizel.smsreceiver


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import kotlinx.coroutines.runBlocking


class MySMSBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.action != Telephony.Sms.Intents.SMS_RECEIVED_ACTION) return
        if (context == null) return
        val bundle = intent.extras ?: return

        val pdus = bundle["pdus"] as Array<*>?
        val messages = arrayOfNulls<SmsMessage>(pdus!!.size)
        val format = bundle.getString("format")

        var text = ""

        pdus.indices.forEachIndexed { index, _ ->
            messages[index] = SmsMessage.createFromPdu(pdus[index] as ByteArray, format)
            text += "ICC: ${messages[index]!!.indexOnIcc}\n"
            text += "From: ${messages[index]!!.originatingAddress}\n"
            text += "Text: ${messages[index]!!.messageBody}\n"
            text += "<---->\n"
        }

        val storage = DataStorage(context)
        val token = storage.getToken() ?: return
        val userId = storage.getUserId() ?: return

        runBlocking {
            val body = Message(
                chatId = userId,
                text = text
            )

            try {
                RetrofitBuilder(token).apiService.sendMessage(body)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}