package com.veles.authorizationflows.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.veles.authorizationflows.R
import com.veles.authorizationflows.base.mvvm.activity.BaseActivity


class MainActivity : BaseActivity<MainContract.ViewModel>(), MainContract.View {

    override val viewModel: MainContract.ViewModel by viewModels { viewModelFactory }

    override val layoutResource: Int = R.layout.activity_main

    private lateinit var navController: NavController

    override fun onViewReady(_savedInstanceState: Bundle?) {
        super.onViewReady(_savedInstanceState)
        navController = Navigation.findNavController(this, R.id.auth_fragment)
    }

}
