//package com.veles.authorizationflows.model.api
//
//import android.content.Context
//import com.veles.authorizationflows.R
//
//
//public fun Daily.getMinMaxTemp(context: Context): String {
//    return context.run {
//        getString(R.string.temperature, temp.min.toInt().convertWhenIsPositive())
//            .plus(" ")
//            .plus(getString(R.string.temperature, temp.max.toInt().convertWhenIsPositive()))
//    }
//}
