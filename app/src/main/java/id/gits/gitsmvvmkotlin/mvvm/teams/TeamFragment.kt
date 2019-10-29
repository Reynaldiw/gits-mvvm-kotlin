package id.gits.gitsmvvmkotlin.mvvm.teams

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import id.co.gits.gitsdriver.utils.GitsHelper
import android.view.ViewGroup
import id.gits.gitsmvvmkotlin.base.BaseFragment
import id.gits.gitsmvvmkotlin.data.model.Team
import id.gits.gitsmvvmkotlin.databinding.FragmentTeamBinding
import id.gits.gitsmvvmkotlin.mvvm.main.MainActivity
import id.gits.gitsmvvmkotlin.util.NavigationParamGlobal
import id.gits.gitsmvvmkotlin.util.showToast
import id.gits.gitsmvvmkotlin.util.startNewActivity
import id.gits.gitsmvvmkotlin.mvvm.teamdetail.TeamDetailActivity
import id.gits.gitsmvvmkotlin.util.putArgs
import kotlinx.android.synthetic.main.fragment_team.*

class TeamFragment : BaseFragment(), TeamItemUserActionListener {

    companion object {
        fun newInstance() = TeamFragment().putArgs {  }
    }

    private lateinit var viewBinding : FragmentTeamBinding
    private lateinit var viewModel: TeamViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewBinding = FragmentTeamBinding.inflate(inflater, container, false).apply {
            viewModel = (activity as MainActivity).obtainTeamViewModel().apply {
                openDetailTeam.observe(this@TeamFragment, Observer {team ->
                    onItemTeamClicked(team!!)
                })
            }
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel
            it.setLifecycleOwner(this)
        }

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupTeams()
        setupViewListener()
    }

    override fun onItemTeamClicked(team: Team) {
        (activity as MainActivity).apply {
            team.teamName?.let { showToast(context!!, it) }
            TeamDetailActivity.start(context!!, GitsHelper.Const.EXTRA_GLOBAL, team)
        }
    }

    private fun setupViewModel() {
        viewModel = viewBinding.viewModel!!

        viewModel.start()
    }

    private fun setupTeams() {
        rvTeam?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TeamAdapter(viewModel)

        }
    }

    private fun setupViewListener() {
        swipeTeam.setOnRefreshListener {
            rvTeam?.apply {
                adapter!!.notifyItemRangeRemoved(0, adapter!!.itemCount)
                viewModel.start()

                swipeTeam.isRefreshing = false
            }
        }
    }

}
