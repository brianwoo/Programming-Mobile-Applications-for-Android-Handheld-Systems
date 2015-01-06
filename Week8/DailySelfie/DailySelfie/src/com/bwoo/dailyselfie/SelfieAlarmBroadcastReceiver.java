/**
 * 
 */
package com.bwoo.dailyselfie;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;

/**
 * @author bwoo
 *
 */
public class SelfieAlarmBroadcastReceiver extends BroadcastReceiver
{
	
	
	
	// Notification ID to allow for future updates
	private static final int MY_NOTIFICATION_ID = 1;
	
	/**
	 * Setup the alarm
	 * 
	 * @param context
	 */
	public void setupAlarm(Context context)
	{
		
		AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		PendingIntent alarmIntent = getAlarmIntent(context);

		alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				SystemClock.elapsedRealtime() + (60 * 1000),
				60 * 1000, 
				alarmIntent);
	}

	
	/**
	 * Cancel the alarm
	 * 
	 * @param context
	 */
	public void cancelAlarm(Context context)
	{
		AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		PendingIntent alarmIntent = getAlarmIntent(context);
		
		alarmMgr.cancel(alarmIntent);
	}
	
	
	
	private PendingIntent getAlarmIntent(Context context)
	{
		Intent intent = new Intent(context, SelfieAlarmBroadcastReceiver.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		
		return alarmIntent;
	}
	
	//public void cancelAlarm()
	

	@Override
	public void onReceive(Context context, Intent intent)
	{
		
		System.out.println("#### alarm received!!");
		
		Intent mNotificationIntent = new Intent(context,
				MainActivity.class);
		
		PendingIntent mContentIntent = PendingIntent.getActivity(context, 0, 
				mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
		
		long[] mVibratePattern = { 0, 200, 200, 300 };
		
		// Define the Notification's expanded message and Intent:
		Notification.Builder notificationBuilder = new Notification.Builder(
				context)
				.setTicker("Time for another selfie")
				.setSmallIcon(android.R.drawable.stat_sys_warning)
				.setAutoCancel(true)
				.setContentTitle("Daily Selfie")
				.setContentText(
						"Time for another selfie 2")
				.setContentIntent(mContentIntent)
				.setVibrate(mVibratePattern);

		// Pass the Notification to the NotificationManager:
		NotificationManager mNotificationManager = 
				(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(MY_NOTIFICATION_ID,
				notificationBuilder.build());
	}

}
