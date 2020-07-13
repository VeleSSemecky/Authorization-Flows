package com.veles.authorizationflows.base

import android.os.Bundle
import androidx.fragment.app.Fragment

interface INavigator {
    fun navigateBack(): Boolean
    fun navigateBack(_closeAll: Boolean): Boolean
    fun dispose()
    fun <T : Fragment?> openScreen(_fragmentToShow: Class<T>?,
                                   _arguments: Bundle?)

    fun <T : Fragment?> openScreen(_fragmentToShow: Class<T>?,
                                   _addToBackStack: Boolean,
                                   _arguments: Bundle?)
}