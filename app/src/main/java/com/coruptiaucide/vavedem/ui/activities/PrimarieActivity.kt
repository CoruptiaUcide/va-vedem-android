package com.coruptiaucide.vavedem.ui.activities

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.coruptiaucide.vavedem.R
import com.coruptiaucide.vavedem.api.model.Adresa
import com.coruptiaucide.vavedem.api.model.Cerere
import com.coruptiaucide.vavedem.api.model.Primarie
import com.coruptiaucide.vavedem.ui.fragments.EMPTY_STRING
import com.coruptiaucide.vavedem.utils.EmptyStateRecyclerView
import com.coruptiaucide.vavedem.utils.SimpleDividerItemDecoration
import kotlinx.android.synthetic.main.activity_primarie.*

/**
 * Created by tiberiugolaes on 28/05/2017.
 */

const val PRIMARIE_EXTRA = "primarie"

class PrimarieActivity : BaseActivity() {

    internal var mAdapter: EmptyStateRecyclerView.SimpleAdapter<Cerere, HomeActivity.CerereViewHolder>? = null

    lateinit var mRecyclerView: EmptyStateRecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primarie)
        setSupportActionBar(primarie_toolbar)
        val primarie = intent.getParcelableExtra<Primarie>(PRIMARIE_EXTRA)
        primarieTitle.text = primarie.nume
        primariePopulatie.text = getString(R.string.primarii_populatie, primarie.populatie.toString())
        tvTelefon.text = primarie.telefon
        tvEmail.text = primarie.email
        tvAdresa.text = primarie.adresa.strada.plus(if (primarie.adresa.nr.equals(EMPTY_STRING)) "," else " nr. ${primarie.adresa.nr},")
                .plus(if (primarie.adresa.codPostal.equals(EMPTY_STRING)) " ," else " ${primarie.adresa.codPostal},")
                .plus(primarie.adresa.localitatea)
        (cerereImg.background as LayerDrawable).findDrawableByLayerId(R.id.image)
                .setColorFilter(ContextCompat.getColor(this@PrimarieActivity, R.color.colorAccent), PorterDuff.Mode.SRC_IN)
        back_layout.setOnClickListener { finish() }
        email.setOnClickListener { sendEmail(primarie.email) }
        telefon.setOnClickListener { callPhoneNumber(primarie.telefon) }
        adresa.setOnClickListener { openGoogleMaps(primarie.adresa) }
        cerere.setOnClickListener { startActivity(SecondaryActivity.createViewDepuneCerere(this@PrimarieActivity)) }
        mRecyclerView = findViewById(R.id.rv_cereri) as EmptyStateRecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(this@PrimarieActivity, LinearLayoutManager.VERTICAL, false)
        mAdapter = createAdapter()
        mRecyclerView.emptyView = findViewById(android.R.id.empty)
        mRecyclerView.adapter = mAdapter
        cerereAdd.setOnClickListener { startActivity(SecondaryActivity.createViewDepuneCerere(this@PrimarieActivity)) }
        mRecyclerView.addItemDecoration(SimpleDividerItemDecoration(ContextCompat.getDrawable(this@PrimarieActivity, R.drawable.simple_divider)))
        (cerereImg.background as LayerDrawable).findDrawableByLayerId(R.id.image)
                .setColorFilter(ContextCompat.getColor(this@PrimarieActivity, R.color.colorAccent), PorterDuff.Mode.SRC_IN)
        mAdapter!!.setData(emptyList())
    }

    fun sendEmail(recipient: String) {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "message/rfc822"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        try {
            startActivity(Intent.createChooser(i, resources.getString(R.string.trimite_email)))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this@PrimarieActivity, R.string.eroare_email, Toast.LENGTH_LONG).show()
        }
    }

    fun callPhoneNumber(number: String) {
        val callIntent = Intent(Intent.ACTION_VIEW)
        callIntent.data = Uri.parse("tel:$number")
        startActivity(callIntent)
    }


    internal fun createAdapter(): EmptyStateRecyclerView.SimpleAdapter<Cerere, HomeActivity.CerereViewHolder> {
        return object : EmptyStateRecyclerView.SimpleAdapter<Cerere, HomeActivity.CerereViewHolder>() {
            override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HomeActivity.CerereViewHolder {
                return HomeActivity.CerereViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.view_item_cerere, viewGroup, false), this@PrimarieActivity)
            }
        }
    }

    internal fun openGoogleMaps(adresa: Adresa) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://maps.google.co.in/maps?q=" + adresa.strada + " " + adresa.nr + " " + adresa.localitatea)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
