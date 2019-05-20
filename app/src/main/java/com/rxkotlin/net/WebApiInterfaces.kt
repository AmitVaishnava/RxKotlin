package com.rxkotlin.net

import com.rxkotlin.wikipediasearch.model.WikiModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApiInterfaces {
    @GET("/api.php")
    fun hitCountCheck(@Query("action") action: String,
                      @Query("format") format: String,
                      @Query("list") list: String,
                      @Query("srsearch") srsearch: String):
            Observable<WikiModel.Result>
}
