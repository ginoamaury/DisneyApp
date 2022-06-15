package com.co.gino.infrastructure.guests.httpclient.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GuestDto(
    @SerializedName("name") val name: String,
    @SerializedName("hasReservation") val hasReservation: Boolean
) : Parcelable