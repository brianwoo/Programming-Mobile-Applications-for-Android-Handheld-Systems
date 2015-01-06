/**
 * 
 */
package com.bwoo.dailyselfie.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author bwoo
 *
 */
public class BitmapBuilder
{

	/**
	 * Build a Bitmap from the file passed in. 
	 * 
	 * @param file
	 * @return bitmap
	 * @throws IOException
	 */
	public Bitmap getBitmapFromFile(File file) throws IOException
	{
		FileInputStream fis = new FileInputStream(file);
		Bitmap bitmap = getBitmapFromFileInputStream(fis);
		
		return bitmap;
	}
	
	
	/**
	 * Build a Bitmap from the fileLocation passed in. 
	 * 
	 * @param fileLocation
	 * @return bitmap
	 * @throws IOException
	 */
	public Bitmap getBitmapFromFile(String fileLocation) throws IOException
	{
		FileInputStream fis = new FileInputStream(fileLocation);
		Bitmap bitmap = getBitmapFromFileInputStream(fis);
		
		return bitmap;
	}
	
	
	
	/**
	 * Private method that takes a FileInputStream and return a bitmap.  This method will
	 * close the FileInputStream so the calling method doesn't need to.
	 * 	  
	 * @param fis
	 * @return
	 * @throws IOException
	 */
	private Bitmap getBitmapFromFileInputStream(FileInputStream fis) throws IOException
	{
		try
		{
			BitmapFactory.Options options = new BitmapFactory.Options();
	    	options.inPreferredConfig = Bitmap.Config.ARGB_8888;
	    	Bitmap bitmap = BitmapFactory.decodeStream(fis, null, options);
	    	
	    	return bitmap;
		}
		finally
		{
			if (fis != null)
				fis.close();
		}
	}
		
	
	
	
	
}
