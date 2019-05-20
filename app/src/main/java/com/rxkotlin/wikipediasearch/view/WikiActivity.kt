package com.rxkotlin.wikipediasearch.view

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import com.rxkotlin.R
import com.rxkotlin.wikipediasearch.model.Search
import com.rxkotlin.wikipediasearch.model.WikiContract
import com.rxkotlin.wikipediasearch.model.WikiServiceApiImpl
import com.rxkotlin.wikipediasearch.presenter.WikiPresenter
import com.rxkotlin.base.view.BaseActivity
import android.support.v7.widget.DividerItemDecoration
import android.widget.Button
import android.widget.Toast


class WikiActivity : BaseActivity<WikiContract.WikiUserActionListener>(), WikiContract.WikiView {

    /**
     * Ui variable declaration
     */
    private var mProgressBar: View? = null
    private var mSearchEditText: EditText? = null
    private var mRecyclerView: RecyclerView? = null
    private var mSearchButton: Button? = null

    /**
     * Variable declaration
     */
    private var mWikiAdapter: WikiAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wiki_list_fragment)

        initToolbar()
        mProgressBar = findViewById(R.id.progress_bar)
        mSearchEditText = findViewById(R.id.search_edt_text)

        mSearchButton = findViewById(R.id.search_btn)
        mSearchButton?.setOnClickListener(View.OnClickListener {
            if (!TextUtils.isEmpty(mSearchEditText?.text)) {
                mUserActionListener?.loadWikiConfig(mSearchEditText?.text.toString())
            } else
                Toast.makeText(this@WikiActivity, "Search Text is empty", Toast.LENGTH_SHORT)
        })
        mRecyclerView = findViewById(R.id.recycler_view)
        mWikiAdapter = WikiAdapter()
        mRecyclerView?.setLayoutManager(LinearLayoutManager(this))
        mRecyclerView?.setItemAnimator(DefaultItemAnimator())
        mRecyclerView?.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        mRecyclerView?.adapter = mWikiAdapter
    }

    override fun initUserActionListener() {
        mUserActionListener = WikiPresenter(WikiServiceApiImpl())
    }

    override fun onResume() {
        super.onResume()
        setTitle(getString(R.string.search_title))
        //Initially default data is set
        mUserActionListener?.loadWikiConfig("Trump")
    }

    override fun hideProgressbar() {
        mProgressBar?.visibility = View.GONE
    }

    override fun showProgressbar() {
        mProgressBar?.visibility = View.VISIBLE
    }

    override fun showWikiDisplayItem(searchList: List<Search>) {
        mWikiAdapter?.setWikiData(searchList)
    }
}
