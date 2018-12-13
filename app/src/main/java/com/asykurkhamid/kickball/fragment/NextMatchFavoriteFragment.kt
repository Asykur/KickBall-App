package com.asykurkhamid.kickball.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.asykurkhamid.kickball.adapter.NextMatchFavoriteAdapter
import com.asykurkhamid.kickball.database.database
import com.asykurkhamid.kickball.pojo.DataFavorite
import com.asykurkhamid.kickball.pojo.DataFavoriteNext
import com.asykurkhamid.kickball.R
import kotlinx.android.synthetic.main.fragment_next_match_favorite.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class NextMatchFavoriteFragment : Fragment() {

    private var favoriteNext: MutableList<DataFavoriteNext> = mutableListOf()
    private lateinit var rvFavoriteNext: RecyclerView
    private lateinit var layoutEmptyNext: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_next_match_favorite, container, false)
        rvFavoriteNext = view.rvFavNextMatch
        layoutEmptyNext = view.layoutEmptyNext
        rvFavoriteNext.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        loadFavNextMatch()
        return view
    }

    override fun onResume() {
        super.onResume()
        loadFavNextMatch()
    }

    private fun loadFavNextMatch() {
        favoriteNext.clear()
        context?.database?.use {
            val result = select(DataFavorite.TABLE_FAVORITE_NEXT)
            val fav = result.parseList(classParser<DataFavoriteNext>())
            favoriteNext.addAll(fav)
            val adapter = NextMatchFavoriteAdapter(favoriteNext, context)
            rvFavoriteNext.adapter = adapter
            adapter.notifyDataSetChanged()

            if (fav.isNotEmpty()){
                rvFavoriteNext.visibility = View.VISIBLE
                layoutEmptyNext.visibility = View.GONE
            }else{
                rvFavoriteNext.visibility = View.GONE
                layoutEmptyNext.visibility = View.VISIBLE
            }
        }
    }

}
