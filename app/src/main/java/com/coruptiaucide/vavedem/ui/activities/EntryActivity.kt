package com.coruptiaucide.vavedem.ui.activities

import android.content.Intent
import android.os.Bundle
import com.coruptiaucide.vavedem.VaVedemApplication


class EntryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = null
        val intent: Intent
        if (!VaVedemApplication.isLoggedIn()) {
            intent = Intent(this, FirstActivity::class.java)
        } else {
            intent = Intent(this, HomeActivity::class.java)
        }
        startActivity(intent)
        finish()
    }

}