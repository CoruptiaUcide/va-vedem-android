package com.coruptiaucide.vavedem.ui.activities

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.view.GravityCompat
import android.view.Menu
import android.view.MenuItem
import com.coruptiaucide.vavedem.api.PrefManager
import com.coruptiaucide.vavedem.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(getTintedDrawable(R.drawable.menu, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowTitleEnabled(false);


        navigation_view.setNavigationItemSelectedListener { menuItem ->
            drawer_layout.closeDrawers();
            when (menuItem.itemId) {
                R.id.action_logout -> {
                    PrefManager.clearSession()
                    startActivity(Intent(this@HomeActivity, FirstActivity::class.java))
                    this.finish()
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }

    fun getTintedDrawable(@DrawableRes drawableResId: Int, @ColorRes colorResId: Int): Drawable? {
        if (drawableResId == 0) return null
        val drawable = ContextCompat.getDrawable(this, drawableResId).mutate()
        val color = ContextCompat.getColor(this, colorResId)
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        return drawable;
    }
}