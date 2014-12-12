package com.bwoo.modernartui;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private TextView tView1, tView2, tView3, tView4;
	private SeekBar sBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tView1 = (TextView) findViewById(R.id.textview1);
		tView2 = (TextView) findViewById(R.id.textview2);
		tView3 = (TextView) findViewById(R.id.textview3);
		tView4 = (TextView) findViewById(R.id.textview4);
		sBar = (SeekBar) findViewById(R.id.seekBar1);
		
		
		sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser)
			{
				// TODO Auto-generated method stub
				//Log.i(TAG, "Progress=" + progress);
				adjustViewBgColors(progress);
				
			}
		});
		
		
		
	}
	
	
	private void adjustViewBgColors(int color)
	{
		ColorDrawable currentColor = (ColorDrawable) tView1.getBackground();
		int alpha = currentColor.getAlpha();
		
		int newAlpha = alpha - color;
		Log.i(TAG, "alpha=" + newAlpha);
		
		currentColor.setAlpha(newAlpha);
		
		tView1.setBackground(currentColor);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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
}
