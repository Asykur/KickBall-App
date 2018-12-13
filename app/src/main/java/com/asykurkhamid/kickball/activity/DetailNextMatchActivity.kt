package com.asykurkhamid.kickball.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.ScrollView
import com.asykurkhamid.kickball.baseClass.BaseActivity
import com.asykurkhamid.kickball.database.database
import com.asykurkhamid.kickball.pojo.DataFavorite
import com.asykurkhamid.kickball.pojo.DataFavoriteNext
import com.asykurkhamid.kickball.pojo.DataNextPerItem
import com.asykurkhamid.kickball.R
import com.asykurkhamid.kickball.pojo.DataTeams
import com.asykurkhamid.kickball.presenter.DetailMatchPresenter
import com.asykurkhamid.kickball.utils.DetailMatchView
import com.asykurkhamid.kickball.utils.gone
import com.asykurkhamid.kickball.utils.visible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_next_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import java.text.SimpleDateFormat

class DetailNextMatchActivity : BaseActivity(),DetailMatchView {

    private var items: DataNextPerItem? = null
    private lateinit var loading: ProgressBar
    private var scroll: ScrollView? = null
    private var category = "Next Match"
    private var isFavorite: Boolean = false
    private var favorite: DataFavoriteNext? = null
    private var listFavorite: List<DataFavoriteNext>? = null
    private lateinit var presenter: DetailMatchPresenter

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_next_match)
        items = intent.getParcelableExtra("allDataNext") //receive data from events fragment
        favorite = intent.getParcelableExtra("favoriteNext") //receive data from favorite fragment
        loading = pgDetailNext
        scroll = scDetailNext
        presenter = DetailMatchPresenter(this)

        if (items != null){
            favoriteState(items?.idEvent)
            setDefaultToolbar(true, getString(R.string.match_schedule), items, category, isFavorite)
            loadData(items,null)

            presenter.getDetailHomeTeam(items?.idHomeTeam)
            presenter.getDetailAwayTeam(items?.idAwayTeam)

        }else if (favorite != null){
            favoriteState(favorite?.eventID)
            setDefaultToolbar(true, getString(R.string.match_schedule), favorite, category, isFavorite)
            loadData(null,listFavorite)

            presenter.getDetailHomeTeam(favorite?.homeID)
            presenter.getDetailAwayTeam(favorite?.awayID)
        }
    }


    private fun favoriteState(idEvent: String?) {
        database.use {
            val result = select(DataFavorite.TABLE_FAVORITE_NEXT).whereArgs("${DataFavorite.EVENT_ID}=$idEvent")
            listFavorite = result.parseList(classParser<DataFavoriteNext>())
            if (listFavorite?.size != 0){
                isFavorite = true
            }
        }
    }

    private fun loadData(item: DataNextPerItem?,favorite: List<DataFavoriteNext>?){
        if (item != null){
            loadDataFromApi(item)
        }else if (favorite != null){
            loadDataFroFavorite(favorite)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadDataFroFavorite(favorite: List<DataFavoriteNext>?) {
        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val dates = inFormat.parse(favorite?.get(0)?.dateEvent)
        val outFormat = SimpleDateFormat("EEEE, dd MMM yyyy")
        val dateEvent = outFormat.format(dates)

        val time = favorite?.get(0)?.timeEvent?.substring(0, 5)

        tvHomeNextMatch.text = favorite?.get(0)?.homeTeamName
        tvAwayNextMatch.text = favorite?.get(0)?.awayTeamName
        tvDateMatch.text = dateEvent
        tvTimeMatch.text = time.toString()
        tvLeagueName.text = favorite?.get(0)?.strLeague
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadDataFromApi(items: DataNextPerItem?) {
        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val dates = inFormat.parse(items?.dateEvent)
        val outFormat = SimpleDateFormat("EEEE, dd MMM yyyy")
        val dateEvent = outFormat.format(dates)

        val time = items?.strTime?.substring(0, 5)

        tvHomeNextMatch.text = items?.strHomeTeam
        tvAwayNextMatch.text = items?.strAwayTeam
        tvDateMatch.text = dateEvent
        tvTimeMatch.text = time.toString()
        tvLeagueName.text = items?.strLeague
    }

    override fun showLoading() {
        loading.visible()
    }

    override fun hideLoading() {
        loading.gone()
    }

    override fun showHomeBadge(data: List<DataTeams.ItemTeams>?) {
        if (data!= null){
            Glide.with(this)
                    .load(data[0].strTeamBadge)
                    .into(imgHomeNext)
        }

    }

    override fun showAwayBadge(data: List<DataTeams.ItemTeams>?) {
        if (data!=null){
            Glide.with(this)
                    .load(data[0].strTeamBadge)
                    .into(imgAwayNext)
        }
    }

}
