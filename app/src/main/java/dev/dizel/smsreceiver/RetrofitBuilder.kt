package dev.dizel.smsreceiver

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitBuilder(private val token: String) {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.TELEGRAM_BASE_URL + "bot${token}/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: TelegramApi = retrofit.create(TelegramApi::class.java)
}