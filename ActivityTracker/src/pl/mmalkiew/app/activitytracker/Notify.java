package pl.mmalkiew.app.activitytracker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Notify extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setText("Sample text!!!");
		
		setContentView(textView);
	}
}
