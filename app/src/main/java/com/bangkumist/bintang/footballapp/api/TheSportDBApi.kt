package com.bangkumist.bintang.footballapp.api

import com.bangkumist.bintang.footballapp.BuildConfig

object TheSportDBApi {

    fun getLeague(league: String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/search_all_leagues.php?s=" + league
    }

    fun getDetailLeague(id: String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookupleague.php?id=" + id
    }


    fun getPrevMatch(id: String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventspastleague.php?id=" + id
    }

    fun getNextMatch(id: String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventsnextleague.php?id=" + id
    }


    fun getDetailMatch(id: String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookupevent.php?id=" + id
    }


    fun getSearchMatch(query: String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/searchevents.php?e=" + query
    }

    fun getTeamLogo(query: String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/searchteams.php?t=" + query
    }

    // Api For Final Project

    fun getTeamList(id: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookup_all_teams.php?id=" + id
    }

    fun getStandingsTeam(league: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookuptable.php?l=" + league
    }

    fun getDetailTeam(id: String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookupteam.php?id=" + id
    }
    fun getListPlayer(id: String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookup_all_players.php?id=" + id
    }
    fun getDetailPlayer(id: String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookupplayer.php?id=" + id
    }
    fun getSearchTeam(query: String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/searchteams.php?t=" + query
    }
}