package com.coruptiaucide.vavedem.ui.fragments

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.ImageView
import com.coruptiaucide.vavedem.R
import com.coruptiaucide.vavedem.api.model.Adresa
import com.coruptiaucide.vavedem.api.model.Primarie
import com.coruptiaucide.vavedem.ui.activities.PRIMARIE_EXTRA
import com.coruptiaucide.vavedem.ui.activities.PrimarieActivity
import com.coruptiaucide.vavedem.ui.activities.SecondaryActivity
import com.coruptiaucide.vavedem.utils.EmptyStateRecyclerView
import com.coruptiaucide.vavedem.utils.SimpleDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_primarii.*
import kotlinx.android.synthetic.main.view_item_primarie.view.*
import java.util.*


/**
 * Created by tiberiugolaes on 02/05/2017.
 */
internal const val EMPTY_STRING = ""

class PrimariiFragment : BaseFragment() {

    internal var mAdapter: EmptyStateRecyclerView.SimpleAdapter<Primarie, PrimarieViewHolder>? = null
    internal var mList: List<Primarie> = emptyList()
    lateinit var mRecyclerView: EmptyStateRecyclerView
    lateinit var searchView: SearchView
    lateinit var searchItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_primarii, container, false)
        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = rv_primarii as EmptyStateRecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = createAdapter()
        mRecyclerView.emptyView = view?.findViewById(android.R.id.empty)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(SimpleDividerItemDecoration(ContextCompat.getDrawable(context, R.drawable.simple_divider)))
        mAdapter!!.setData(emptyList())
        setFailState()
    }

    internal fun createAdapter(): EmptyStateRecyclerView.SimpleAdapter<Primarie, PrimarieViewHolder> {
        return object : EmptyStateRecyclerView.SimpleAdapter<Primarie, PrimarieViewHolder>() {
            override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PrimarieViewHolder {
                return PrimarieViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.view_item_primarie, viewGroup, false), activity)
            }
        }
    }

    internal class PrimarieViewHolder(itemView: View, val activity: Activity) : EmptyStateRecyclerView.SimpleViewHolder<Primarie>(itemView, activity) {
        override fun bind(item: Primarie) {
            itemView.apply {
                tvEmail.text = item.email
                tvNume.text = item.nume
                tvEmail.paintFlags = tvEmail.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                tvAdresa.text = item.adresa.strada.plus(if (item.adresa.nr.equals(EMPTY_STRING)) "," else " nr. ${item.adresa.nr},")
                        .plus(if (item.adresa.codPostal.equals(EMPTY_STRING)) " ," else " ${item.adresa.codPostal},")
                        .plus(item.adresa.localitatea)
                ivPrimarie.setOnClickListener { context.startActivity(Intent(context, PrimarieActivity::class.java).putExtra(PRIMARIE_EXTRA, item)) }
                if (item.telefon.equals(EMPTY_STRING)) {
                    tvEmail.setOnClickListener { }
                } else {
                    tvEmail.setOnClickListener { (activity as SecondaryActivity).sendEmail(item.email) }
                }
            }
        }
    }

    private fun setFailState() {
        empty.visibility = View.GONE
        rv_primarii.visibility = View.GONE
        failPrimarii.visibility = View.VISIBLE
        retryText.setOnClickListener { loadPrimarii() }
    }

    private fun loadPrimarii() {
        empty.visibility = View.VISIBLE
        rv_primarii.visibility = View.GONE
        failPrimarii.visibility = View.GONE
        var list = ArrayList<Primarie>()
        list.add(Primarie(0, Adresa(0, 620920, "Bucuresti", 12, "Bulevardul Iuliu Maniu"), 223, "ps6@gmail.com", "Primaria sector 6", 340000, "0745036474"))
        list.add(Primarie(1, Adresa(0, 620921, "Bucuresti", 14, "Calea Victoriei"), 223, "ps1@gmail.com", "Primaria sector 1", 340000, "0745036474"))
        list.add(Primarie(2, Adresa(0, 620920, "Craiova", 19, "Strada Expozitiei"), 223, "primariacraiova@gmail.com", "Primaria Craiova", 340000, "0745036474"))
        list.add(Primarie(3, Adresa(0, 620921, "Iasi", 20, "Calea Victoriei"), 223, "primariaiasi@gmail.com", "Primaria Iasi", 340000, "0745036474"))
        rv_primarii.visibility = View.VISIBLE
        mList = list
        mAdapter?.setData(list)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_search_primarie, menu)
        searchItem = menu.findItem(R.id.action_search)
        val searchManager = activity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem.actionView as android.support.v7.widget.SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.callingActivity))
        val searchClose = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn) as ImageView
        searchClose.setImageResource(R.drawable.close)
        searchClose.setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_ATOP)
        searchView.setOnSearchClickListener {
            (activity as SecondaryActivity).supportActionBar?.setHomeAsUpIndicator(null)
            (activity as SecondaryActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
            (activity as SecondaryActivity).findViewById(R.id.tv_title).visibility = View.GONE
        }
        searchView.setOnCloseListener {
            (activity as SecondaryActivity).displayCloseButton()
            (activity as SecondaryActivity).findViewById(R.id.tv_title).visibility = View.VISIBLE
            false
        }
        val queryTextListener = object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                filterPrimarii(p0 as String)
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                filterPrimarii(p0 as String)
                return true
            }
        }
        searchView.setOnQueryTextListener(queryTextListener)
        super.onCreateOptionsMenu(menu, menuInflater)
    }


    fun filterPrimarii(text: String) {
        if (!text.isNullOrBlank()) {
            mAdapter?.setData(mList.filter {
                it ->
                it.nume.toUpperCase().contains(text.toUpperCase()) || it.adresa.strada.toUpperCase().contains(text.toUpperCase())
            })
        } else {
            mAdapter?.setData(mList)
        }
    }
}