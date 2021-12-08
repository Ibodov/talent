package com.smile.talent_official.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.smile.talent_official.data.models.TalentModel

//3.
@Dao
interface TalentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(talentModel: TalentModel)

//    @Query ("DELETE  FROM talent")
//    fun deleteAllTalent()

    @Update
    suspend fun update(talentModel: TalentModel)

    @Query("SELECT * FROM profile_table")
    fun getAllTalents(): LiveData<List<TalentModel>>

    @Query("SELECT * FROM profile_table")
    suspend fun getAllSuspend(): List<TalentModel>

    @Delete
    suspend fun delete(i: TalentModel)
}