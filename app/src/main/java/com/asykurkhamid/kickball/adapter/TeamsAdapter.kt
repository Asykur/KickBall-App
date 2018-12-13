package com.asykurkhamid.kickball.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asykurkhamid.kickball.R
import com.asykurkhamid.kickball.pojo.DataTeamPerItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_teams.view.*

class TeamsAdapter(ctx: Context?, dataAllTeam: List<DataTeamPerItem>) : RecyclerView.Adapter<TeamsHolder>(){

    private var datas : List<DataTeamPerItem> = dataAllTeam
    private var ctx : Context? = ctx

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsHolder {
        return TeamsHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_teams,parent,false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: TeamsHolder, position: Int) {
        datas[position].let { holder.bind(it) }
        holder.itemView.setOnClickListener {

        }
    }

}

class TeamsHolder (view: View):RecyclerView.ViewHolder(view){
    private val tvTeamName = view.teamsName
    private val tvCaption = view.captionTeam
    private val imgTeams = view.imgTeams

    fun bind(dataAllTeam : DataTeamPerItem?){
        tvTeamName.text = dataAllTeam?.strTeam
        tvCaption.text = dataAllTeam?.strDescriptionEN
        Glide.with(imgTeams.context)
                .load(dataAllTeam?.strTeamBadge)
                .into(imgTeams)
    }

}
