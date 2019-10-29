package id.gits.gitsmvvmkotlin.mvvm.teamdetail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.co.gits.gitsdriver.utils.GitsHelper
import id.gits.gitsmvvmkotlin.BR
import id.gits.gitsmvvmkotlin.R
import id.gits.gitsmvvmkotlin.base.BaseActivity
import id.gits.gitsmvvmkotlin.data.model.Team
import id.gits.gitsmvvmkotlin.databinding.ActivityTeamDetailBinding
import id.gits.gitsmvvmkotlin.util.loadImage

class TeamDetailActivity : BaseActivity() {

    companion object {
        fun start(context: Context, key : String, data : Team) {
           context.startActivity(Intent(context, TeamDetailActivity::class.java).putExtra(key, data))
        }
    }

    private lateinit var team : Team
    private lateinit var viewBinding : ActivityTeamDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        team = intent.getSerializableExtra(GitsHelper.Const.EXTRA_GLOBAL) as Team
        Log.d("aldi123", team.toString())

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_team_detail)
        viewBinding.apply {
            setVariable(BR.item, team)
            team.teamLogo?.let { imgTeamLogo.loadImage(it) }
        }
    }
}
