/**
 * 
 */
package com.bwoo.dailyselfie;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import com.bwoo.dailyselfie.utils.BitmapBuilder;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

/**
 * @author bwoo
 *
 */
public class FetchImagesAsyncTask extends AsyncTask<File, Integer, List<ImageInfo>>
{
	private static final int THUMBNAIL_SIZE = 36;
	private ImageInfoReturnCallback finishedImageFetchCallback;
	private static final String TAG = FetchImagesAsyncTask.class.getName();
	
	public FetchImagesAsyncTask(ImageInfoReturnCallback callback)
	{
		this.finishedImageFetchCallback = callback;
	}
	
	
	@Override
	protected List<ImageInfo> doInBackground(File... params)
	{
		if (null == params)
			throw new IllegalArgumentException("no directory specified in parameter");
		
	    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

	    
	    
	    // Setup filename filter to only fetch jpg files
	    File[] imageFiles = path.listFiles(new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String filename)
			{
				if (filename.endsWith(".jpg"))
					return true;
				
				return false;
			}
		});
	    
	    List<ImageInfo> imageInfoList = new ArrayList<ImageInfo>();
	    
	    for (File imageFile : imageFiles)
	    {
	    	
	    	try
			{
	    		BitmapBuilder bitmapBuilder = new BitmapBuilder();
	    		Bitmap bitmap = bitmapBuilder.getBitmapFromFile(imageFile);
			    
	            Bitmap thumbnailBitmap = 
	            		Bitmap.createScaledBitmap(bitmap, THUMBNAIL_SIZE, THUMBNAIL_SIZE, false);

	            ImageInfo imageInfo = new ImageInfo();
	            imageInfo.setThumbnail(thumbnailBitmap);
	            imageInfo.setFileName(imageFile.getName());
	            
	            imageInfoList.add(imageInfo);
		    	
			}
			catch (Exception e1)
			{
				Log.e(TAG, "BAD image file: " + imageFile.getName());
			}

	    }
		
		return imageInfoList;
	}


	
	@Override
	protected void onPostExecute(List<ImageInfo> result)
	{
		//super.onPostExecute(result);
		
		finishedImageFetchCallback.onFinishImageFetch(result);
		
	}
	
	
	

}
