package com.bangkumist.bintang.footballapp.model.detailLeague

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StandingsItems (
    @SerializedName("name")
    var mStandingsName : String? = null,

    @SerializedName("played")
    var mStandingsPlayed : String? = null,

    @SerializedName("goalsfor")
    var mStandingsGoals : String? = null,

    @SerializedName("win")
    var mStandingsWin : String? = null,

    @SerializedName("draw")
    var mStandingsDraw : String? = null,

    @SerializedName("loss")
    var mStandingsLose : String? = null,

    @SerializedName("total")
    var mStandingsPoint : String? = null
):Parcelable