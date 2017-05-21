package com.coruptiaucide.vavedem.utils

import android.annotation.TargetApi
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.support.annotation.ColorRes
import android.support.annotation.StyleRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.ImageView
import com.coruptiaucide.vavedem.R

/**
 * Created by tiberiugolaes on 07/05/2017.
 */
public open class TintedImageView : ImageView {
    @TargetApi(21)
    constructor(context: Context, attrs: AttributeSet?, @StyleRes defStyleAttr: Int, @StyleRes defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }

    constructor(context: Context, attrs: AttributeSet?, @StyleRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    private var mTintColor: ColorStateList? = null

    private fun init(context: Context, attrs: AttributeSet?, @StyleRes defStyleAttr: Int, @StyleRes defStyleRes: Int) {
        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.TintedImageView,
                defStyleAttr, defStyleRes);

        try {
            mTintColor = a.getColorStateList(R.styleable.TintedImageView_tintColor);
        } finally {
            a.recycle();
        }

        updateColorFilter()
    }

    fun setTintColor(@ColorRes color: Int) {
        mTintColor = ContextCompat.getColorStateList(context, color)
        updateColorFilter()
    }

    fun setTintColorNonResource(color: Int) {
        mTintColor = ColorStateList.valueOf(color)
        updateColorFilter()
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        if (mTintColor != null && mTintColor!!.isStateful) {
            updateColorFilter()
        }
    }

    private fun updateColorFilter() {
        if (mTintColor != null) {
            setColorFilter(mTintColor!!.getColorForState(drawableState, mTintColor!!.defaultColor), PorterDuff.Mode.SRC_IN)
        }
    }
}