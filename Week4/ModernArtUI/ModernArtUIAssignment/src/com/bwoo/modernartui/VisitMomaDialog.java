/**
 * 
 */
package com.bwoo.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * @author bwoo
 *
 */
public class VisitMomaDialog extends DialogFragment 
{

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.dialogTitleMessage)
			.setMessage(R.string.dialogMessage);
		
		builder.setPositiveButton(R.string.dialogPosButton, new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.moma.org"));
				startActivity(intent);
			}
		});
		
		
		builder.setNegativeButton(R.string.dialogNegButton, new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// do nothing.
			}
		});
		
		return builder.create();
	}

}
