package com.coruptiaucide.vavedem.ui.activities

import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.coruptiaucide.vavedem.R

/**
 * Created by tiberiugolaes on 26/05/16.
 */
abstract class BaseActivity : AppCompatActivity(){
    fun displayCloseButton() {
        val back = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material)
        back.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}