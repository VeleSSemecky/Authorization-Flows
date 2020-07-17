package com.veles.authorizationflows.presentation.change

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.veles.authorizationflows.R
import com.veles.authorizationflows.base.mvvm.fragment.BaseFragment
import com.veles.authorizationflows.data.bus.SharedViewModelImpl
import kotlinx.android.synthetic.main.fragment_change_field.*
import kotlinx.android.synthetic.main.toolbar_back.*


class ChangeFieldFragment : BaseFragment<ChangeFieldContract.ViewModel>(),
    ChangeFieldContract.View {

    override val viewModel: ChangeFieldContract.ViewModel by viewModels { viewModelFactory }

    private val sharedViewModel: SharedViewModelImpl<Boolean> by activityViewModels()

    private val args: ChangeFieldFragmentArgs by navArgs()

    override val toolbarResource: Int
        get() = R.layout.toolbar_back

    override val layoutResource: Int
        get() = R.layout.fragment_change_field

    override fun onViewReady(_savedInstanceState: Bundle?) {
        sntSmMagic.isChecked = args.myArg
        sntSmMagic.setOnCheckedChangeListener { _, b -> sharedViewModel.setShared(b) }
        sntIvBack.setOnClickListener { findNavController().popBackStack() }
    }

}