package com.smile.talent_official.ui.start

import android.app.Application
import androidx.lifecycle.*
import com.smile.talent_official.Event
import com.smile.talent_official.RecyclerViewCallback
import com.smile.talent_official.db.TalentDatabase
import com.smile.talent_official.db.repository.TalentRealization
import com.smile.talent_official.db.repository.TalentRepository
import com.smile.talent_official.data.models.TalentModel
import com.smile.talent_official.data.network.service.NetworkService
import com.smile.talent_official.data.repository.phone_number.PhoneNumbersRepository
import com.smile.talent_official.data.repository.phone_number.PhoneNumbersRepositoryImpl
import kotlinx.coroutines.launch

class StartViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TalentRepository =
        TalentRealization(TalentDatabase.getInstance(application).getTalentDao())
    private val phoneNumbersRepository: PhoneNumbersRepository = PhoneNumbersRepositoryImpl(
        NetworkService.phoneNumberApi(),
        TalentDatabase.getInstance(application).getTalentDao()
    )

    val getAll = MutableLiveData<Unit>()

    val getAllResource = Transformations.switchMap(getAll) {
        repository.allTalents
    }

    init {
        getAll.postValue(Unit)
    }

    fun refresh() {
        getAll.postValue(Unit)
    }

    private val _openTalent = MutableLiveData<Event<TalentModel>>()
    val openTalent: LiveData<Event<TalentModel>>
        get() = _openTalent

    val recyclerViewCallback = object : RecyclerViewCallback {
        override fun onItemClick(any: Any) {
            if (any is TalentModel) {
                _openTalent.postValue(Event(any))
            }
        }

        override fun onDeleteItem(any: Any) {
            if (any is TalentModel){
                viewModelScope.launch {
                    repository.deleteTalent(any)
                    getAll.postValue(Unit)
                }
            }
        }
    }

    private val getAllNumbers = MutableLiveData<Unit>()

    val getAllNumbersResource = Transformations.switchMap(getAllNumbers) {
        phoneNumbersRepository.getPhoneNumbers()
    }

    init {
        getAllNumbers.postValue(Unit)
    }
}