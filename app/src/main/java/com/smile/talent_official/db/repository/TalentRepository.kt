package com.smile.talent_official.db.repository

import androidx.lifecycle.LiveData
import com.smile.talent_official.data.models.TalentModel


//4 Это интерфейс перечисляет методы
interface TalentRepository {
    val allTalents: LiveData<List<TalentModel>>
    suspend fun insertTalent(talentModel: TalentModel) //используем suspend, чтоб можно было использовать в куротинах
    suspend fun deleteTalent(talentModel: TalentModel)
    suspend fun updateTalent(talentModel: TalentModel)

}