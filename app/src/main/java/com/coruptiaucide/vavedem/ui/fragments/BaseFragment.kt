package com.coruptiaucide.vavedem.ui.fragments

import android.app.ProgressDialog
import android.support.v4.app.Fragment

/**
 * Created by tiberiugolaes on 02/05/2017.
 */
open class BaseFragment : Fragment() {

    var progressBar: ProgressDialog? = null

    fun showProgressBar() {
        if (progressBar?.isShowing ?: false) return
        progressBar = ProgressDialog(this.activity.window.context)
        progressBar?.show()
    }

    override fun onDestroyView() {
        closeProgressBar()
        super.onDestroyView()
    }

    fun closeProgressBar() {
        if (progressBar?.isShowing ?: false) {
            progressBar?.dismiss()
            progressBar = null
        }
    }
}

