package com.asykurkhamid.kickball.fragment

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.asykurkhamid.kickball.baseClass.BaseFagment
import com.asykurkhamid.kickball.pojo.DataLastPerItem
import com.asykurkhamid.kickball.presenter.DataPresenter
import com.asykurkhamid.kickball.R
import com.asykurkhamid.kickball.R.array.league_list
import com.asykurkhamid.kickball.adapter.LastMatchAdapter
import com.asykurkhamid.kickball.pojo.DataTeamPerItem
import com.asykurkhamid.kickball.utils.LastMatchView
import com.asykurkhamid.kickball.utils.SearchListener
import com.asykurkhamid.kickball.utils.gone
import com.asykurkhamid.kickball.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_last_match.view.*

class LastMatchFragment : BaseFagment(), LastMatchView, SearchListener {



    private var pgLast: ProgressBar? = null
    private var rvLastMatch: RecyclerView? = null
    private var dataLast: MutableList<DataLastPerItem> = mutableListOf()
    private var dataTeam: MutableList<DataTeamPerItem> = mutableListOf()
    private lateinit var presenter: DataPresenter
    private lateinit var adapterLast: LastMatchAdapter
    private lateinit var spinner: Spinner
    private lateinit var leagueID: String
    private lateinit var leagueName: String

    companion object : SearchListener {
        override fun doSearch(text: String) {

        }

        fun getSearchIntance():SearchListener{
            return this
        }
    }
    override fun doSearch(text: String) {
        dataLast.removeAll(dataLast)
        dataTeam.removeAll(dataTeam)
        searchLastMatch(text)
    }

    private fun searchLastMatch(text: String) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val views = inflater.inflate(R.layout.fragment_last_match, container, false)
        pgLast = views.pgLast
        rvLastMatch = views.rvLastMatch
        spinner = views.spinnerLastMatch
        getLastMatchData()
        initRecycler()
        return views
    }



    private fun initRecycler() {
        adapterLast = LastMatchAdapter(context, dataLast, dataTeam)
        rvLastMatch?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvLastMatch?.adapter = adapterLast
    }

    private fun getLastMatchData() {
        presenter = DataPresenter(this, null,null)
        val spinnerData = resources.getStringArray(league_list)
        val spAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,spinnerData)
        spinner.adapter = spAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                leagueID = when (position) {
                    0 -> "4335"
                    1 -> "4328"
                    2 -> "4329"
                    3 -> "4331"
                    4 -> "4332"
                    5 -> "4422"
                    else -> "4334"
                }
                presenter.getLastMatch(leagueID, leagueName)

            }
            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }
    }

    override fun showLoading() {
        pgLast?.visible()
    }

    override fun hideLoading() {
        pgLast?.gone()
    }

    override fun showLastMatchList(data: List<DataLastPerItem>?, datLastTeam: List<DataTeamPerItem>?) {
        if (data != null) {
            this.dataLast.clear()
            this.dataLast.addAll(data)
        }
        if (datLastTeam != null) {
            this.dataTeam.clear()
            this.dataTeam.addAll(datLastTeam)
        }
        adapterLast.notifyDataSetChanged()
    }
}

