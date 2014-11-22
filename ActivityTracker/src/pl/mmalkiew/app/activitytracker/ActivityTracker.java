package pl.mmalkiew.app.activitytracker;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

public class ActivityTracker extends Activity {
	
	private NotificationManager notificationManager = null;
	private static final int ID_NOTIFY_ME = 1500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_activity_tracker);
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		createNotification("onCreate", "Created!", null);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		createNotification("onPause", "Paused!", "App stopped");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		createNotification("onResume", "Resumed!", "App resumed");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		createNotification("onStop", "Stopped", "App stopped");
	}
	
	protected void onDestroy() {
		super.onDestroy();
		createNotification("onDestroy", "Destroyed!", "App destroyed");
	}
	
	@Override
	  protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    super.onRestoreInstanceState(savedInstanceState);
	    createNotification("onRestoreInstanceState", "Restored!", "App restored");
	  }

	  @Override
	  protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    createNotification("onSaveInstanceState", "Saved!", "App saved");
	  }
	
	private void createNotification(String method, String status, String message) {
		Notification note = new Notification(R.drawable.activity_icon_small, status, System.currentTimeMillis());
		PendingIntent i = PendingIntent.getActivity(this, 0, new Intent(this, Notify.class), 0);
		note.setLatestEventInfo(this, method, message, i);
		
		notificationManager.notify(ID_NOTIFY_ME, note);
	}


}
