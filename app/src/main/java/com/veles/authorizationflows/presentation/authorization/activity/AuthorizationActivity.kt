package com.veles.authorizationflows.presentation.authorization.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.veles.authorizationflows.R
import com.veles.authorizationflows.base.DefaultNavigator
import com.veles.authorizationflows.base.mvvm.activity.BaseActivity
import com.veles.authorizationflows.presentation.authorization.login.LoginFragment


public class AuthorizationActivity : BaseActivity<AuthorizationContract.ViewModel, DefaultNavigator>(), AuthorizationContract.View {

    override val viewModel: AuthorizationContract.ViewModel by viewModels { viewModelFactory }

    override val layoutResource: Int = R.layout.activity_authorization

    override val fragmentContainerId: Int = R.id.fragment_container

    override fun onViewReady(_savedInstanceState: Bundle?) {
        super.onViewReady(_savedInstanceState)
        viewModel.openScreen(LoginFragment::class.java).observe(this@AuthorizationActivity, Observer {
            navigator.openScreen(it,false,null)
        })
    }

}
