package com.bangkumist.bintang.footballapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueItems(
    @SerializedName("idLeague")
    var mIdLeague: String? = null,

    @SerializedName("strLeague")
    var mNameLeague: String? = null,

    @SerializedName("strBadge")
    var mLogoLeague: String? = null

) : Parcelable
