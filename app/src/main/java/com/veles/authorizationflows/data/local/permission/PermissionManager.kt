package com.veles.authorizationflows.data.local.permission

import android.app.Activity
import androidx.fragment.app.Fragment
import com.veles.authorizationflows.base.error.Command

interface PermissionManager {
    fun checkPermissionCamera(): PermissionManager
    fun checkPermissionWriteExternalStorage(): PermissionManager
    fun checkPermissionCallPhone(): PermissionManager
    fun <T : Activity> checkPermissionResult(activity: T)
    fun <T : Fragment> checkPermissionResult(fragment: T)
    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>?,
        grantResults: IntArray?,
        requestPermissionsResult: Command<PermissionResult>
    )
    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>?,
        grantResults: IntArray?,
        requestPermissionsResult: (PermissionResult) -> Unit
    )
}