package com.bangkumist.bintang.footballapp.model.detailTeam

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailTeamItems(
    @SerializedName("idTeam")
    var mIdTeam : String? = null,
    @SerializedName("strTeam")
    var mDetailNameTeam : String? = null,
    @SerializedName ("intFormedYear")
    var mDetailFormedTeam : String? = null,
    @SerializedName("strAlternate")
    var mDetailAlternateTeam : String? = null,
    @SerializedName("strDescriptionEN")
    var mDetailDescTeam : String? = null,
    @SerializedName("strTeamBadge")
    var mDetailBadgeTeam : String? = null
):Parcelable