package com.coruptiaucide.vavedem.utils

import android.content.Context
import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by tiberiugolaes on 07/05/2017.
 */
public class SpaceDividerItemDecoration : RecyclerView.ItemDecoration {

    private val mDividerSize: Int
    private var mOrientation: Int? = null

    var mSpaceStart = false
    var mSpaceEnd = false

    constructor(context: Context, orientation: Int, @DimenRes dividerHeightRes: Int) {
        val resources = context.resources
        mDividerSize = if (dividerHeightRes != 0) resources.getDimensionPixelSize(dividerHeightRes) else 0
        setOrientation(orientation)
    }

    fun setOrientation(orientation: Int) {
        if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL) {
            throw IllegalArgumentException("invalid orientation")
        }
        mOrientation = orientation
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildPosition(view)
        if (position >= state.itemCount - 1 && !mSpaceEnd) return;
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, if (position == 0 && mSpaceStart) mDividerSize else 0, 0, mDividerSize)
        } else {
            outRect.set(if (position == 0 && mSpaceStart) mDividerSize else 0, 0, mDividerSize, 0)
        }
    }

}
