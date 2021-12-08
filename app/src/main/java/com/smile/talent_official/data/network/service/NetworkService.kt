package com.smile.talent_official.data.network.service

import com.smile.talent_official.data.network.api.PhoneNumbersApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    val BASE_URL = "https://random-data-api.com/api/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun phoneNumberApi () = retrofit.create(PhoneNumbersApi::class.java)

}