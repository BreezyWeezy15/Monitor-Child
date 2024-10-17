package com.app.lockcomposeChild

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.google.firebase.database.*

class AppUpdateReceiver : BroadcastReceiver() {

    private lateinit var firebaseDatabase: DatabaseReference
    override fun onReceive(context: Context, intent: Intent?) {
        fetchFirebaseData(context)
    }

    private fun fetchFirebaseData(context: Context) {

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("apps")
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                showAppIcon(context)
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        }

        firebaseDatabase.addValueEventListener(valueEventListener)
    }


    private fun showAppIcon(context: Context) {
        val componentName = ComponentName(context, "com.app.lockcomposeChild.MainActivity")
        context.packageManager.setComponentEnabledSetting(
            componentName,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }

}
