package com.veles.authorizationflows.base

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import com.veles.authorizationflows.base.mvvm.activity.BaseActivity
import javax.inject.Inject
import javax.inject.Singleton

class DefaultNavigator @Inject internal constructor(private var baseActivity: BaseActivity<*, *>?) : INavigator {
    fun <T : Activity?> openScreen(_activityToOpen: Class<T>?) {
        openScreen(_activityToOpen, false)
    }

    fun <T : Activity?> openScreen(_activityToOpen: Class<T>?,
                                   _finishCurrent: Boolean) {
        openScreen(_activityToOpen, null, null, _finishCurrent,
            finishAll = false,
            isAnimation = false,
            enterAnimation = 0,
            exitAnimation = 0
        )
    }

    fun <T : Activity?> openScreen(_activityToOpen: Class<T>?,
                                   _finishCurrent: Boolean, enterAnimation: Int, exitAnimation: Int) {
        openScreen(_activityToOpen, null, null, _finishCurrent,
            finishAll = false,
            isAnimation = true,
            enterAnimation = enterAnimation,
            exitAnimation = exitAnimation
        )
    }

    fun <T : Activity?> openScreen(_activityToOpen: Class<T>?,
                                   _finishCurrent: Boolean,
                                   _finishAll: Boolean) {
        openScreen(_activityToOpen, null, null, _finishCurrent, _finishAll, false, 0, 0)
    }

    fun <T : Activity?> openScreen(_activityToOpen: Class<T>?,
                                   extras: Pair<String?, Bundle?>?,
                                   finishCurrent: Boolean) {
        openScreen(_activityToOpen, extras, null, finishCurrent,
            finishAll = false,
            isAnimation = false,
            enterAnimation = 0,
            exitAnimation = 0
        )
    }

    fun <T : Activity?> openScreen(_activityToOpen: Class<T>?,
                                   extras: Pair<String?, Bundle?>?,
                                   finishCurrent: Boolean, enterAnimation: Int, exitAnimation: Int) {
        openScreen(_activityToOpen, extras, null, finishCurrent,
            finishAll = false,
            isAnimation = true,
            enterAnimation = enterAnimation,
            exitAnimation = exitAnimation
        )
    }

    fun <T : Activity?> openScreen(_activityToOpen: Class<T>?,
                                   _activityOptions: ActivityOptions?,
                                   finishCurrent: Boolean) {
        openScreen(_activityToOpen, null, _activityOptions, finishCurrent,
            finishAll = false,
            isAnimation = false,
            enterAnimation = 0,
            exitAnimation = 0
        )
    }

    fun <T : Activity?> openScreen(_activityToOpen: Class<T>?,
                                   extras: Pair<String?, Bundle?>?,
                                   _activityOptions: ActivityOptions?,
                                   finishCurrent: Boolean,
                                   finishAll: Boolean, isAnimation: Boolean, enterAnimation: Int, exitAnimation: Int) {
        val launchIntent = Intent(baseActivity, _activityToOpen)
        if (extras != null) {
            launchIntent.putExtra(extras.first, extras.second)
        }
        ActivityCompat.startActivity(baseActivity!!, launchIntent,
                _activityOptions?.toBundle())
        if (finishAll) baseActivity!!.finishAffinity() else if (finishCurrent) {
            if (isAnimation) {
                baseActivity!!.overridePendingTransition(enterAnimation, exitAnimation)
            }
            baseActivity!!.finish()
        }
    }

    fun <T : Activity?> openScreenForResult(_activityToOpen: Class<T>?,
                                            _extras: Pair<String?, Bundle?>?,
                                            _activityOptions: ActivityOptions?,
                                            _requestCode: Int) {
        val launchIntent = Intent(baseActivity, _activityToOpen)
        if (_extras != null) {
            launchIntent.putExtra(_extras.first, _extras.second)
        }
        ActivityCompat.startActivityForResult(baseActivity!!, launchIntent,
                _requestCode, _activityOptions?.toBundle())
    }

    fun finishWithResult(_responseCode: Int,
                         _extras: Pair<String?, Bundle?>?) {
        val resultIntent = Intent()
        if (_extras != null) {
            resultIntent.putExtra(_extras.first, _extras.second)
        }
        baseActivity!!.setResult(_responseCode, resultIntent)
        baseActivity!!.finish()
    }

    override fun <T : Fragment?> openScreen(_fragmentToShow: Class<T>?,
                                            _arguments: Bundle?) {
        openScreen(_fragmentToShow, true, _arguments)
    }

    override fun <T : Fragment?> openScreen(_fragmentToShow: Class<T>?,
                                            _addToBackStack: Boolean,
                                            _arguments: Bundle?) {
        val currentFragment = baseActivity!!.supportFragmentManager
                .findFragmentById(baseActivity!!.fragmentContainerId)
        if (currentFragment != null && currentFragment.javaClass == _fragmentToShow) return
        val fragment: Fragment = if (_arguments == null) {
            Fragment.instantiate(baseActivity!!, _fragmentToShow!!.name)
        } else {
            Fragment.instantiate(baseActivity!!, _fragmentToShow!!.name, _arguments)
        }
        val fragmentTransaction = baseActivity!!.supportFragmentManager
                .beginTransaction()
        if (_addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.name)
        }
        fragmentTransaction.replace(baseActivity!!.fragmentContainerId, fragment, fragment.javaClass.name)
        fragmentTransaction.commitAllowingStateLoss()
    }

    override fun navigateBack(): Boolean {
        return navigateBack(false)
    }

    override fun navigateBack(_closeAll: Boolean): Boolean {
        if (!_closeAll) return baseActivity!!.supportFragmentManager.popBackStackImmediate()
        val fragmentManager = baseActivity!!.supportFragmentManager
        val backStackSize = fragmentManager.backStackEntryCount
        for (i in 0 until backStackSize) {
            fragmentManager.popBackStack()
        }
        return false
    }

    override fun dispose() {
        baseActivity = null
    }

}