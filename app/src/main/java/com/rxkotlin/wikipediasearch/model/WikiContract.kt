package com.rxkotlin.wikipediasearch.model

import com.rxkotlin.base.model.BaseContract

class WikiContract {

    interface WikiView : BaseContract.BaseView {
        fun showProgressbar()
        fun hideProgressbar()
        fun showWikiDisplayItem(searchList : List<Search>)
    }

    interface WikiUserActionListener : BaseContract.BaseUserActionsListener {
        fun loadWikiConfig(search:String)
        fun onStop()

    }
}
