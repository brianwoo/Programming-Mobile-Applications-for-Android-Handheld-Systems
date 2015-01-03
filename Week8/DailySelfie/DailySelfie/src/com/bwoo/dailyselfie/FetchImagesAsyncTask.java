/**
 * 
 */
package com.bwoo.dailyselfie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

	    	FileInputStream fis = null;
	    	
	    	try
			{
				fis = new FileInputStream(imageFile);
		    	
				BitmapFactory.Options options = new BitmapFactory.Options();
		    	options.inPreferredConfig = Bitmap.Config.ARGB_8888;
				//options.inJustDecodeBounds = true;
		    	Bitmap bitmap = BitmapFactory.decodeStream(fis, null, options);
		    	//int photoW = options.outWidth;
		    	//int photoH = options.outHeight;
		    	
		    	
		    	// Determine how much to scale down the image
		        //int scaleFactor = Math.min(photoW/THUMBNAIL_SIZE, photoH/THUMBNAIL_SIZE);

		        // Decode the image file into a Bitmap sized to fill the View
		        //options.inJustDecodeBounds = false;
		        //options.inSampleSize = scaleFactor;
		        //options.inPurgeable = true;

		        //Bitmap thumbnailBitmap = BitmapFactory.decodeStream(fis, null, options);
		        
		        
		    	
			    //System.out.println("#### imageFile exists? = " + imageFile.exists());
			    //System.out.println("imageFile abs path = " + bitmap);
			    
	            Bitmap thumbnailBitmap = Bitmap.createScaledBitmap(bitmap, THUMBNAIL_SIZE, THUMBNAIL_SIZE, false);

	            ImageInfo imageInfo = new ImageInfo();
	            imageInfo.setThumbnail(thumbnailBitmap);
	            imageInfo.setFileName(imageFile.getName());
	            
	            imageInfoList.add(imageInfo);
		    	
			}
			catch (Exception e1)
			{
				Log.e(TAG, "BAD image file: " + imageFile.getName());
			}
	    	finally
	    	{
				try
				{
		    		if (fis != null)
		    			fis.close();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
