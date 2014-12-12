package com.bwoo.modernartui;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
	private ColorDrawable tView1Color, tView2Color, tView3Color, tView4Color;
	private int currentProgress = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tView1 = (TextView) findViewById(R.id.textview1);
		tView2 = (TextView) findViewById(R.id.textview2);
		tView3 = (TextView) findViewById(R.id.textview3);
		tView4 = (TextView) findViewById(R.id.textview4);
		sBar = (SeekBar) findViewById(R.id.seekBar1);
		
		tView1Color = (ColorDrawable) tView1.getBackground().getCurrent();
		tView2Color = (ColorDrawable) tView2.getBackground().getCurrent();
		tView3Color = (ColorDrawable) tView3.getBackground().getCurrent();
		tView4Color = (ColorDrawable) tView4.getBackground().getCurrent();
		
		sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser)
			{
				int change = progress - currentProgress;
					
				currentProgress = progress;
				
				Log.i(TAG, "change=" + change);
				adjustViewBgColors(change);
				
			}
		});
		
		
		
	}
	
	
	private void adjustViewBgColors(int degOfIncrement)
	{
		tView1Color = (ColorDrawable) tView1.getBackground().getCurrent();
		tView2Color = (ColorDrawable) tView2.getBackground().getCurrent();
		tView3Color = (ColorDrawable) tView3.getBackground().getCurrent();
		tView4Color = (ColorDrawable) tView4.getBackground().getCurrent();
		
		
		Drawable newColor1 = adjustColor(tView1Color, degOfIncrement);
		Drawable newColor2 = adjustColor(tView2Color, degOfIncrement);
		Drawable newColor3 = adjustColor(tView3Color, degOfIncrement);
		Drawable newColor4 = adjustColor(tView4Color, degOfIncrement);

		tView1.setBackground(newColor1);
		tView2.setBackground(newColor2);
		tView3.setBackground(newColor3);
		tView4.setBackground(newColor4);
		
	}
	
	
	/**
	 * Increment the RGB values of the existingColor.
	 *  
	 * @param existingColor
	 * @param degOfIncrement
	 * @return
	 */
	private Drawable adjustColor(ColorDrawable existingColor, int degOfIncrement)
	{
		int oldColor = existingColor.getColor();
		
		int red = (oldColor >> 16) & 0xFF;
		int green = (oldColor >> 8) & 0xFF;
		int blue = (oldColor >> 0) & 0xFF;
		
		if ((degOfIncrement > 0 && red < 255) || (degOfIncrement < 0 && red > 0)) 
			red += degOfIncrement;
		
		if ((degOfIncrement > 0 && green < 255) || (degOfIncrement < 0 && green > 0))
			green += degOfIncrement;
		
		if ((degOfIncrement > 0 && blue < 255) || (degOfIncrement < 0 && blue > 0))
			blue += degOfIncrement;
		
		//Log.i(TAG, "rgb=" + red + "," + green + "," + blue);
		
		ColorDrawable newColor = new ColorDrawable(Color.rgb(red, green, blue));
		
		return newColor;
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
			
			DialogFragment dialog = new VisitMomaDialog();
			dialog.show(getFragmentManager(), "visit");
			
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
