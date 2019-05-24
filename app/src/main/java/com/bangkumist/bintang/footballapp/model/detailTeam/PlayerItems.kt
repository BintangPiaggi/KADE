package com.bangkumist.bintang.footballapp.model.detailTeam

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerItems (
    @SerializedName("idPlayer")
    var mIdPlayer : String? = null,
    @SerializedName("strPlayer")
    var mPlayerName : String? = null,
    @SerializedName ("strPosition")
    var mPlayerPosition : String? = null,
    @SerializedName("strCutout")
    var mPlayerPhoto : String? = null
):Parcelable