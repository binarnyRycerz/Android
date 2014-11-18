package pl.binknight.android.notifydemo;

import android.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * @author binKnight, 2014.11
 *
 */
public class NotifyDemo extends Activity {

	private static final int ID_NOTIFY_ME = 1500;
	private  static int counter = 0;
	private NotificationManager notificationManager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notify_demo_layout);

		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notify_demo, menu);
		return true;
	}

	public void notifyMe(View view) {
		Notification note = new Notification(R.drawable.notify_status_bar_icon, "Message!", System.currentTimeMillis());
		PendingIntent i = PendingIntent.getActivity(this, 0, new Intent(this, SampleNotify.class), 0);
		note.setLatestEventInfo(this, "Notification Title", "This is the notification message", i);

		note.number = ++counter;
		note.vibrate = new long[] { 500L, 200L, 200L, 500L };
		note.flags |= Notification.FLAG_AUTO_CANCEL;

		notificationManager.notify(ID_NOTIFY_ME, note);
		BadgeController.setBadge(this, counter);
	}

	public void clearNotification(View view) {
		counter = 0;
		BadgeController.setBadge(this, 0);
	}



}
