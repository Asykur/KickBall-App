package com.asykurkhamid.kickball.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.asykurkhamid.kickball.R
import com.asykurkhamid.kickball.utils.Variables
import kotlinx.android.synthetic.main.fragment_matches.view.*

class MatchesFragment : Fragment() {

    companion object {
        fun newInstance():MatchesFragment{
            val matchesFragment = MatchesFragment()
            val args = Bundle()
            matchesFragment.arguments = args
            return matchesFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_matches, container, false)
        val matchAdapetr = MatchesAdapter(childFragmentManager)
        val vPager = view.vpMathes
        vPager.adapter = matchAdapetr
        view.tabMatches.setupWithViewPager(vPager)
        return view
    }

    class MatchesAdapter(ma : FragmentManager?):FragmentPagerAdapter(ma){
        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> {
                    NextMatchFragment()
                }
                else ->{
                    LastMatchFragment()
                }
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position){
                0->Variables.NEXT
                else->Variables.LAST
            }
        }

        override fun getCount(): Int {
            return 2
        }

    }
}
