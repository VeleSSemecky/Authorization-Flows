package com.veles.authorizationflows.data.mapper

import java.lang.reflect.Type

interface DataMapper {
    fun <T> fromString(_data: String?, _tClass: Class<T>?): T
    fun <T> fromString(_data: String?, _tClass: Type?): T
    fun <T> toString(_data: T): String?
}