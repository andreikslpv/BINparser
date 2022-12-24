package com.andreikslpv.binparser.domain

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}