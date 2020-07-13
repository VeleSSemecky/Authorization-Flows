package com.veles.authorizationflows.presentation.splash

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.veles.authorizationflows.R
import com.veles.authorizationflows.base.DefaultNavigator
import com.veles.authorizationflows.base.mvvm.activity.BaseActivity
import com.veles.authorizationflows.presentation.authorization.login.LoginFragment


public class SplashActivity : BaseActivity<SplashContract.ViewModel, DefaultNavigator>(), SplashContract.View {

    override val viewModel: SplashContract.ViewModel by viewModels { viewModelFactory }

    override val layoutResource: Int = 0

    override fun onViewReady(_savedInstanceState: Bundle?) {
        super.onViewReady(_savedInstanceState)
        navigator.openScreen(viewModel.openScreen() ,true)
    }

}
