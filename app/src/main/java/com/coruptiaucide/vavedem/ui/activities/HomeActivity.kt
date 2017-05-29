package com.coruptiaucide.vavedem.ui.activities

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.view.GravityCompat
import android.view.*
import com.coruptiaucide.vavedem.api.PrefManager
import com.coruptiaucide.vavedem.R
import com.coruptiaucide.vavedem.api.model.Cerere
import com.coruptiaucide.vavedem.utils.EmptyStateRecyclerView
import kotlinx.android.synthetic.main.activity_home.*
import com.coruptiaucide.vavedem.utils.DateExtensions.toMediumDateString
import android.graphics.drawable.LayerDrawable
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import com.coruptiaucide.vavedem.utils.SimpleDividerItemDecoration
import kotlinx.android.synthetic.main.view_item_cerere.view.*



class HomeActivity : BaseActivity() {

    internal var mAdapter: EmptyStateRecyclerView.SimpleAdapter<Cerere, CerereViewHolder>? = null

    lateinit var mRecyclerView: EmptyStateRecyclerView

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
                R.id.action_depune_cerere -> {
                    startActivity(SecondaryActivity.createViewDepuneCerere(this@HomeActivity))
                }
                R.id.action_info_cerere -> {
                    startActivity(SecondaryActivity.createViewInfoCerere(this@HomeActivity))
                }
                R.id.action_noi -> {
                    startActivity(SecondaryActivity.createViewDespreNoi(this@HomeActivity))
                }
                R.id.action_primarii -> {
                    startActivity(SecondaryActivity.createViewPrimarie(this@HomeActivity))
                }
                R.id.action_contact -> {
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto:tgol@itu.dk") // only email apps should handle this
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    }
                }
            }
            true
        }
        mRecyclerView = findViewById(R.id.rv_cereri) as EmptyStateRecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
        mAdapter = createAdapter()
        mRecyclerView.emptyView = findViewById(android.R.id.empty)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(SimpleDividerItemDecoration(ContextCompat.getDrawable(this@HomeActivity, R.drawable.simple_divider)))
        (cerereImg.background as LayerDrawable).findDrawableByLayerId(R.id.image)
                .setColorFilter(ContextCompat.getColor(this@HomeActivity, R.color.colorAccent), PorterDuff.Mode.SRC_IN)
        mAdapter!!.setData(emptyList())
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
            R.id.action_statistics -> {
                startActivity(SecondaryActivity.createViewStatistica(this@HomeActivity))
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_full_home, menu)
        return true
    }

    fun getTintedDrawable(@DrawableRes drawableResId: Int, @ColorRes colorResId: Int): Drawable? {
        if (drawableResId == 0) return null
        val drawable = ContextCompat.getDrawable(this, drawableResId).mutate()
        val color = ContextCompat.getColor(this, colorResId)
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    internal fun createAdapter(): EmptyStateRecyclerView.SimpleAdapter<Cerere, CerereViewHolder> {
        return object : EmptyStateRecyclerView.SimpleAdapter<Cerere, CerereViewHolder>() {
            override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CerereViewHolder {
                return CerereViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.view_item_cerere, viewGroup, false), this@HomeActivity)
            }
        }
    }

    internal class CerereViewHolder(itemView: View, activity: HomeActivity) : EmptyStateRecyclerView.SimpleViewHolder<Cerere>(itemView, activity) {
        override fun bind(item: Cerere) {
            itemView.apply {
                tvNume.text = item.primarie.nume
                tvTip.text = item.tip
                tvData.text = item.data.toMediumDateString()
                ivCerere.setOnClickListener { context.startActivity(SecondaryActivity.createViewCerere(context)) }
            }
        }
    }
}