package com.asykurkhamid.kickball.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.asykurkhamid.kickball.adapter.LastMatchFavoriteAdapter
import com.asykurkhamid.kickball.database.database
import com.asykurkhamid.kickball.pojo.DataFavorite
import com.asykurkhamid.kickball.R

import kotlinx.android.synthetic.main.fragment_last_match_favorite.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select


class LastMatchFavoriteFragment : Fragment() {

    private var favoriteLast: MutableList<DataFavorite> = mutableListOf()
    private lateinit var rvFavoriteLast: RecyclerView
    private lateinit var layoutEmptyLast: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_last_match_favorite, container, false)
        rvFavoriteLast = view.rvFavLastMatch
        layoutEmptyLast = view.layoutEmptyLast
        rvFavoriteLast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        loadFavLastMatch()
        return view
    }

    override fun onResume() {
        super.onResume()
        loadFavLastMatch()
    }

    private fun loadFavLastMatch() {
        favoriteLast.clear()
        context?.database?.use {
            val result = select(DataFavorite.TABLE_FAVORITE_LAST)
            val fav = result.parseList(classParser<DataFavorite>())
            favoriteLast.addAll(fav)
            val adapter = LastMatchFavoriteAdapter(favoriteLast, context)
            rvFavoriteLast.adapter = adapter
            adapter.notifyDataSetChanged()

            if (fav.isNotEmpty()) {
                rvFavoriteLast.visibility = View.VISIBLE
                layoutEmptyLast.visibility = View.GONE
            } else {
                rvFavoriteLast.visibility = View.GONE
                layoutEmptyLast.visibility = View.VISIBLE
            }
        }
    }


}
