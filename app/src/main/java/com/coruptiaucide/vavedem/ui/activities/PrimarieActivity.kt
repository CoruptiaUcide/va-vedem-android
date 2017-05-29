package com.coruptiaucide.vavedem.ui.activities

import android.os.Bundle
import com.coruptiaucide.vavedem.R
import com.coruptiaucide.vavedem.api.model.Primarie
import com.coruptiaucide.vavedem.ui.fragments.EMPTY_STRING
import kotlinx.android.synthetic.main.activity_primarie.*

/**
 * Created by tiberiugolaes on 28/05/2017.
 */

const val PRIMARIE_EXTRA = "primarie"

class PrimarieActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primarie)
        setSupportActionBar(primarie_toolbar)
        val primarie = intent.getParcelableExtra<Primarie>(PRIMARIE_EXTRA)
        primarieTitle.text = primarie.nume
        primarieAdresa.text = primarie.adresa.strada.plus(if (primarie.adresa.nr.equals(EMPTY_STRING)) "," else " nr. ${primarie.adresa.nr},")
                .plus(if (primarie.adresa.codPostal.equals(EMPTY_STRING)) " ," else " ${primarie.adresa.codPostal},")
                .plus(primarie.adresa.localitatea)

        back_layout.setOnClickListener { finish() }
    }
}
