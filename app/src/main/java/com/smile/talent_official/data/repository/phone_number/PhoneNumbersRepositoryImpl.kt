package com.smile.talent_official.data.repository.phone_number

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.smile.talent_official.data.models.PhoneNumber
import com.smile.talent_official.data.network.api.PhoneNumbersApi
import com.smile.talent_official.db.dao.TalentDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneNumbersRepositoryImpl constructor(
    val api: PhoneNumbersApi,
    val dao: TalentDao
) : PhoneNumbersRepository {
    override fun getPhoneNumbers(): LiveData<List<PhoneNumber>> {
        val data: LiveData<List<PhoneNumber>> = liveData {
            val response = api.getRandomPhoneNumbers()
            if (response.isSuccessful) {
                val p = dao.getAllSuspend()
                val numbers = response.body()!!
                p.forEach {
                    it.phoneNumber = numbers.random().cellPhoneIne64!!
                    dao.update(it)
                }
                emit(response.body()!!)

            }
        }
        return data
    }

    override fun getPhoneNumbers2(): LiveData<List<PhoneNumber>> {
        val liveData = MutableLiveData<List<PhoneNumber>>()
        api.getRandomPhoneNumbers2().enqueue(object : Callback<List<PhoneNumber>> {
            override fun onResponse(
                call: Call<List<PhoneNumber>>,
                response: Response<List<PhoneNumber>>
            ) {
                if (response.isSuccessful) {
                    liveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<PhoneNumber>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return liveData
    }
}