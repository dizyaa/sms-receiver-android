package dev.dizel.smsreceiver

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofit = Retrofit.Builder()
        .baseUrl("${BuildConfig.TELEGRAM_BASE_URL}${BuildConfig.TELEGRAM_BOT_TOKEN}/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: TelegramApi = retrofit.create(TelegramApi::class.java)
}