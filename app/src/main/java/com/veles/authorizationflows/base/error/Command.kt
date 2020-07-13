package com.veles.authorizationflows.base.error

interface Command<T> {
    fun execute(arg: T)
}