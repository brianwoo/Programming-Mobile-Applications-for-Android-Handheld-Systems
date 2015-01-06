package com.bwoo.dailyselfie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.bwoo.dailyselfie.utils.BitmapBuilder;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;

public class ImageActivity extends Activity
{
		

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);

		
		System.out.println("########3 onCreate 1");
		
		if (savedInstanceState == null)
		{
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		System.out.println("########3 onCreate 2");
		
		//getIntent().getExtras()
	}

	
	
	
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image, menu);
		return true;
	}
	*/

	/*
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	*/

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment
	{
		private ImageView mShowImageView;
		
		
		public PlaceholderFragment()
		{
		}

		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_image,
					container, false);
			
			System.out.println("########3 onCreateView");
			
			return rootView;
		}

		
		@Override
		public void onActivityCreated(Bundle savedInstanceState)
		{
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			
			mShowImageView = (ImageView) getActivity().findViewById(R.id.show_image);
			
			System.out.println("########3 onActivityCreated");
			
			String imageName = getActivity().getIntent().getStringExtra(MainActivity.IMAGE_NAME);
			
			File picsDir = 
					Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			String picsDirPath = picsDir.getAbsolutePath();
			
			String imageFile = picsDirPath + "/" + imageName;			
			
			try
			{
				BitmapBuilder bitmapBuilder = new BitmapBuilder();
				Bitmap bitmap = bitmapBuilder.getBitmapFromFile(imageFile);
				
				mShowImageView.setImageBitmap(bitmap);
			}
			catch (IOException e1)
			{
				Toast toast = Toast.makeText(getActivity(), 
						"Unable to open bitmap file", Toast.LENGTH_SHORT);
				toast.show();
			}
		}

		
	}

}
