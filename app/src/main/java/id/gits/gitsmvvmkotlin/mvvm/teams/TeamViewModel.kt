package id.gits.gitsmvvmkotlin.mvvm.teams

import android.app.Application
import android.databinding.BindingAdapter
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import id.co.gits.gitsbase.BaseViewModel
import id.gits.gitsmvvmkotlin.data.model.Team
import id.gits.gitsmvvmkotlin.data.source.GitsDataSource
import id.gits.gitsmvvmkotlin.data.source.GitsRepository
import id.gits.gitsmvvmkotlin.util.SingleLiveEvent

class TeamViewModel(context : Application, private val gitsRepository: GitsRepository) :
        BaseViewModel(context) {

    var teamsListLive = SingleLiveEvent<List<Team>>()
    var errorMessageLive = SingleLiveEvent<String>()
    internal val openDetailTeam = SingleLiveEvent<Team>()

    fun start() {
        getTeams()
    }

    private fun getTeams() {
        gitsRepository.getTeams(object : GitsDataSource.GetTeamsCallback {
            override fun onShowProgressDialog() {
                eventShowProgress.value = true
            }

            override fun onHideProgressDialog() {
                eventShowProgress.value = false
            }

            override fun onSuccess(data: List<Team>) {
                if (data.isNotEmpty()) {
                    teamsListLive.value = data
                } else {
                    onFailed(101, "Data kosong")
                }
            }

            override fun onFinish() {
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                errorMessageLive.value = errorMessage
            }

        })
    }
}