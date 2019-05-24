package com.bangkumist.bintang.footballapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchItems(
    @SerializedName("idEvent")
    var mIdMatch: String? = null,

    @SerializedName("strHomeTeam")
    var mHomeMatch: String? = null,

    @SerializedName("strAwayTeam")
    var mAwayMatch: String? = null,

    @SerializedName("dateEvent")
    var mDateMatch: String? = null,

    @SerializedName("strTime")
    var mTimeMatch: String? = null,

    @SerializedName("intHomeScore")
    var mHomeScore: String? = null,

    @SerializedName("intAwayScore")
    var mAwayScore: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    val mHomeGK: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    val mAwayGk: String? = null,

    @SerializedName("strHomeLineupDefense")
    val mHomeDef: String? = null,

    @SerializedName("strAwayLineupDefense")
    val mAwayDef: String? = null,

    @SerializedName("strHomeLineupMidfield")
    val mHomeMid: String? = null,

    @SerializedName("strAwayLineupMidfield")
    val mAwayMid: String? = null,

    @SerializedName("strHomeLineupForward")
    val mHomeFw: String? = null,

    @SerializedName("strAwayLineupForward")
    val mAwayFw: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    val mHomeSub: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    val mAwaySub: String? = null,

    @SerializedName("strHomeYellowCards")
    var mHomeYellow: String? = null,

    @SerializedName("strHomeGoalDetails")
    val mHomeGoal: String? = null,

    @SerializedName("strAwayGoalDetails")
    val mAwayGoal: String? = null,

    @SerializedName("strAwayYellowCards")
    var mAwayYellow: String? = null

    ) : Parcelable