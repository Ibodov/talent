package com.smile.talent_official.data.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

//@Entity
data class PhoneNumber(
    val id: Int?,
    val uid: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("cell_phone")
    val cellPhone: String?,
    @SerializedName("cell_phone_in_e164")
    val cellPhoneIne64: String?
)