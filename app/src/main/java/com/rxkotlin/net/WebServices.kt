package com.rxkotlin.net

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun createWebService(): WebApiInterfaces {
    val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://en.wikipedia.org/w")//Base Url
        .build()
    return retrofit.create(WebApiInterfaces::class.java)
}
