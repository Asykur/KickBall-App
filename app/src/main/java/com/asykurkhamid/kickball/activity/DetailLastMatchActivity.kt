package com.asykurkhamid.kickball.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.ScrollView
import com.asykurkhamid.kickball.baseClass.BaseActivity
import com.asykurkhamid.kickball.database.database
import com.asykurkhamid.kickball.pojo.DataFavorite
import com.asykurkhamid.kickball.pojo.DataLastPerItem
import com.asykurkhamid.kickball.R
import com.asykurkhamid.kickball.pojo.DataTeams
import com.asykurkhamid.kickball.presenter.DetailMatchPresenter
import com.asykurkhamid.kickball.utils.DetailMatchView
import com.asykurkhamid.kickball.utils.gone
import com.asykurkhamid.kickball.utils.visible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_deatail_last_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import java.text.SimpleDateFormat


class DetailLastMatchActivity : BaseActivity(), DetailMatchView {


    private var item: DataLastPerItem? = null
    private lateinit var progressDetail: ProgressBar
    private var scrollDetail: ScrollView? = null
    private var category = "Last Match"
    private var isFavorite: Boolean = false
    private var favorite: DataFavorite? = null
    private var list: List<DataFavorite>? = null
    private lateinit var presenter: DetailMatchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deatail_last_match)
        item = intent.getParcelableExtra("allData")
        favorite = intent.getParcelableExtra("favoriteLast")
        progressDetail = pgDetail
        scrollDetail = scDetail
        presenter = DetailMatchPresenter(this)

        if (item != null) {
            favoriteState(item?.idEvent)
            setDefaultToolbar(true, getString(R.string.match_statistics), item, category, isFavorite)
            loadText(item, null)

            presenter.getDetailHomeTeam(item?.idHomeTeam)
            presenter.getDetailAwayTeam(item?.idAwayTeam)

        } else if (favorite != null) {
            favoriteState(favorite?.eventID)
            setDefaultToolbar(true, getString(R.string.match_statistics), favorite, category, isFavorite)
            loadText(null, list)

            presenter.getDetailHomeTeam(favorite?.homeID)
            presenter.getDetailAwayTeam(favorite?.awayID)

        }
    }

    private fun favoriteState(eventID: String?) {
        database.use {
            val result = select(DataFavorite.TABLE_FAVORITE_LAST).whereArgs("${DataFavorite.EVENT_ID}=$eventID")
            list = result.parseList(classParser<DataFavorite>())
            if (list?.size != 0) {
                isFavorite = true
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadText(item: DataLastPerItem?, list: List<DataFavorite>?) {

        if (item != null) {
            loadFromAPI()
        } else if (list?.size != null) {
            loadFromDBFavorite()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadFromDBFavorite() {
        val scorerHome: String? = list?.get(0)?.strHomeGoalDetails?.replace(";", "\n")
        val scorerAway: String? = list?.get(0)?.strAwayGoalDetails?.replace(";", "\n")
        val yellowHome: String? = list?.get(0)?.strHomeYellowCards?.replace(";", "\n")
        val yellowAway: String? = list?.get(0)?.strAwayYellowCards?.replace(";", "\n")
        val redHome: String? = list?.get(0)?.strHomeRedCards?.replace(";", "\n")
        val redAway: String? = list?.get(0)?.strAwayRedCards?.replace(";", "\n")
        val homeDef: String? = list?.get(0)?.strHomeLineupDefense?.replace(";", "\n")
        val awayDef: String? = list?.get(0)?.strAwayLineupDefense?.replace(";", "\n")
        val homeMid: String? = list?.get(0)?.strHomeLineupMidfield?.replace(";", "\n")
        val awayMid: String? = list?.get(0)?.strAwayLineupMidfield?.replace(";", "\n")
        val homeForward: String? = list?.get(0)?.strHomeLineupForward?.replace(";", "\n")
        val awayForward: String? = list?.get(0)?.strAwayLineupForward?.replace(";", "\n")

        tvHomeTeamDetail.text = list?.get(0)?.homeTeamName
        tvAwayTeamDetail.text = list?.get(0)?.awayTeamName
        tvHomeScoreDetail.text = list?.get(0)?.homeScore
        tvAwayScoreDetail.text = list?.get(0)?.awayScore
        tvHomeGK.text = list?.get(0)?.strHomeLineupGoalkeeper
        tvAwayGK.text = list?.get(0)?.strAwayLineupGoalkeeper
        tvHomeDef.text = homeDef
        tvAwayDef.text = awayDef
        tvHomeMid.text = homeMid
        tvAwayMid.text = awayMid
        tvHomeForward.text = homeForward
        tvAwayForward.text = awayForward

        if (TextUtils.isEmpty(scorerHome) && TextUtils.isEmpty(scorerAway)) {
            cardGoals.visibility = View.GONE
        }
        tvHomeScorer.text = scorerHome
        tvAwayScorer.text = scorerAway

        if (TextUtils.isEmpty(yellowHome) && TextUtils.isEmpty(yellowAway)) {
            cardYellow.visibility = View.GONE
        }
        tvHomeYellow.text = yellowHome
        tvAwayYellow.text = yellowAway

        if (TextUtils.isEmpty(redHome) && TextUtils.isEmpty(redAway)) {
            cardRed.visibility = View.GONE
        }
        tvHomeRed.text = redHome
        tvAwayRed.text = redAway

        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val dates = inFormat.parse(list?.get(0)?.dateEvent)
        val outFormat = SimpleDateFormat("EEEE, dd MMM yyyy")
        val dateEvent = outFormat.format(dates)
        tvDateEventDetail.text = dateEvent
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadFromAPI() {
        val scorerHome: String? = item?.strHomeGoalDetails?.replace(";", "\n")
        val scorerAway: String? = item?.strAwayGoalDetails?.replace(";", "\n")
        val yellowHome: String? = item?.strHomeYellowCards?.replace(";", "\n")
        val yellowAway: String? = item?.strAwayYellowCards?.replace(";", "\n")
        val redHome: String? = item?.strHomeRedCards?.replace(";", "\n")
        val redAway: String? = item?.strAwayRedCards?.replace(";", "\n")
        val homeDef: String? = item?.strHomeLineupDefense?.replace(";", "\n")
        val awayDef: String? = item?.strAwayLineupDefense?.replace(";", "\n")
        val homeMid: String? = item?.strHomeLineupMidfield?.replace(";", "\n")
        val awayMid: String? = item?.strAwayLineupMidfield?.replace(";", "\n")
        val homeForward: String? = item?.strHomeLineupForward?.replace(";", "\n")
        val awayForward: String? = item?.strAwayLineupForward?.replace(";", "\n")

        tvHomeTeamDetail.text = item?.strHomeTeam
        tvAwayTeamDetail.text = item?.strAwayTeam
        tvHomeScoreDetail.text = item?.intHomeScore
        tvAwayScoreDetail.text = item?.intAwayScore
        tvHomeGK.text = item?.strHomeLineupGoalkeeper
        tvAwayGK.text = item?.strAwayLineupGoalkeeper
        tvHomeDef.text = homeDef
        tvAwayDef.text = awayDef
        tvHomeMid.text = homeMid
        tvAwayMid.text = awayMid
        tvHomeForward.text = homeForward
        tvAwayForward.text = awayForward

        if (TextUtils.isEmpty(scorerHome) && TextUtils.isEmpty(scorerAway)) {
            cardGoals.visibility = View.GONE
        }
        tvHomeScorer.text = scorerHome
        tvAwayScorer.text = scorerAway

        if (TextUtils.isEmpty(yellowHome) && TextUtils.isEmpty(yellowAway)) {
            cardYellow.visibility = View.GONE
        }
        tvHomeYellow.text = yellowHome
        tvAwayYellow.text = yellowAway

        if (TextUtils.isEmpty(redHome) && TextUtils.isEmpty(redAway)) {
            cardRed.visibility = View.GONE
        }
        tvHomeRed.text = redHome
        tvAwayRed.text = redAway

        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val dates = inFormat.parse(item?.dateEvent)
        val outFormat = SimpleDateFormat("EEEE, dd MMM yyyy")
        val dateEvent = outFormat.format(dates)
        tvDateEventDetail.text = dateEvent
    }

    override fun showLoading() {
        progressDetail.visible()
    }

    override fun hideLoading() {
        progressDetail.gone()
    }

    override fun showHomeBadge(data: List<DataTeams.ItemTeams>?) {
        if (data != null) {
            Glide.with(this)
                    .load(data[0].strTeamBadge)
                    .into(imgHome)
        }
    }

    override fun showAwayBadge(data: List<DataTeams.ItemTeams>?) {
        if (data != null) {
            Glide.with(this)
                    .load(data[0].strTeamBadge)
                    .into(imgAway)
        }
    }


}
