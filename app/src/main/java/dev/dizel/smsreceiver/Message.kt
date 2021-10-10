package dev.dizel.smsreceiver

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("chat_id") val chatId: String,
    @SerializedName("text") val text: String
)