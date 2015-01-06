package com.bwoo.dailyselfie;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bwoo.dailyselfie.SelfieListAdapter.ViewHolder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements ImageInfoReturnCallback
{
	protected static final String IMAGE_NAME = "imageName";
	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final String TAG = MainActivity.class.getName();
	
	
	private ListView mListView;
	private SelfieListAdapter mSelfieAdapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mSelfieAdapter = new SelfieListAdapter(this, R.layout.row_image);
		
		mListView = (ListView) findViewById(R.id.selfie_list);
		mListView.setAdapter(mSelfieAdapter);
		
		mListView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Log.i(TAG, "### item clicked!");
				
				ViewHolder vHolder = (ViewHolder) view.getTag();
				String filename = vHolder.getFilename();
				
				Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
				intent.putExtra(IMAGE_NAME, filename);
				startActivity(intent);
			}
		});
		
	}

		
	
	@Override
	protected void onStart()
	{
		super.onResume();
		fetchImagesFromFileSystem();
	}




	@Override
	protected void onResume()
	{
		super.onResume();
		
		// Cancel the alarm when we are in the app.
		SelfieAlarmBroadcastReceiver selfieAlarm = new SelfieAlarmBroadcastReceiver();
		selfieAlarm.cancelAlarm(this);
	}



	@Override
	protected void onStop()
	{
		// TODO Auto-generated method stub
		super.onStop();
		
		// setup repeating alarm while the app is stopped.
		SelfieAlarmBroadcastReceiver selfieAlarm = new SelfieAlarmBroadcastReceiver();
		selfieAlarm.setupAlarm(this);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			dispatchTakePictureIntent();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	


	private void dispatchTakePictureIntent() 
	{
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    // Ensure that there's a camera activity to handle the intent
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) 
	    {
	        // Create the File where the photo should go
	        File photoFile = null;
	        try 
	        {
	            photoFile = createImageFile();
	        } 
	        catch (IOException ex) 
	        {
	            // Error occurred while creating the File
	        	Toast toast = Toast.makeText(this, "Unable to create image", Toast.LENGTH_SHORT);
	        	toast.show();
	        }
	        
	        // Continue only if the File was successfully created
	        if (photoFile != null) 
	        {
	            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
	                    Uri.fromFile(photoFile));
	            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
	        }
	    }
	}
	
	

	private File createImageFile() throws IOException 
	{
	    // Create an image file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String imageFileName = timeStamp + "_";
	    File storageDir = Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_PICTURES);
	    File image = File.createTempFile(
	        imageFileName,  /* prefix */
	        ".jpg",         /* suffix */
	        storageDir      /* directory */
	    );

	    // Save a file: path for use with ACTION_VIEW intents
	    String mCurrentPhotoPath = "file:" + image.getAbsolutePath();
	    return image;

	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
	    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) 
	    {
	    	fetchImagesFromFileSystem();
	    }
	}
	
	
	
	private void fetchImagesFromFileSystem()
	{
    	FetchImagesAsyncTask fetchImagesTask = new FetchImagesAsyncTask(this);
    	fetchImagesTask.execute(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
	}
	
	
	
	@Override
	public void onFinishImageFetch(List<ImageInfo> imageInfoList)
	{
		// TODO Auto-generated method stub
		System.out.println("#### imageInfoList=" + imageInfoList.size());
		
		mSelfieAdapter.clear();
		
		// add the new list of images to adapter, then trigger the notification
		for (ImageInfo imageInfo : imageInfoList)
		{
			mSelfieAdapter.add(imageInfo);
		}
		
		mSelfieAdapter.notifyDataSetChanged();
		
	}
	
	
}
