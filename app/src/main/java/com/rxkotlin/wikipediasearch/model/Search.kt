package com.rxkotlin.wikipediasearch.model

import com.google.gson.annotations.SerializedName

data class Search(val ns:Int) {

    @SerializedName("title")
    var title: String? = null
    @SerializedName("pageid")
    var pageid: Int? = null
    @SerializedName("size")
    var size: Int? = null
    @SerializedName("wordcount")
    var wordcount: Int? = null
    @SerializedName("snippet")
    var snippet: String? = null
    @SerializedName("timestamp")
    var timestamp: String? = null
}
