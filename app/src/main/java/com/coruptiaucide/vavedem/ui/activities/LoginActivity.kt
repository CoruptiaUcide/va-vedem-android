package com.coruptiaucide.vavedem.ui.activities

import android.content.Intent
import android.os.Bundle
import com.coruptiaucide.vavedem.R

import kotlinx.android.synthetic.main.activity_login.*


/**
 * Created by Tiberiu on 6/23/2016.
 */

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = ""
        tv_title.text = resources.getString(R.string.login_text)
        setSupportActionBar(toolbar)
        displayCloseButton()
        toolbar.setNavigationOnClickListener {
            finish()
        }
        btnLoginConfirm.setOnClickListener {
            if (emailLogin.isValid() && passwordLogin.isValid()) {
                login()
            } else {
                if (!emailLogin.isValid()) {
                    login_email_wrapper.error = getString(R.string.error_email)
                } else {
                    login_email_wrapper.error = null
                }
                if (!passwordLogin.isValid()) {
                    login_password_wrapper.error = getString(R.string.error_password)
                } else {
                    login_password_wrapper.error = null
                }
            }
        }

        passwordLogin.setOnFocusChangeListener { view, motionEvent ->
            login_password_wrapper.error = null
        }
        emailLogin.setOnFocusChangeListener { view, b ->
            login_email_wrapper.error = null

        }
        btnForgotPassword.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }
    }

    fun login() {
       /* VaVedemApplication.Companion.showProgressBar(this@LoginActivity)
        SessionRequester.login(emailLogin.text.toString(), passwordLogin.text.toString())
                .subscribeOn(Schedulers.io()).subscribe(object : InertObserver<DataSession>() {


            override fun onError(e: Throwable?) {
                VaVedemApplication.closeProgressBar()
                Snackbar.make(btnLoginConfirm, "${e?.message}", Snackbar.LENGTH_LONG)
            }

            override fun onNext(session: DataSession) {
                Log.d("Results", "${session.sessionId},${session.userId}")
                VaVedemApplication.closeProgressBar()*/
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
      /*      }
        })*/
    }

}