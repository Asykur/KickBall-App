package com.asykurkhamid.kickball.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asykurkhamid.kickball.activity.DetailNextMatchActivity
import com.asykurkhamid.kickball.pojo.DataNextPerItem
import com.asykurkhamid.kickball.pojo.DataTeamPerItem
import com.asykurkhamid.kickball.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_next_match.view.*
import java.text.SimpleDateFormat

class NextMatchAdapter(data: List<DataNextPerItem>, ctx: Context?, private var dataAllTeam: List<DataTeamPerItem>?) : RecyclerView.Adapter<DNHolder>() {

    private var datas: List<DataNextPerItem> = data
    private var context: Context? = ctx

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DNHolder {
        return DNHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_next_match,parent,false)
        )
    }

    override fun getItemCount(): Int {
       return datas.size
    }

    @SuppressLint("PrivateResource")
    override fun onBindViewHolder(holder: DNHolder, position: Int) {
        datas[position].let { holder.bindData(it, dataAllTeam) }
        holder.itemView.setOnClickListener {
            val intent = Intent(context?.applicationContext, DetailNextMatchActivity::class.java)
            intent.putExtra("allDataNext", datas[position])
            context?.startActivity(intent)
            val activity: Activity = context as Activity
            activity.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
        }
    }
}

class DNHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imgNextHome = view.imgNextHome
    private val imgNextAway = view.imgNextAway
    private val tvNextHome = view.tvNexthome
    private val tvNextAway = view.tvNextAway
    private val tvNextDate = view.tvNextDate

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    fun bindData(datas: DataNextPerItem?, dataAllTeam : List<DataTeamPerItem>?){
        //Date format
        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val dates = inFormat.parse(datas?.dateEvent)
        val outFormat = SimpleDateFormat("EEEE, dd MMM yyyy")
        val event = outFormat.format(dates)

        val result = datas?.strTime?.substring(0,5)

        tvNextHome.text = datas?.strHomeTeam
        tvNextAway.text = datas?.strAwayTeam
        tvNextDate.text = event+"\n"+result

        if (dataAllTeam != null) {
            for (i in dataAllTeam) {
                if (datas?.strHomeTeam.equals(i.strTeam)) {
                    Glide.with(imgNextHome.context)
                            .load(i.strTeamBadge)
                            .into(imgNextHome)
                }
            }
        }
        if (dataAllTeam != null) {
            for (i in dataAllTeam) {
                if (datas?.strAwayTeam.equals(i.strTeam)) {
                    Glide.with(imgNextAway.context)
                            .load(i.strTeamBadge)
                            .into(imgNextAway)
                }
            }
        }
    }
}
