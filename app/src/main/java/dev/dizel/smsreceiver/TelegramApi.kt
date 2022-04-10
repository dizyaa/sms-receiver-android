package dev.dizel.smsreceiver

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface TelegramApi {
    @POST("sendMessage")
    suspend fun sendMessage(
        @Body body: Message
    ): Message
}