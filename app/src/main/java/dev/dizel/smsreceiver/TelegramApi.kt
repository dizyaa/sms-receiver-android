package dev.dizel.smsreceiver

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TelegramApi {
    @POST("sendMessage")
    fun sendMessage(
        @Body body: Message
    ): Call<Message>
}