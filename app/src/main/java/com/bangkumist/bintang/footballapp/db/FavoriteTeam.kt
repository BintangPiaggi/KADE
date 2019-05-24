package com.bangkumist.bintang.footballapp.db

class FavoriteTeam(
    val id: Long?,
    val teamId: String?,
    val nameTeam: String?,
    val photoTeam: String?

) {
    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val NAME_TEAM: String = "NAME_TEAM"
        const val PHOTO_TEAM: String = "PHOTO_TEAM"
    }
}