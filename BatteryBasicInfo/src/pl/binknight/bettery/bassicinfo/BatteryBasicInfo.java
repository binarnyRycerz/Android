package pl.binknight.bettery.bassicinfo;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class BatteryBasicInfo extends ListActivity {
	
	private int voltage = 0;
	private boolean run = true;
	private Handler handler = new Handler();
	private Thread worker;
	private Updater updater;
	private TextView text;
	
	public  BatteryBasicInfo() {
		updater = new Updater(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battery_basic_info_layout);
		text = (TextView) findViewById(R.id.mainText);

		
		startMonitor();
	}
	
	private void startMonitor() {
		worker = new Thread() {
			@Override
			public void run() {
				do {
					//	aktualizacja statusu baterri
					batteryLevelUpdate();
					handler.post(updater);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
				while(run);
			}
		};
		worker.start();
	}
	
	@Override  
	   public void onDestroy() {  
	        run = false;  
	        super.onDestroy();  
	   } 
	
	private void batteryLevelUpdate() {  
	     BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {  
	       public void onReceive(Context context, Intent intent) {  
	            context.unregisterReceiver(this);  
	         int rawlevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);  
	         int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);  
	         int level = -1;  
	         if (rawlevel >= 0 && scale > 0) {  
	           level = (rawlevel * 100) / scale;  
	         }  
	         voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);  
	         int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);  
	         int onplug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);  
	         boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;  
	         boolean onUSB = onplug == BatteryManager.BATTERY_PLUGGED_USB;  
	         boolean onAC = onplug == BatteryManager.BATTERY_PLUGGED_AC;  
	         String strStatus = "Charging on ";  
	         if (isCharging && onUSB)  
	              strStatus += "USB";  
	         else if (isCharging && onAC)  
	              strStatus += "AC Power";  
	         else  
	              strStatus = "Battery Discharging";  
	         updater.setBatteryLevel("Battery Level: " + Integer.toString(level) + "%");  
	         updater.setVoltage("Voltage: " + Integer.toString(voltage) + "mV");  
	         updater.setBatteryStatus(strStatus);  
	       }  
	     };  
	     IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);  
	     registerReceiver(batteryLevelReceiver, batteryLevelFilter);  
	   } 
	
	// when an item of the list is clicked
	@Override
	protected void onListItemClick(ListView list, View view, int position, long id) {
		super.onListItemClick(list, view, position, id);
   
		String selectedItem = (String) getListView().getItemAtPosition(position);
		//String selectedItem = (String) getListAdapter().getItem(position);
		
		text.setText("You clicked " + selectedItem + " at position " + position); 
	}
}
