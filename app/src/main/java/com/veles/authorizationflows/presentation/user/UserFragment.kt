package com.veles.authorizationflows.presentation.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.veles.authorizationflows.R
import com.veles.authorizationflows.base.mvvm.fragment.BaseFragment
import com.veles.authorizationflows.data.bus.SharedViewModelImpl

import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.toolbar_exit.*

class UserFragment : BaseFragment<UserContract.ViewModel>(),
    UserContract.View {

    override val viewModel: UserContract.ViewModel by viewModels { viewModelFactory }
    private val sharedViewModel: SharedViewModelImpl<Boolean> by activityViewModels()

    override val toolbarResource: Int
        get() = R.layout.toolbar_exit

    override val layoutResource: Int
        get() = R.layout.fragment_user


    override fun onViewReady(_savedInstanceState: Bundle?) {
        viewModel.openScreen()?.let {
            findNavController().navigate(it)
        }
        Glide.with(requireContext())
            .load(resources.getDrawable(R.drawable.cartman,null))
            .circleCrop()
            .into(sntIv)
        viewModel.apply {
            getUser()
            data.observe(viewLifecycleOwner, Observer {
                sntEtName.text = it.displayName?:""
                sntEtEmail.text = it.email?:""
            })
        }
        sntMbMagic.setOnClickListener {
            findNavController().navigate(UserFragmentDirections.changeField(sntIv.isVisible()))
        }
        sntMbWeather.setOnClickListener {
            findNavController().navigate(UserFragmentDirections.weatherFragment())
        }
        sntTvLogout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(UserFragmentDirections.main())
        }
        sharedViewModel.getShared().observe(viewLifecycleOwner, Observer {
            sntIv.visibility = if(it)View.VISIBLE else View.GONE
        })
    }

    fun View.isVisible() = when(this.visibility){
        View.GONE->false
        View.VISIBLE->true
        View.INVISIBLE->false
        else->false
    }

}