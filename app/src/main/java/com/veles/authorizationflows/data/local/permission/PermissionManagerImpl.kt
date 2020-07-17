package com.veles.authorizationflows.data.local.permission

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.annimon.stream.Stream
import com.veles.authorizationflows.base.error.Command
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionManagerImpl @Inject internal constructor() : PermissionManager {
    private val listPermissionsNeeded =
        ArrayList<String>()

    override fun checkPermissionCamera(): PermissionManager {
        addPermissionWillBeChecked(Manifest.permission.CAMERA)
        return this
    }

    override fun checkPermissionWriteExternalStorage(): PermissionManager {
        addPermissionWillBeChecked(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return this
    }

    override fun checkPermissionLocation(): PermissionManager {
        addPermissionWillBeChecked(Manifest.permission.ACCESS_COARSE_LOCATION)
        addPermissionWillBeChecked(Manifest.permission.ACCESS_FINE_LOCATION)
        return this
    }

    override fun checkPermissionCallPhone(): PermissionManager {
        addPermissionWillBeChecked(Manifest.permission.CALL_PHONE)
        return this
    }

    private fun addPermissionWillBeChecked(vararg permission: String) {
        Stream.of(*permission)
            .forEach { e: String ->
                listPermissionsNeeded.add(e)
            }
    }

    override fun <T : Activity> checkPermissionResult(activity: T) {
        onSetPermission(listPermissionsNeeded, activity)
    }

    override fun <T : Fragment> checkPermissionResult(fragment: T) {
        onSetPermission(listPermissionsNeeded, fragment)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>?,
        grantResults: IntArray?,
        requestPermissionsResult: Command<PermissionResult>
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults!!.size == listPermissionsNeeded.size) {
            val permissionResult =
                grantResults.all { value: Int -> value == PackageManager.PERMISSION_GRANTED }
            requestPermissionsResult.execute(if (permissionResult) PermissionResult.PERMISSION_GRANTED else PermissionResult.PERMISSION_DENIED)
        }
        listPermissionsNeeded.clear()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>?,
        grantResults: IntArray?,
        requestPermissionsResult: (PermissionResult) -> Unit
    ) {
        onRequestPermissionsResult(requestCode, permissions, grantResults, object :
            Command<PermissionResult> {
            override fun execute(arg: PermissionResult) {
                requestPermissionsResult(arg)
            }
        })
    }

    private fun <T : Activity?> onSetPermission(
        listPermissionsNeeded: ArrayList<String>?,
        context: T
    ) {
        if (listPermissionsNeeded == null || listPermissionsNeeded.isEmpty()) {
            return
        }
        ActivityCompat.requestPermissions(
            context!!, listPermissionsNeeded.toTypedArray(),
            Companion.PERMISSION_REQUEST_CODE
        )
    }

    private fun <T : Fragment?> onSetPermission(
        listPermissionsNeeded: ArrayList<String>?,
        context: T
    ) {
        if (listPermissionsNeeded == null || listPermissionsNeeded.isEmpty()) {
            return
        }
        context!!.requestPermissions(
            listPermissionsNeeded.toTypedArray(),
            PERMISSION_REQUEST_CODE
        )
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE: Int = 69
    }
}