package com.veles.authorizationflows.util

import android.text.TextUtils
import android.widget.EditText
import java.util.regex.Pattern

fun String.isTextValid(regex: Pattern): Boolean {
    return this.isEmpty().not() && regex.matcher(this).matches()
}

fun Int.convertWhenIsPositive():String{
    return when{
        this>0->"+$this"
        else->this.toString()
    }
}