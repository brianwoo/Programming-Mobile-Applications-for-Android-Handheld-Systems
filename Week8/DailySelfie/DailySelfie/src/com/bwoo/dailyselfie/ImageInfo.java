/**
 * 
 */
package com.bwoo.dailyselfie;

import android.graphics.Bitmap;

/**
 * @author bwoo
 *
 */
public class ImageInfo
{
	private Bitmap thumbnail;
	private String fileName;
	
	
	public Bitmap getThumbnail()
	{
		return thumbnail;
	}
	public void setThumbnail(Bitmap thumbnail)
	{
		this.thumbnail = thumbnail;
	}
	public String getFileName()
	{
		return fileName;
	}
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	
}
