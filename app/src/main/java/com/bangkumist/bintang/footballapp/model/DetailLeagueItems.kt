package com.bangkumist.bintang.footballapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailLeagueItems(
    @SerializedName("idLeague")
    var mIdLeagueDetail: String? = null,

    @SerializedName("strLeague")
    var mNameLeagueDetail: String? = null,

    @SerializedName("strBadge")
    var mLogoLeagueDetail: String? = null,

    @SerializedName("intFormedYear")
    var mFormedLeagueDetail: String? = null
) : Parcelable
