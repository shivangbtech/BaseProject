package com.example.baseproject.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class EmailUtils {

	private final String TAG = getClass().getSimpleName();
	
	public void sendEmail(Context context, String subject, String bodyText, String attachmentFileName){
		Log.i(TAG, "Sending email");
		copyFromAsset(context, attachmentFileName);
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setData(Uri.parse("mailto:"));
		emailIntent.setType("text/jpg");
//		emailIntent.setType("message/rfc822");
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT, bodyText);
		emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + context.getFilesDir() + "/"+ attachmentFileName));
		try {
			context.startActivity(Intent.createChooser(emailIntent, "Pick an Email provider..."));
			
			Log.i(TAG, "Finished sending email");
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(context, "There is no email client installed.", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void sendEmail(Context context, String[] to, String subject, String bodyText, String attachmentFileName){
		Log.i(TAG, "Sending email");
		copyFromAsset(context, attachmentFileName);
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setData(Uri.parse("mailto:"));
		emailIntent.setType("text/jpg");
//		emailIntent.setType("message/rfc822");
		emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT, bodyText);
		emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + context.getFilesDir() + "/"+ attachmentFileName));
		try {
			context.startActivity(Intent.createChooser(emailIntent, "Pick an Email provider..."));

			Log.i(TAG, "Finished sending email");
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(context, "There is no email client installed.", Toast.LENGTH_SHORT).show();
		}
	}

	private void copyFromAsset(Context context, String attachmentFileName){
		AssetManager assetManager = context.getAssets();
		InputStream in = null;
		OutputStream out = null;
		File file = new File(context.getFilesDir(), attachmentFileName);
		try  {
			in = assetManager.open(attachmentFileName);
			out = context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e){
			Log.e("tag", e.getMessage());
		}

	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1){
			out.write(buffer, 0, read);
		}
	}
}
