package com.example.mad_practical_8_21012011125

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cardView = findViewById<MaterialCardView>(R.id.card2)
        cardView.visibility= View.GONE
        val createAlarm = findViewById<MaterialButton>(R.id.button_Alarm)
        createAlarm.setOnClickListener{
            //cardView.visibility=View.VISIBLE
            TimePickerDialog(this, { tp, hour, minute -> setAlarmTime(hour, minute) },
                Calendar.HOUR, Calendar.MINUTE,false).show()
        }
    }

    fun setAlarmTime(hour:Int,minute:Int)
    {
        val cardView=findViewById<MaterialCardView>(R.id.card2)
        cardView.visibility=View.GONE
        val alarmtime=Calendar.getInstance()
        val year=alarmtime.get(Calendar.YEAR)
        val month=alarmtime.get(Calendar.MONTH)
        val date=alarmtime.get(Calendar.DATE)
        alarmtime.set(year, month, date, hour, minute)
        setAlarm(alarmtime.timeInMillis,AlarmBroadcastReceiver.ALARM_START)
        //cardView.visibility=View.GONE
    }


    fun stop()
    {
        setAlarm(-1,AlarmBroadcastReceiver.ALARM_STOP)
    }

    fun setAlarm(millitime:Long,action:String){
        val intentAlarm=Intent(applicationContext,AlarmBroadcastReceiver::class.java)
        intentAlarm.putExtra(AlarmBroadcastReceiver.ALARMKEY,action)
        val pendingIntent=PendingIntent.getBroadcast(applicationContext,2345,intentAlarm,PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmmanager=getSystemService(ALARM_SERVICE) as AlarmManager
        if(action==AlarmBroadcastReceiver.ALARM_START){
            alarmmanager.setExact(AlarmManager.RTC_WAKEUP,millitime,pendingIntent)
        }
        else if(action==AlarmBroadcastReceiver.ALARM_STOP)
            alarmmanager.cancel(pendingIntent)
        sendBroadcast(intentAlarm)
    }
}