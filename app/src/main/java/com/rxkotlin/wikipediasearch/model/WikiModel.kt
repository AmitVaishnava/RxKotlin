package com.rxkotlin.wikipediasearch.model

import com.google.gson.annotations.SerializedName

object WikiModel {
    data class Result(val query: Query)
    data class Query(val search:List<Search>)
}
