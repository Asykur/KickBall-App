package com.asykurkhamid.kickball.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asykurkhamid.kickball.activity.DetailLastMatchActivity
import com.asykurkhamid.kickball.pojo.DataFavorite
import com.asykurkhamid.kickball.R
import kotlinx.android.synthetic.main.item_favorite.view.*
import java.text.SimpleDateFormat

class LastMatchFavoriteAdapter(private val favoriteLast: List<DataFavorite>, private var context: Context?)
    : RecyclerView.Adapter<LastMatchFavoriteAdapter.FavViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favoriteLast.size
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bindDataLast(favoriteLast[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(context?.applicationContext, DetailLastMatchActivity::class.java)
            intent.putExtra("favoriteLast", favoriteLast[position])
            context?.startActivity(intent)
            val activity: Activity = context as Activity
            activity.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)

        }
    }

    class FavViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateFav = view.tvDateEventFav
        private val homeTeamFav = view.tvHomeTeamFav
        private val homeScoreFav = view.tvHomeScoreFav
        private val awayTeamFav = view.tvAwayTeamFav
        private val awayScoreFav = view.tvAwayScoreFav
        private val timeFav = view.tvTimeFav

        fun bindDataLast(favorite: DataFavorite?) {
            val inFormat = SimpleDateFormat("yyyy-MM-dd")
            val dates = inFormat.parse(favorite?.dateEvent)
            val outFormat = SimpleDateFormat("EEEE, dd MMM yyyy")
            val event = outFormat.format(dates)

            dateFav.text = event
            homeTeamFav.text = favorite?.homeTeamName
            awayTeamFav.text = favorite?.awayTeamName
            var homeScore = favorite?.homeScore
            var awayScore = favorite?.awayScore
            if (!TextUtils.isEmpty(homeScore)) {
                awayScoreFav.visibility = View.VISIBLE
                awayScoreFav.text = favorite?.awayScore

            }
            if (!TextUtils.isEmpty(awayScore)) {
                homeScoreFav.visibility = View.VISIBLE
                homeScoreFav.text = favorite?.homeScore
            }
            timeFav.text = favorite?.timeEvent?.substring(0, 5)

        }


    }
}