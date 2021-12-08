package com.smile.talent_official.db.repository

import androidx.lifecycle.LiveData
import com.smile.talent_official.db.dao.TalentDao
import com.smile.talent_official.data.models.TalentModel

//5 - Объединяет интерфейсы TalentDao и TalentRepository
class TalentRealization(private val talentDao: TalentDao): TalentRepository { //Имплементируем методы из TalentRepository
    // в конструктур TalentRealization передаем TalentDao
    override val allTalents: LiveData<List<TalentModel>>
        get() = talentDao.getAllTalents()

    override suspend fun insertTalent(talentModel: TalentModel) {
        talentDao.insert(talentModel) //связываем

    }

    override suspend fun deleteTalent(talentModel: TalentModel) {
       talentDao.delete(talentModel)
    }


    override suspend fun updateTalent(talentModel: TalentModel) {
        talentDao.update(talentModel)

    }
}