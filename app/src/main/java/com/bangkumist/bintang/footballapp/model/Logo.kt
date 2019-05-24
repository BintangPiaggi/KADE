package com.bangkumist.bintang.footballapp.model

import com.google.gson.annotations.SerializedName

data class Logo (
    @SerializedName("strTeamBadge")
    val mBadge: String? = ""
)