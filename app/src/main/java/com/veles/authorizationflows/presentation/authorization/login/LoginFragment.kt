package com.veles.authorizationflows.presentation.authorization.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.common.api.ApiException
import com.veles.authorizationflows.R
import com.veles.authorizationflows.base.DefaultNavigator
import com.veles.authorizationflows.base.mvvm.fragment.BaseFragment
import com.veles.authorizationflows.presentation.authorization.register.RegisterFragment
import com.veles.authorizationflows.presentation.main.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseFragment<LoginContract.ViewModel, DefaultNavigator>(),
    LoginContract.View {

    companion object {
        const val GOOGLE_SIGN_IN_REQUEST = 9001
    }

    override val viewModel: LoginContract.ViewModel by viewModels { viewModelFactory }

    override val toolbarResource: Int
        get() = R.layout.simple_toolbar

    override val layoutResource: Int
        get() = R.layout.fragment_login


    override fun onViewReady(_savedInstanceState: Bundle?) {
        viewModel.isAuth.observe(viewLifecycleOwner, Observer {
            if (it) navigator.openScreen(MainActivity::class.java, true)
        })
        googleLoginButton.setOnClickListener {
            val signInIntent = viewModel.getGoogleSignInClient().signInIntent
            startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST)
        }
        sntMbSingIn.setOnClickListener {
            viewModel.signInWithEmailAndPassword(
                sntEtEmail.text.toString(),
                sntEtPass.text.toString()
            )
        }
        sntMtvRegister.setOnClickListener {
            navigator.openScreen(RegisterFragment::class.java, viewModel.userEmailBundle())
        }
        viewModel.isProgress.observe(viewLifecycleOwner, Observer {
            sntProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN_REQUEST)
            try {
                data?.let { googleSign(it) }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
            }
    }


    private fun googleSign(data: Intent) {
        viewModel.apply {
            getSignedInAccountFromIntent(data)
            userLiveData.observe(viewLifecycleOwner, Observer {
                navigator.openScreen(RegisterFragment::class.java, userSocialBundle(it))
            })
        }
    }

}