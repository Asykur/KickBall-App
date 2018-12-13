package com.asykurkhamid.kickball.fragment


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.asykurkhamid.kickball.adapter.NextMatchAdapter
import com.asykurkhamid.kickball.baseClass.BaseFagment
import com.asykurkhamid.kickball.pojo.DataNextPerItem
import com.asykurkhamid.kickball.pojo.DataTeamPerItem
import com.asykurkhamid.kickball.R
import com.asykurkhamid.kickball.R.array.league_list
import com.asykurkhamid.kickball.presenter.DataPresenter
import com.asykurkhamid.kickball.utils.*
import kotlinx.android.synthetic.main.fragment_next_match.view.*

class NextMatchFragment : BaseFagment(),NextMatchView, SearchListener {
    override fun doSearch(text: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var pgNext: ProgressBar
    private lateinit var rvNextMatch: RecyclerView
    private var dataNextTeam: MutableList<DataTeamPerItem> = mutableListOf()
    private var dataNextList: MutableList<DataNextPerItem> = mutableListOf()
    private lateinit var presenter: DataPresenter
    private lateinit var adapterNext: NextMatchAdapter
    private lateinit var spinner: Spinner
    private lateinit var leagueID : String
    private lateinit var leagueName: String


    companion object : SearchListener {
        override fun doSearch(text: String) {

        }

        fun getSearchIntance():SearchListener{
            return this
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val views = inflater.inflate(R.layout.fragment_next_match, container, false)
        pgNext = views.pgNext
        rvNextMatch = views.rvNextMatch
        spinner = views.spinnerNextMatch
        getNextMatchData()
        initRecycler()
        return views
    }

    private fun initRecycler() {
        adapterNext = NextMatchAdapter(dataNextList,context,dataNextTeam)
        rvNextMatch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvNextMatch.adapter = adapterNext
    }

    private fun getNextMatchData() {
        presenter = DataPresenter(null,this,null)
        val spinnerData = resources.getStringArray(league_list)
        val spAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,spinnerData)
        spinner.adapter = spAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                leagueID = when(position){
                    0 -> "4335"
                    1 -> "4328"
                    2 -> "4329"
                    3 -> "4331"
                    4 -> "4332"
                    5 -> "4422"
                    else -> "4334"
                }
                presenter.getNextMatch(leagueID,leagueName)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }
    }

    override fun showLoading() {
        pgNext.visible()
    }

    override fun hideLoading() {
        pgNext.gone()
    }

    override fun showNextMatchList(dataNext: List<DataNextPerItem>?, dataTeam: List<DataTeamPerItem>?) {
        if (dataNext != null){
            dataNextList.clear()
            dataNextList.addAll(dataNext)
        }
        if (dataTeam != null){
            dataNextTeam.clear()
            dataNextTeam.addAll(dataTeam)
        }
        adapterNext.notifyDataSetChanged()
    }
}
