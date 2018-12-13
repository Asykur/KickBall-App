package com.asykurkhamid.kickball.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asykurkhamid.kickball.activity.DetailLastMatchActivity
import com.asykurkhamid.kickball.pojo.DataLastPerItem
import com.asykurkhamid.kickball.pojo.DataTeamPerItem
import com.asykurkhamid.kickball.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_last_match.view.*
import java.text.SimpleDateFormat

class LastMatchAdapter(private var context: Context?, private var datas: MutableList<DataLastPerItem>, teams: List<DataTeamPerItem>?)
    : RecyclerView.Adapter<LastMatchAdapter.DataHolder>() {

    private var team: List<DataTeamPerItem>? = teams

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.item_last_match, parent, false
        )
        return DataHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    @SuppressLint("PrivateResource")
    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.bindData(datas[position],team)
        holder.itemView.setOnClickListener {
            val intent = Intent(context?.applicationContext, DetailLastMatchActivity::class.java)
            intent.putExtra("allData", datas[position])
            context?.startActivity(intent)
            val activity: Activity = context as Activity
            activity.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
        }
    }

    class DataHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val date = view.tvDateEvent
        private val homeTeam = view.tvHomeTeam
        private val awayTeam = view.tvAwayTeam
        private val homeScore = view.tvHomeScore
        private val awayScore = view.tvAwayScore
        private val imgHome = view.imgHome
        private val imgAway = view.imgAway

        @SuppressLint("SimpleDateFormat")
        fun bindData(item: DataLastPerItem?, itemTeam: List<DataTeamPerItem>?) {
            //date format
            val inFormat = SimpleDateFormat("yyyy-MM-dd")
            val dates = inFormat.parse(item?.dateEvent)
            val outFormat = SimpleDateFormat("EEEE, dd MMM yyyy")
            val event = outFormat.format(dates)
            date.text = event

            homeTeam.text = item?.strHomeTeam
            awayTeam.text = item?.strAwayTeam
            homeScore.text = item?.intHomeScore
            awayScore.text = item?.intAwayScore

            if (itemTeam != null) {
                for (i in itemTeam) {
                    if (item?.strHomeTeam.equals(i.strTeam)) {
                        Glide.with(homeTeam.context)
                                .load(i.strTeamBadge)
                                .into(imgHome)
                    }
                }
            }
            if (itemTeam != null) {
                for (i in itemTeam) {
                    if (item?.strAwayTeam.equals(i.strTeam)) {
                        Glide.with(awayTeam.context)
                                .load(i.strTeamBadge)
                                .into(imgAway)
                    }
                }
            }
        }
    }
}


