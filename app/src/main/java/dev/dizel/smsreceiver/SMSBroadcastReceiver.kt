package dev.dizel.smsreceiver


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.gsm.SmsMessage
import kotlinx.coroutines.runBlocking


class MySMSBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.action !== Telephony.Sms.Intents.SMS_RECEIVED_ACTION) return
        val bundle = intent.extras ?: return

        val pdus = bundle["pdus"] as Array<*>?
        val messages = arrayOfNulls<SmsMessage>(pdus!!.size)

        pdus.indices.forEachIndexed { index, _ ->
            messages[index] = SmsMessage.createFromPdu(pdus[index] as ByteArray)
        }

        if (messages.size <= -1) return

        runBlocking {
            val body = Message(
                chatId = BuildConfig.TELEGRAM_USER_ID,
                text = messages[0]!!.messageBody
            )

            RetrofitBuilder.apiService.sendMessage(body)
        }
    }
}