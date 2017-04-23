package com.coruptiaucide.vavedem.ui.activities

import android.app.ActionBar
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.util.Log
import com.coruptiaucide.vavedem.VaVedemApplication
import com.coruptiaucide.vavedem.api.model.data.DataSession
import com.coruptiaucide.vavedem.api.requester.SessionRequester
import com.coruptiaucide.vavedem.utils.InertObserver
import com.coruptiaucide.vavedem.R
import kotlinx.android.synthetic.main.activity_register.*
import rx.schedulers.Schedulers

/**
 * Created by Tiberiu on 6/23/2016.
 */

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        title = ""
        tv_title.text = resources.getString(R.string.register_text)
        setSupportActionBar(toolbar)
        displayCloseButton()
        toolbar.setNavigationOnClickListener {
            finish()
        }
        btnRegisterConfirm.setOnClickListener {
            if (emailRegister.isValid() && passwordRegister.isValid() && passwordRegister.text.toString().equals(passwordRegister2.text.toString())) {
                register()
            } else {
                if (!emailRegister.isValid()) {
                    register_email_wrapper.error = getString(R.string.error_email)
                } else {
                    register_email_wrapper.error = null
                }
                if (!passwordRegister.text.equals(passwordRegister2.text)) {
                    register_password_wrapper.error = getString(R.string.password_missmatch)
                    register_password2_wrapper.error = getString(R.string.password_missmatch)
                } else {
                    register_password_wrapper.error = null
                    register_password2_wrapper.error = null
                }
                if (!passwordRegister.isValid()) {
                    register_password_wrapper.error = getString(R.string.error_password)
                } else {
                    register_password_wrapper.error = null
                }
                if (!passwordRegister2.isValid()) {
                    register_password2_wrapper.error = getString(R.string.error_password)
                } else {
                    register_password2_wrapper.error = null
                }
            }
        }
        emailRegister.setOnFocusChangeListener { view, motionEvent ->
            register_email_wrapper.error = null
        }
        passwordRegister.setOnFocusChangeListener { view, motionEvent ->
            register_password_wrapper.error = null
        }
        passwordRegister2.setOnFocusChangeListener { view, motionEvent ->
            register_password2_wrapper.error = null
        }
    }

    fun register() {
      /*  VaVedemApplication.Companion.showProgressBar(this@RegisterActivity)
        SessionRequester.register(emailRegister.text.toString(), passwordRegister.text.toString())
                .subscribeOn(Schedulers.io()).subscribe(object : InertObserver<DataSession>() {

            override fun onError(e: Throwable?) {
                VaVedemApplication.closeProgressBar()
                Snackbar.make(btnRegisterConfirm, "${e?.message}", Snackbar.LENGTH_LONG)
            }

            override fun onNext(session: DataSession){
                Log.d("Results", "${session.sessionId},${session.userId}")
                VaVedemApplication.closeProgressBar()*/
                startActivity(Intent(this@RegisterActivity, HomeActivity::class.java))
        /*    }
        })*/
    }



}