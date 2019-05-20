package com.rxkotlin.net

interface WebServicesCallback<T> {

    fun onSuccess(model: T)

    fun onFailure(errorMsg: String)

    fun onDefaultError()
}
