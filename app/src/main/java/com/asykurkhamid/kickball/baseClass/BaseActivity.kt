package com.asykurkhamid.kickball.baseClass

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.asykurkhamid.kickball.database.database
import com.asykurkhamid.kickball.pojo.*
import com.asykurkhamid.kickball.retrofit.ServiceFactory
import com.asykurkhamid.kickball.utils.Variables
import com.bumptech.glide.Glide
import com.sackcentury.shinebuttonlib.ShineButton
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.db.insert
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.animation.AnimatorListenerAdapter
import android.view.ViewAnimationUtils
import android.os.Build
import android.support.annotation.RequiresApi
import com.asykurkhamid.kickball.R


@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    private lateinit var id: String
    private var datas: List<DataTeams.ItemTeams>? = null


    protected fun toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    protected  fun setDefaultToolbar(back: Boolean, title: String){
        assignDataToolbars(back,title,null,null,null,null,null,false)
    }
    protected fun setDefaultToolbar(back: Boolean, title: String?, itemNext: DataNextPerItem?, category: String?, isFavorite : Boolean){
        assignDataToolbars(back,title,null,itemNext,null,null,category,isFavorite)
    }
    protected fun setDefaultToolbar(back: Boolean, title: String?, itemLast: DataLastPerItem?, category: String?, isFavorite : Boolean){
        assignDataToolbars(back,title,itemLast,null,null,null,category,isFavorite)
    }
    protected fun setDefaultToolbar(back: Boolean, title: String?, itemFav: DataFavorite?,category: String?, isFavorite : Boolean){
        assignDataToolbars(back,title,null,null,itemFav,null,category,isFavorite)
    }
    protected fun setDefaultToolbar(back: Boolean, title: String?, itemFavNext: DataFavoriteNext?,category: String?, isFavorite : Boolean){
        assignDataToolbars(back,title,null,null,null,itemFavNext,category,isFavorite)
    }

    private fun assignDataToolbars(back: Boolean, title: String?, itemLast: DataLastPerItem?, itemNext: DataNextPerItem?, itemFavLast: DataFavorite?, itemFavNext: DataFavoriteNext?, category: String?, isFavorite : Boolean) {
        val toolbar: Toolbar? = tbLast
        setSupportActionBar(toolbar)
        val tvTitle: TextView? = tvTbTitile
        val btnFav: ShineButton? = btnFavorite
        val btnBack: ImageView? = btnBack
        if (!back){
            btnFav?.visibility = View.GONE
        }

        btnFav?.isChecked = isFavorite

        btnFav?.setOnCheckStateChangeListener { view, checked ->
            if (checked){
                if (itemLast != null){
                    addFavoriteLast(itemLast,category)
                }else if (itemNext != null){
                    addFavoriteNext(itemNext,category)
                }
            }else{
                if (itemLast != null){
                    removeFromFavoriteLast(itemLast.idEvent)
                }else if (itemNext != null){
                    removeFromFavoriteNext(itemNext.idEvent)
                }else if (itemFavLast != null && category.equals(Variables.LAST)){
                    removeFromFavoriteLast(itemFavLast.eventID)
                }else if (itemFavNext != null && category.equals(Variables.NEXT)){
                    removeFromFavoriteNext(itemFavNext?.eventID)
                }
            }
        }

        if (TextUtils.isEmpty(title)) {
            tvTitle?.text = ""
        } else {
            tvTitle?.text = title
        }

        if (back) {
            btnBack?.visibility = View.VISIBLE
            btnBack?.setOnClickListener { finish() }
        }
    }

    private fun addFavoriteLast(item: DataLastPerItem?, category: String?){
        try {
            database.use{
                insert(DataFavorite.TABLE_FAVORITE_LAST,
                        DataFavorite.EVENT_ID to item?.idEvent,
                        DataFavorite.HOME_ID to item?.idHomeTeam,
                        DataFavorite.AWAY_ID to item?.idAwayTeam,
                        DataFavorite.HOME_TEAM_NAME to item?.strHomeTeam,
                        DataFavorite.AWAY_TEAM_NAME to item?.strAwayTeam,
                        DataFavorite.HOME_SCORE to item?.intHomeScore,
                        DataFavorite.AWAY_SCORE to item?.intAwayScore,
                        DataFavorite.DATE_EVENT to item?.dateEvent,
                        DataFavorite.TIME_EVENT to item?.strTime,
                        DataFavorite.HOME_SCORER to item?.strHomeGoalDetails,
                        DataFavorite.AWAY_SCORER to item?.strAwayGoalDetails,
                        DataFavorite.HOME_GOAL_KEEPER to item?.strHomeLineupGoalkeeper,
                        DataFavorite.AWAY_GOAL_KEEPER to item?.strAwayLineupGoalkeeper,
                        DataFavorite.HOME_DEFENDER to item?.strHomeLineupDefense,
                        DataFavorite.AWAY_DEFENDER to item?.strAwayLineupDefense,
                        DataFavorite.HOME_MID_FIELDER to item?.strHomeLineupMidfield,
                        DataFavorite.AWAY_MID_FIELDER to item?.strAwayLineupMidfield,
                        DataFavorite.HOME_FORWARDER to item?.strHomeLineupForward,
                        DataFavorite.AWAY_FORWARDER to item?.strAwayLineupForward,
                        DataFavorite.HOME_YELLOW_CARDS to item?.strHomeYellowCards,
                        DataFavorite.AWAY_YELLOW_CARDS to item?.strAwayYellowCards,
                        DataFavorite.HOME_RED_CARDS to item?.strHomeRedCards,
                        DataFavorite.AWAY_RED_CARDS to item?.strAwayRedCards,
                        DataFavorite.CATEGORY to category
                )
            }
            Toast.makeText(this,"Added to Favorite",Toast.LENGTH_SHORT).show()

        }catch (e : SQLiteConstraintException){
            e.stackTrace
        }
    }

    private fun addFavoriteNext(itemNext: DataNextPerItem?,category: String?){
        try {
            database.use {
                insert(DataFavorite.TABLE_FAVORITE_NEXT,
                        DataFavorite.EVENT_ID to itemNext?.idEvent,
                        DataFavorite.HOME_ID to itemNext?.idHomeTeam,
                        DataFavorite.AWAY_ID to itemNext?.idAwayTeam,
                        DataFavorite.HOME_TEAM_NAME to itemNext?.strHomeTeam,
                        DataFavorite.AWAY_TEAM_NAME to itemNext?.strAwayTeam,
                        DataFavorite.HOME_SCORE to itemNext?.intHomeScore,
                        DataFavorite.AWAY_SCORE to itemNext?.intAwayScore,
                        DataFavorite.DATE_EVENT to itemNext?.dateEvent,
                        DataFavorite.TIME_EVENT to itemNext?.strTime,
                        DataFavorite.LEAGUE to itemNext?.strLeague,
                        DataFavorite.CATEGORY to category
                        )
            }
            Toast.makeText(this,"Added to Favorite",Toast.LENGTH_SHORT).show()

        }catch (e : SQLiteConstraintException){
            e.stackTrace
        }
    }

    private fun removeFromFavoriteLast(idEvent: String?){
        id = idEvent.toString()
        try {
            database.use {
                delete(DataFavorite.TABLE_FAVORITE_LAST, "${DataFavorite.EVENT_ID}=$idEvent",
                        null)
            }
            Toast.makeText(this,"Removed from Favorite",Toast.LENGTH_SHORT).show()

        } catch (e: SQLiteConstraintException){
            e.stackTrace
        }
    }

    private fun removeFromFavoriteNext(idEvent: String?){
        id = idEvent.toString()
        try {
            database.use {
                delete(DataFavorite.TABLE_FAVORITE_NEXT, "${DataFavorite.EVENT_ID}=$idEvent",
                        null)
            }
            Toast.makeText(this,"Removed from Favorite",Toast.LENGTH_SHORT).show()

        } catch (e: SQLiteConstraintException){
            e.stackTrace
        }
    }

//    @SuppressLint("PrivateResource")
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    fun circleReveal(viewID: Int, posFromRight: Int, containsOverflow: Boolean, isShow: Boolean) {
//        val myView = findViewById<View>(viewID)
//
//        var width = myView.width
//
//        if (posFromRight > 0)
//            width -= posFromRight * resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) - resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) / 2
//        if (containsOverflow)
//            width -= resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material)
//
//        val cx = width
//        val cy = myView.height / 2
//
//        val anim: Animator
//        if (isShow)
//            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0f, width.toFloat())
//        else
//            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, width.toFloat(), 0f)
//
//        anim.duration = 220.toLong()
//
//        // make the view invisible when the animation is done
//        anim.addListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationEnd(animation: Animator) {
//                if (!isShow) {
//                    super.onAnimationEnd(animation)
//                    myView.visibility = View.INVISIBLE
//                }
//            }
//        })
//
//        // make the view visible and start the animation
//        if (isShow)
//            myView.visibility = View.VISIBLE
//
//        // start the animation
//        anim.start()
//
//
//    }

}