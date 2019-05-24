package com.bangkumist.bintang.footballapp.model.detailTeam

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailPlayerItems(
    @SerializedName("strFanart1")
    var mFanArt : String? = null,
    @SerializedName("strDescriptionEN")
    var mDescPlayerDetail : String? = null,
    @SerializedName("strNationality")
    var mNationPlayerDetail : String? = null
): Parcelable