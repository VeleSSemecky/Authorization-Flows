package com.veles.authorizationflows.util

import android.text.TextUtils
import android.widget.EditText
import java.util.regex.Pattern

fun String.isTextValid(regex: Pattern): Boolean {
    return this.isEmpty().not() && regex.matcher(this).matches()
}

