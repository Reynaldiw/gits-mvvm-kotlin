package id.gits.gitsmvvmkotlin.util

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import id.gits.gitsmvvmkotlin.data.source.GitsRepository
import id.gits.gitsmvvmkotlin.mvvm.forgotpassword.ForgotPasswordViewModel
import id.gits.gitsmvvmkotlin.mvvm.login.LoginViewModel
import id.gits.gitsmvvmkotlin.mvvm.main.MainViewModel
import id.gits.gitsmvvmkotlin.mvvm.maindetail.MainDetailViewModel
import id.gits.gitsmvvmkotlin.mvvm.teams.TeamViewModel

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

class ViewModelFactory private constructor(
        private val mApplication: Application,
        private val gitsRepository: GitsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(MainViewModel::class.java) ->
                        MainViewModel(mApplication, gitsRepository)
                    isAssignableFrom(MainDetailViewModel::class.java) ->
                        MainDetailViewModel(mApplication, gitsRepository)
                    isAssignableFrom(LoginViewModel::class.java) ->
                        LoginViewModel(mApplication, gitsRepository)
                    isAssignableFrom(ForgotPasswordViewModel::class.java) ->
                        ForgotPasswordViewModel(mApplication, gitsRepository)
                    isAssignableFrom(TeamViewModel::class.java) ->
                        TeamViewModel(mApplication, gitsRepository)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(mApplication: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(mApplication,
                            Injection.provideGitsRepository(mApplication.applicationContext))
                            .also { INSTANCE = it }
                }
    }
}