package com.rxkotlin.base.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.rxkotlin.R
import com.rxkotlin.base.model.BaseContract

abstract class BaseActivity<T : BaseContract.BaseUserActionsListener> :
    AppCompatActivity(), BaseContract.BaseView {

    protected var mUserActionListener: T? = null
    protected var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUserActionListener()
        if (mUserActionListener != null)
            mUserActionListener!!.bindView(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (mUserActionListener != null)
            mUserActionListener!!.bindView(this)
    }

    //initialize presenter instance.
    open protected fun initUserActionListener() {

    }

    open protected fun initToolbar() {
        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()!!.setHomeButtonEnabled(true)
        mToolbar!!.setNavigationOnClickListener { onBackPressed() }
    }

    open protected fun setTitle(title: String) {
        mToolbar!!.setTitle(title)
    }


    override fun onRestart() {
        super.onRestart()
        if (mUserActionListener != null)
            mUserActionListener!!.bindView(this)
    }

    override fun onStop() {
        super.onStop()
        if (mUserActionListener != null)
            mUserActionListener!!.unbindView()
    }

    override fun showNetworkError() {
        Toast.makeText(this, getString(R.string.no_internet_connection_msg), Toast.LENGTH_SHORT)
    }

    override fun showErrorMsg(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT)
    }

    override fun showDefaultError() {
        Toast.makeText(this, getString(R.string.default_error_msg), Toast.LENGTH_SHORT)
    }
}
