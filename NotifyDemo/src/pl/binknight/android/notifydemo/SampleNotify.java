package pl.binknight.android.notifydemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author binKnight, 2014.11
 *
 */
public class SampleNotify extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setText("Sample text!!!");
		
		setContentView(textView);
		BadgeController.setBadge(this, 0);
	}
}
