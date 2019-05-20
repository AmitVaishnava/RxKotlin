package com.rxkotlin.wikipediasearch.model

import com.rxkotlin.net.WebServicesCallback

interface WikiServiceApi {
    fun getWikiData(search: String,wikiListCallback: WebServicesCallback<List<Search>>)
}
