package com.smile.talent_official.data.repository.phone_number

import androidx.lifecycle.LiveData
import com.smile.talent_official.data.models.PhoneNumber

interface PhoneNumbersRepository {
    fun getPhoneNumbers(): LiveData<List<PhoneNumber>>
    fun getPhoneNumbers2(): LiveData<List<PhoneNumber>>

}