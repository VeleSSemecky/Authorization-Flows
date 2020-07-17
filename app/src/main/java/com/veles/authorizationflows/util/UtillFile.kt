package com.veles.authorizationflows.util

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigateActivity(directions: NavDirections, finishCurrent: Boolean) {
    findNavController().navigate(directions)
    if(finishCurrent) requireActivity().finish()
}