package com.coruptiaucide.vavedem.utils

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.*

/**
 * Created by tiberiugolaes on 01/05/2017.
 */
open class EmptyStateRecyclerView : RecyclerView {
    var emptyView: View? = null
        set(view) {
            field = view
            checkEmptyView()
        }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    private fun checkEmptyView() {
        if (adapter == null) {
            return;
        }
        if (adapter.itemCount > 0) {
            emptyView?.visibility = View.GONE
        } else {
            emptyView?.visibility = View.VISIBLE
        }
    }

    private val observer = object : AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged();
            checkEmptyView();
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            super.onItemRangeChanged(positionStart, itemCount);
            checkEmptyView();
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount);
            checkEmptyView();
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            checkEmptyView();
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount);
            checkEmptyView();

        }
    };


    override fun setAdapter(adapter: Adapter<ViewHolder>?) {
        val oldAdapter = this.adapter;
        oldAdapter?.unregisterAdapterDataObserver(observer);
        super.setAdapter(adapter);
        adapter?.registerAdapterDataObserver(observer);
    }

    override fun swapAdapter(adapter: Adapter<ViewHolder>?, removeAndRecycleExistingViews: Boolean) {
        val oldAdapter = this.adapter;
        oldAdapter?.unregisterAdapterDataObserver(observer);
        adapter?.registerAdapterDataObserver(observer);
        super.swapAdapter(adapter, removeAndRecycleExistingViews);
        checkEmptyView();
    }

    abstract class SimpleAdapter<T, VH : SimpleViewHolder<T>>() : RecyclerView.Adapter<VH>() {
        val mItems = ArrayList<T>()
        override fun getItemCount(): Int {
            if (mItems.size == 0) {
                return 0
            } else {
                return mItems.size
            }
        }

        fun setData(items: List<T>?) {
            mItems.clear()
            Log.d("EmptyStateRecyclerView", "setData: items.size - " + items?.size)

            if (items != null) {
                mItems.addAll(items)
            }
            notifyDataSetChanged()
        }


        override final fun onBindViewHolder(holder: VH, i: Int) {
            if (i < mItems.size) {
                holder.bind(mItems[i])
            }
        }
    }

    abstract class SimpleViewHolder<T>(itemView: View, activity: Activity?) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

}
