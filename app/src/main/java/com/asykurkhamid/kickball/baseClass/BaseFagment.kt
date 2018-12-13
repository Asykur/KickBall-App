package com.asykurkhamid.kickball.baseClass

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast


open class BaseFagment : Fragment(){

    protected var strLaLiga : String? = "Spanish La Liga"


    protected fun View.show() {
        this.visibility = View.VISIBLE
    }

    protected fun View.hide() {
        this.visibility = View.GONE
    }

    protected fun toast(message: CharSequence) = Toast.makeText(context, message, Toast.LENGTH_LONG).show()

    protected fun displayError(view: View?, err: String?) {
        view.let {
            if (it != null) {
                Snackbar.make(it, err.toString(), Snackbar.LENGTH_LONG).show()
            }
        }
    }

}