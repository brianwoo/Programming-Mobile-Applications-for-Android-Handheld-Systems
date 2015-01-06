/**
 * 
 */
package com.bwoo.dailyselfie;

import java.util.StringTokenizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author bwoo
 *
 */
public class SelfieListAdapter extends ArrayAdapter<ImageInfo>
{
	private Context mContext;
	
	
	public SelfieListAdapter(Context context, int resource)
	{
		super(context, resource);
		
		this.mContext = context;
	}

	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		
		ViewHolder vHolder;
		
		if (null == convertView)
		{
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.row_image, null);

			vHolder = new ViewHolder();
			
			vHolder.iconView = (ImageView) convertView.findViewById(R.id.icon);
			vHolder.filenameView = (TextView) convertView.findViewById(R.id.filename);

			convertView.setTag(vHolder);
		}
		else
		{
			vHolder = (ViewHolder) convertView.getTag();
		}
		
		ImageInfo imageInfo = getItem(position);
		vHolder.iconView.setImageBitmap(imageInfo.getThumbnail());
		
		String shortenedFileName = getShortenedFileName(imageInfo.getFileName());
		vHolder.filenameView.setText(shortenedFileName);
		
		vHolder.filename = imageInfo.getFileName();
		
		
		return convertView;
	}

	
	
	
	public class ViewHolder
	{
		private ImageView iconView;
		private TextView filenameView; 
		private String filename;
		
		
		public String getFilename()
		{
			return filename;
		}
		
	}
	
	
	
	private String getShortenedFileName(String originalFilename)
	{
		StringTokenizer tokenizer = new StringTokenizer(originalFilename, "_");
		
		String yearDateString = "";
		String timeString = "";
		
		if (tokenizer.hasMoreElements())
			yearDateString = tokenizer.nextToken();
		
		if (tokenizer.hasMoreElements())
			timeString = tokenizer.nextToken();
		
		return yearDateString + "_" + timeString;
	}
	
}
