package com.andreikslpv.binparser.domain


interface ApiCallback {
    fun onSuccess(films: List<String>)
    fun onFailure(message: String)
}