package com.coruptiaucide.vavedem.ui.activities

import android.content.Intent
import android.os.Bundle
import com.coruptiaucide.vavedem.R
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        btnLogin.setOnClickListener {
            startActivity(Intent(this@FirstActivity, LoginActivity::class.java))
        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this@FirstActivity, RegisterActivity::class.java))
        }
    }
}