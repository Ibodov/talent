package com.smile.talent_official.ui.phone_numbers

import android.app.Application
import androidx.lifecycle.*
import com.smile.talent_official.data.models.PhoneNumber
import com.smile.talent_official.data.network.service.NetworkService
import com.smile.talent_official.data.repository.phone_number.PhoneNumbersRepository
import com.smile.talent_official.data.repository.phone_number.PhoneNumbersRepositoryImpl
import com.smile.talent_official.db.TalentDatabase

class PhoneNumbersViewModel
constructor(
    val app:Application
): AndroidViewModel(app) {
    val repository: PhoneNumbersRepository = PhoneNumbersRepositoryImpl(
        NetworkService.phoneNumberApi(),
        TalentDatabase.getInstance(app).getTalentDao()
    )

    private val getAllNumbers = MutableLiveData<Unit>()

    val getAllNumbersResource = Transformations.switchMap(getAllNumbers) {
        repository.getPhoneNumbers()
    }

    init {
        getAllNumbers.postValue(Unit)
    }
}