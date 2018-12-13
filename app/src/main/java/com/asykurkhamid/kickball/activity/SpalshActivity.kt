package com.asykurkhamid.kickball.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.asykurkhamid.kickball.R

class SpalshActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)

            Handler().postDelayed({
                startActivity(Intent(this,MainActivity::class.java))
                finishAffinity()
            },2000)
        }

}
