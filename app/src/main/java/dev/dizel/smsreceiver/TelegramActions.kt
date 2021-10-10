package dev.dizel.smsreceiver

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TelegramActions {

    private val retrofit = Retrofit.Builder()
        .baseUrl("$BASE_URL$TOKEN_BOT/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val emptyCallback = object : Callback<Message> {
        override fun onResponse(call: Call<Message>, response: Response<Message>) {}
        override fun onFailure(call: Call<Message>, t: Throwable) {}
    }

    fun sendMessage(text: String) {
        val message = Message(
            chatId = USER_ID,
            text = text
        )

        retrofit.create(TelegramApi::class.java)
            .sendMessage(message)
            .enqueue(emptyCallback)
    }

    companion object {
        private const val BASE_URL = "https://api.telegram.org/bot"
        private const val TOKEN_BOT = ""
        private const val USER_ID = ""
    }
}