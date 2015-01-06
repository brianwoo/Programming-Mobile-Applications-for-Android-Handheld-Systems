/**
 * 
 */
package com.bwoo.dailyselfie;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * @author bwoo
 *
 */
public class SelfieAlarmBroadcastReceiver extends BroadcastReceiver
{
	

	public void setupAlarm(Context context)
	{
		
		AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, SelfieAlarmBroadcastReceiver.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

		alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				60 * 1000,
				60 * 1000, 
				alarmIntent);
	}


	@Override
	public void onReceive(Context context, Intent intent)
	{
		System.out.println("#### alarm received!!");
	}
}
