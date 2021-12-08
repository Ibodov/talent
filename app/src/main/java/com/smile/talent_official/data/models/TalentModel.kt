package com.smile.talent_official.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.smile.talent_official.R
import java.io.Serializable

//1

@Entity(tableName = "profile_table")
class TalentModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo
    var name: String = "",

    @ColumnInfo
    var specialization: String = "",

    @ColumnInfo
    var aboutMe: String = "",

    @ColumnInfo
    var picture: Int = R.drawable.ic_image,

    @ColumnInfo
    var skill: String = "",

    @SerializedName("phone_number")
    var phoneNumber: String = "",

    var uri: String = ""

) : Serializable