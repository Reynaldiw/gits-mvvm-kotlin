package id.gits.gitsmvvmkotlin.mvvm.teams

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import id.co.gits.gitsdriver.utils.GitsBindableAdapter
import id.gits.gitsmvvmkotlin.BR
import id.gits.gitsmvvmkotlin.data.model.Team
import id.gits.gitsmvvmkotlin.databinding.ItemTeamBinding
import id.gits.gitsmvvmkotlin.util.loadImage

class TeamAdapter(private var viewModel: TeamViewModel) :
        RecyclerView.Adapter<TeamAdapter.ViewHolder>(), GitsBindableAdapter<Team>{

    var datas = ArrayList<Team>()

    override fun setRecyclerViewData(data: List<Team>) {
        this.datas.addAll(data)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(ItemTeamBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = datas[position]


        val userActionListener = object : TeamItemUserActionListener {
            override fun onItemTeamClicked(team: Team) {
                viewModel.openDetailTeam.value = team
            }
        }

        holder.bindItem(team, userActionListener)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.apply {
            itemTeamBinding.setVariable(BR.team, null)
            itemTeamBinding.setVariable(BR.userActionListener, null)
            itemTeamBinding.executePendingBindings()
        }

        super.onViewRecycled(holder)
    }

    class ViewHolder(val itemTeamBinding: ItemTeamBinding) :
            RecyclerView.ViewHolder(itemTeamBinding.root){

        fun bindItem(team : Team, actionListener: TeamItemUserActionListener) {
            itemTeamBinding.apply {
                setVariable(BR.team, team)
                setVariable(BR.userActionListener, actionListener)
                executePendingBindings()
                team.teamLogo?.let { imgTeamLogo.loadImage(it) }
            }
        }

    }
}