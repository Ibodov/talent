package com.smile.talent_official.ui.worker

import android.app.Application
import androidx.lifecycle.*
import com.smile.talent_official.Event
import com.smile.talent_official.db.TalentDatabase
import com.smile.talent_official.db.repository.TalentRealization
import com.smile.talent_official.db.repository.TalentRepository
import com.smile.talent_official.data.models.TalentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddWorkerViewModel(app: Application) :AndroidViewModel(app) {
    private val repository: TalentRepository = TalentRealization (TalentDatabase.getInstance(app).getTalentDao())

    private val _popBackStack = MutableLiveData<Event<Unit>>()
    val popBackStack: LiveData<Event<Unit>>
        get() = _popBackStack

    fun insert(talentModel: TalentModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val id = repository.insertTalent(talentModel)
            _popBackStack.postValue(Event(Unit))

        }
    }
}
