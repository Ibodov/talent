package com.smile.talent_official.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smile.talent_official.Event

class EmployerViewModel : ViewModel() {
    private val _popBackStack = MutableLiveData<Event<Unit>>()
    val popBackStack: LiveData<Event<Unit>>
        get() = _popBackStack


}