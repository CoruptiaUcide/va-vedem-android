package com.coruptiaucide.vavedem

import android.app.Application
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.coruptiaucide.vavedem.api.Api
import com.coruptiaucide.vavedem.api.PrefManager
import com.coruptiaucide.vavedem.api.database.VaVedemSQLiteHelper
import com.coruptiaucide.vavedem.utils.ProgressDialogFragment


const val PROGRESS_FRAGMENT_TAG = "progress"

class VaVedemApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PrefManager.saveContext(applicationContext)
        Api.with(applicationContext, true)
    }

    companion object {


        var progressBar: ProgressDialogFragment? = null

        fun isLoggedIn(): Boolean {
            return PrefManager.sessionId != null
        }

        fun logout() {
            VaVedemSQLiteHelper.instance.clear(VaVedemSQLiteHelper.instance.writableDatabase)
            PrefManager.clearSession()
        }


        fun showProgressBar(fragment: Fragment) {
            if (progressBar != null && progressBar!!.isAdded) return
            progressBar = ProgressDialogFragment()
            val ft = fragment.activity.supportFragmentManager.beginTransaction()
            ft.add(progressBar, PROGRESS_FRAGMENT_TAG)
            ft.commitAllowingStateLoss()
        }

        fun showProgressBar(activity: AppCompatActivity) {
            if (progressBar != null && progressBar!!.isAdded) return
            progressBar = ProgressDialogFragment()
            val ft = activity.supportFragmentManager.beginTransaction()
            ft.add(progressBar, PROGRESS_FRAGMENT_TAG)
            ft.commitAllowingStateLoss()
        }

        fun closeProgressBar() {
            progressBar?.dismiss()
            progressBar = null
        }

    }
}