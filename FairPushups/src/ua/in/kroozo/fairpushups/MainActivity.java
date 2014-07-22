package ua.in.kroozo.fairpushups;

import ua.in.kroozo.fairpushups.support.Sounds;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{

	
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private TextView tvNumberOfPushups;
	private int counter;
	private boolean fairCount;
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_1);
		
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		tvNumberOfPushups = (TextView) findViewById(R.id.textView1);
		
		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
			tvNumberOfPushups.setText("0");
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("count", counter);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		counter=savedInstanceState.getInt("count");
		tvNumberOfPushups.setText(counter+"");
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}


	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if(0==event.values[0]){
			fairCount = true;
			tvNumberOfPushups.setBackgroundResource(R.drawable.bgrnd_ring_count_near);
			Sounds.playSound(this);
		}
		else{
			if(true==fairCount){
				counter++;
				tvNumberOfPushups.setText(counter+"");
				tvNumberOfPushups.setBackgroundResource(R.drawable.bgrnd_ring_count_far);
				fairCount = false;
			}
			
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	/**
	 * A placeholder fragment containing a simple view.
	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_main, container,
//					false);
//			return rootView;
//		}
//	}

}
