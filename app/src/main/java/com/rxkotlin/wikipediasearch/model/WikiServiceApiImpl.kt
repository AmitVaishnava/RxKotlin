package com.rxkotlin.wikipediasearch.model

import android.text.TextUtils
import com.rxkotlin.net.WebServicesCallback
import com.rxkotlin.net.createWebService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WikiServiceApiImpl : WikiServiceApi {

    val QUERY : String = "query"
    val JSON : String = "json"
    val SEARCH_LIST : String = "search"
    override fun getWikiData(search: String,wikiListCallback: WebServicesCallback<List<Search>>) {

        createWebService().hitCountCheck(QUERY, JSON, SEARCH_LIST, search)
            .map { (query) -> query.search }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Search>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(searches: List<Search>) {
                    wikiListCallback.onSuccess(searches)
                }

                override fun onError(e: Throwable) {
                    if (TextUtils.isEmpty(e.message)) {
                        wikiListCallback.onDefaultError()
                    }else{
                        wikiListCallback.onFailure(e.message!!)
                    }
                }

                override fun onComplete() {

                }
            })
    }
}
