package com.example.getmycontacts

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (getCurrentStateContactsPermission()) {
            Log.d(ACTIVITY_LOG_TAG, "access granded")
        } else {
            Log.d(ACTIVITY_LOG_TAG, "access request")
            requestContactsPermission()
        }
    }

    fun getCurrentStateContactsPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED

    fun requestContactsPermission() =
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_CONTACTS),
            READ_CONTACT_RC
        )

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_CONTACT_RC && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(ACTIVITY_LOG_TAG, "access granded")
            } else {
                Log.d(ACTIVITY_LOG_TAG, "access denied")
                Toast.makeText(this, "Нет доступа к контактам", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        const val ACTIVITY_LOG_TAG = ".MainActivity"
        const val READ_CONTACT_RC = 200
    }
}