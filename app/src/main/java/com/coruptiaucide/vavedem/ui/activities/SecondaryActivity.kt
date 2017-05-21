package com.coruptiaucide.vavedem.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.coruptiaucide.vavedem.R
import com.coruptiaucide.vavedem.ui.fragments.*
import kotlinx.android.synthetic.main.activity_toolbar.*
import java.io.Serializable

/**
 * Created by tiberiugolaes on 02/05/2017.
 */

private const val EXTRA_TYPE = "SecondaryActivity.EXTRA_TYPE"
private const val EXTRA_ACTIVITY_OPTIONS = "SecondaryActivity.ExtraOptions"

private enum class ModalType : Serializable {
    PRIMARII,
    DESPRE_NOI,
    STATISTICA,
    DEPUNE_CERERE,
    INFO_CERERE,
    CERERE
}

class SecondaryActivity : BaseActivity() {

    companion object {

        private fun createIntent(context: Context, modalType: ModalType): Intent {
            return createIntentWithTransition(context, modalType, ActivityOptionsCompat.makeCustomAnimation(context, R.anim.anim_slide_sus, R.anim.wait))

        }

        private fun createIntentWithTransition(context: Context, modalType: ModalType, transition: ActivityOptionsCompat): Intent {
            val intent = Intent(context, SecondaryActivity::class.java)
            intent.putExtra(EXTRA_ACTIVITY_OPTIONS, transition.toBundle())
            intent.putExtra(EXTRA_TYPE, modalType)
            return intent
        }

        fun createViewPrimarie(context: Context) = createIntent(context, ModalType.PRIMARII)
        fun createViewDespreNoi(context: Context) = createIntent(context, ModalType.DESPRE_NOI)
        fun createViewDepuneCerere(context: Context) = createIntent(context, ModalType.DEPUNE_CERERE)
        fun createViewInfoCerere(context: Context) = createIntent(context, ModalType.INFO_CERERE)
        fun createViewStatistica(context: Context) = createIntent(context, ModalType.STATISTICA)
        fun createViewCerere(context: Context) = createIntent(context, ModalType.CERERE)
    }

    private var mType: ModalType? = null
    lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mType = intent.getSerializableExtra(EXTRA_TYPE) as? ModalType

        setContentView(R.layout.activity_toolbar)
        displayCloseButton()
        title = ""
        val fragment: Fragment
        when (mType) {
            ModalType.PRIMARII -> {
                tv_title.text = getString(R.string.menu_primarii)
                fragment = PrimariiFragment()
            }
            ModalType.DESPRE_NOI -> {
                tv_title.text = getString(R.string.menu_noi)
                fragment = DespreNoiFragment()
            }
            ModalType.STATISTICA -> {
                tv_title.text = getString(R.string.menu_statistica)
                fragment = StatisticaFragment()
            }
            ModalType.DEPUNE_CERERE -> {
                tv_title.text = getString(R.string.menu_depunere)
                fragment = DepuneCerereFragment()
            }
            ModalType.INFO_CERERE -> {
                tv_title.text= getString(R.string.menu_info)
                fragment = InfoCerereFragment()
            }
            ModalType.CERERE -> {
                tv_title.text= getString(R.string.fragment_cerere_titlu)
                fragment = CerereFragment()
            }
            else -> {
                Toast.makeText(this, R.string.view_eroare, Toast.LENGTH_SHORT).show()
                finish()
                return
            }
        }
        currentFragment = fragment
        supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment).commit()
    }

    override fun displayCloseButton() {
        setSupportActionBar(toolbar)
        val back = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material)
        back.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.anim_slide_jos)
    }
}