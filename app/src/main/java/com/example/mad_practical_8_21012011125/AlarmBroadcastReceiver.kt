package com.example.mad_practical_8_21012011125

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
class AlarmBroadcastReceiver : BroadcastReceiver() {
    companion object{
        val ALARMKEY="ak"
        val ALARM_STOP="asp"
        val ALARM_START="ast"
    }
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        if (intent!= null){
            val data=intent.getStringExtra(ALARMKEY)
            if (data!=null){
                val intentservice=Intent(context,AlarmService::class.java)
                if(data==ALARM_START)
                {
                    context.startService(intentservice)
                }
                else{
                    context.stopService(intentservice)
                }
            }
        }
    }

}