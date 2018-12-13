package com.asykurkhamid.kickball.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import com.asykurkhamid.kickball.baseClass.BaseActivity
import com.asykurkhamid.kickball.R
import com.asykurkhamid.kickball.fragment.*
import com.asykurkhamid.kickball.utils.SearchListener
import com.asykurkhamid.kickball.utils.Variables
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import java.util.ArrayList


class MainActivity : BaseActivity() {
    private lateinit var searchView: MaterialSearchView
    private var searchIface : List<SearchListener>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setDefaultToolbar(false,getString(R.string.app_name))
        searchView = materialSearchView
        searchView.setVoiceSearch(false)
        searchView.setCursorDrawable(R.drawable.custom_cursor)
        searchView.setEllipsize(true)
        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
//                Snackbar.make(findViewById(R.id.container), "Query: $query", Snackbar.LENGTH_LONG).show()
                if (content!= null){

                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() {
                //Do some magic
            }

            override fun onSearchViewClosed() {
                //Do some magic
            }
        })

        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (savedInstanceState == null){
            val fragment = MatchesFragment.newInstance()
            addFragment(fragment)
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment:Fragment?
        val currentFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.content)

        when (item.itemId) {
            R.id.navigation_last -> {
                if (currentFragment !is LastMatchFragment) {
                    fragment = MatchesFragment.newInstance()
                    addFragment(fragment)
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next -> {
                if (currentFragment !is NextMatchFragment){
                    fragment = TeamsFragment.newInstance()
                    addFragment(fragment)
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                if (currentFragment !is FavoriteFragment){
                    fragment = FavoriteFragment.newInstance()
                    addFragment(fragment)
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    @SuppressLint("PrivateResource")
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            alert(getString(R.string.leaving_app)) {
                yesButton {
                    finish()
                }
                noButton { }
            }.show()
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search,menu)

        val item: MenuItem? = menu?.findItem(R.id.action_search)
        searchView.setMenuItem(item)
        return true
    }

    override fun onBackPressed() {
        if (searchView.isSearchOpen){
            searchView.closeSearch()
        }else{
            super.onBackPressed()
        }

    }
}
