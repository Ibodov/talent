package com.smile.talent_official.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    primaryKeys = ["talentId", "phoneId"], tableName = "talentPhone")
data class TalentPhone(
    @ColumnInfo (name = "talentId")
    val talentId : Int,

    @ColumnInfo (name = "phoneId")
    val phoneId : Int
)
