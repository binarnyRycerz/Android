package pl.binknight.bettery.bassicinfo;

import java.util.ArrayList;
import java.util.List;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Updater implements Runnable {
	
	private BatteryBasicInfo batteryBasicInfo;
	private String batteryLevel = "Battery Level: ";
	private String voltage = "Voltage: ";
	private String batteryStatus = "Status: ";
	private List<String> listValues;
	

	
	public Updater(BatteryBasicInfo batteryBasicInfo) {
		this.batteryBasicInfo = batteryBasicInfo;
	}

	@Override
	public void run() {
		updateView();
	}
	
	private void updateView() {
		listValues = new ArrayList<String>();
		listValues.add(batteryLevel);
		listValues.add(voltage);
		listValues.add(batteryStatus);

		// initiate the listadapter
		ArrayAdapter<String> myAdapter = new ArrayAdapter <String>(batteryBasicInfo, 
				R.layout.battery_basic_info_layout, R.id.mainText, listValues);
 
         // assign the list adapter
		batteryBasicInfo.setListAdapter(myAdapter);
	}

	public BatteryBasicInfo getBatteryBasicInfo() {
		return batteryBasicInfo;
	}

	public void setBatteryBasicInfo(BatteryBasicInfo batteryBasicInfo) {
		this.batteryBasicInfo = batteryBasicInfo;
	}

	public String getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(String batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public String getBatteryStatus() {
		return batteryStatus;
	}

	public void setBatteryStatus(String batteryStatus) {
		this.batteryStatus = batteryStatus;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	
}
