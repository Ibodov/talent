package com.smile.talent_official.data.network.api

import com.smile.talent_official.data.models.PhoneNumber
import retrofit2.Call
import retrofit2.http.GET

interface PhoneNumbersApi {
    @GET("phone_number/random_phone_number?size=20")
    suspend fun getRandomPhoneNumbers(): retrofit2.Response<List<PhoneNumber>>

    @GET("phone_number/random_phone_number?size=20")
     fun getRandomPhoneNumbers2(): Call<List<PhoneNumber>>


}