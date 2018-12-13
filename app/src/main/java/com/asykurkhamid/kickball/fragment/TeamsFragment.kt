package com.asykurkhamid.kickball.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner

import com.asykurkhamid.kickball.R
import com.asykurkhamid.kickball.R.array.league_list
import com.asykurkhamid.kickball.adapter.TeamsAdapter
import com.asykurkhamid.kickball.baseClass.BaseFagment
import com.asykurkhamid.kickball.pojo.DataTeamPerItem
import com.asykurkhamid.kickball.presenter.DataPresenter
import com.asykurkhamid.kickball.utils.TeamsView
import com.asykurkhamid.kickball.utils.gone
import com.asykurkhamid.kickball.utils.visible
import kotlinx.android.synthetic.main.fragment_teams.view.*

class TeamsFragment : BaseFagment(), TeamsView {

    private lateinit var pgTeam : ProgressBar
    private lateinit var rvTeams : RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var leagueID : String
    private lateinit var leagueName: String
    private lateinit var adapter : TeamsAdapter
    private lateinit var presenter : DataPresenter
    private var dataTeamList: MutableList<DataTeamPerItem> = mutableListOf()


    companion object {
        fun newInstance() : TeamsFragment{
            val teamsFragment = TeamsFragment()
            val args = Bundle()
            teamsFragment.arguments = args
            return teamsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_teams,container,false)
        pgTeam = view.pgTeams
        rvTeams = view.rvNTeams
        spinner = view.spinnerTeams
        getAllTeams()
        initRecyCler()
        return view
    }

    private fun getAllTeams() {
        presenter = DataPresenter(null,null,this)
        val spinnerData = resources.getStringArray(league_list)
        val spAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,spinnerData)
        spinner.adapter = spAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeams(leagueName)
            }

        }

    }

    private fun initRecyCler() {
        adapter = TeamsAdapter(context,dataTeamList)
        rvTeams.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        rvTeams.adapter = adapter
    }

    override fun showLoading() {
        pgTeam.visible()
    }

    override fun hideLoading() {
        pgTeam.gone()
    }

    override fun showTeamList(dataTeams: List<DataTeamPerItem>?) {
        if (dataTeams!=null){
            dataTeamList.clear()
            dataTeamList.addAll(dataTeams)
            adapter.notifyDataSetChanged()
        }
    }

}
