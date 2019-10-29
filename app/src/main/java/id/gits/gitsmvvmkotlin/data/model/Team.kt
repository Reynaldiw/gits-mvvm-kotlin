package id.gits.gitsmvvmkotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Team(
        @SerializedName("idTeam")
        val idTeam : Int? = null,
        @SerializedName("strTeam")
        val teamName : String? = null,
        @SerializedName("strTeamBadge")
        val teamLogo : String? = null,
        @SerializedName("strDescriptionEN")
        val teamDesc : String? = null
) : Serializable