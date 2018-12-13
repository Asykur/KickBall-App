package com.asykurkhamid.kickball.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asykurkhamid.kickball.baseClass.BaseFagment
import com.asykurkhamid.kickball.R
import com.asykurkhamid.kickball.utils.Variables
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : BaseFagment() {

    companion object {
        fun newInstance(): FavoriteFragment {
            val favoriteFragment = FavoriteFragment()
            val args = Bundle()
            favoriteFragment.arguments = args
            return favoriteFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val views = inflater.inflate(R.layout.fragment_favorite, container, false)
        val favoriteAdapter = FavoritePagerAdapter(childFragmentManager)
        val vPager = views.vpFav
        vPager.adapter = favoriteAdapter
        views.tabFav.setupWithViewPager(vPager)
        return views
    }

    class FavoritePagerAdapter(fm : FragmentManager?) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position){
                0 -> {
                    LastMatchFavoriteFragment()
                }1 -> {
                    NextMatchFavoriteFragment()
                }else -> {
                    TeamsFavoriteFragment()
                }
            }
        }

        override fun getCount(): Int {
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> Variables.LAST
                1 -> Variables.NEXT
                else -> Variables.TEAMS
            }
        }
    }
}


