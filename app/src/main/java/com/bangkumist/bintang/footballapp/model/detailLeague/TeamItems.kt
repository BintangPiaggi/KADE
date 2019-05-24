package com.bangkumist.bintang.footballapp.model.detailLeague

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamItems (
    @SerializedName("strTeam")
    var mNameTeam: String? = null,
    @SerializedName("strTeamBadge")
    var mTeamBadge: String? = null,
    @SerializedName("idTeam")
    var mIdTeam: String? = null
):Parcelable