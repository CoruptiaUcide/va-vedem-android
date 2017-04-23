package com.coruptiaucide.vavedem.ui.activities

import android.os.Bundle
import com.coruptiaucide.vavedem.R
import kotlinx.android.synthetic.main.activity_forgot.*

/**
 * Created by Tiberiu on 6/23/2016.
 */

class ForgotPasswordActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        title = ""
        tv_title.text = resources.getString(R.string.button_reset_password)
        setSupportActionBar(toolbar)
        displayCloseButton()
        toolbar.setNavigationOnClickListener {
            finish()
        }
        emailPassword.setOnFocusChangeListener { view, b ->
            password_email_wrapper.error = null
        }
        btnReset.setOnClickListener {
            if (!emailPassword.isValid()) {
                password_email_wrapper.error = getString(R.string.error_email)
            } else {
                password_email_wrapper.error = null
                resetPassword()
            }
        }
    }

    fun resetPassword() {

    }
}