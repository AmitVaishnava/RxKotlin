package com.rxkotlin.wikipediasearch.presenter

import com.rxkotlin.net.WebServicesCallback
import com.rxkotlin.base.presenter.BasePresenter
import com.rxkotlin.wikipediasearch.model.Search
import com.rxkotlin.wikipediasearch.model.WikiContract
import com.rxkotlin.wikipediasearch.model.WikiServiceApi


class WikiPresenter(wikiserviceApi: WikiServiceApi) : BasePresenter<WikiContract.WikiView>(),
    WikiContract.WikiUserActionListener {

    var mWikiserviceApi = wikiserviceApi
    var mSearchList: List<Search>? = null

    override fun loadWikiConfig(search: String) {

        if (mSearchList!=null&& mSearchList!!.size > 0) {
            view()?.showWikiDisplayItem(mSearchList!!)
            return
        }

        view()?.showProgressbar()
        this.mWikiserviceApi.getWikiData(search, object : WebServicesCallback<List<Search>> {
            override fun onDefaultError() {
                view()?.hideProgressbar()
                view()?.showDefaultError()
            }

            override fun onFailure(errorMsg: String) {
                view()?.hideProgressbar()
                view()?.showErrorMsg(errorMsg)

            }

            override fun onSuccess(searchList: List<Search>) {
                view()?.hideProgressbar()
                mSearchList = searchList
                view()?.showWikiDisplayItem(mSearchList!!)
            }
        })
    }

    override fun onStop() {

    }

}
