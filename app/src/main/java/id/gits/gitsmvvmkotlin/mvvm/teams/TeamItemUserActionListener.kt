package id.gits.gitsmvvmkotlin.mvvm.teams

import id.gits.gitsmvvmkotlin.data.model.Team

interface TeamItemUserActionListener {
    fun onItemTeamClicked(team : Team)
}