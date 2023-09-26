package com.example.mad_practical_8_21012011125

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmBroadcastReceiver : BroadcastReceiver() {
    companion object
    {
        val ALARMKEY="ak"
        val ALARAM_STOP="asp"
        val ALARM_START="ast"
    }

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val data=intent.getStringExtra(ALARMKEY)
        val intentService = Intent(context,AlarmService::class.java)
        if(data== ALARM_START){
            context.startService(intentService)
        }
        else if(data== ALARAM_STOP){
            context.stopService(intentService)
        }
    }

}