package dev.dizel.smsreceiver

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TelegramApi {
    @POST("sendMessage")
    suspend fun sendMessage(
        @Body body: Message
    ): Message
}