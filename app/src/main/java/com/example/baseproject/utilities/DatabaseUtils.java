package com.example.baseproject.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class DatabaseUtils {

	
	private String appName = "";
	  private String packageName = "";

/*	  public boolean backup() {
	    boolean rc = false;

	    boolean writeable = isSDCardWriteable();
	    if (writeable) {
	      File file = new File(Environment.getDataDirectory() + "/data/" + packageName + "/databases/" + MySQLiteOpenHelper.getDatabaseName());

	      File fileBackupDir = new File(Environment.getExternalStorageDirectory(), appName + "/backup");
	      if (!fileBackupDir.exists()) {
	        fileBackupDir.mkdirs();
	      }

	      if (file.exists()) {
	        File fileBackup = new File(fileBackupDir, MySQLiteOpenHelper.getDatabaseName());
	        try {
	          fileBackup.createNewFile();
	          FileUtils.copyFile(file, fileBackup);
	          rc = true;
	        } catch (IOException ioException) {
	          //
	        } catch (Exception exception) {
	          //
	        }
	      }
	    }

	    return rc;
	  }
*/
	  public boolean isSDCardWriteable() {
	    boolean rc = false;

	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	      rc = true;
	    }

	    return rc;
	  }

	    
	
		
	public void exportDB(String SAMPLE_DB_NAME, Context context){
		File sd = Environment.getExternalStorageDirectory();
	      	File data = Environment.getDataDirectory();
	       FileChannel source=null;
	       FileChannel destination=null;
	       String currentDBPath = "/data/"+ "com.authorwjf.sqliteexport" +"/databases/"+SAMPLE_DB_NAME;
	       String backupDBPath = SAMPLE_DB_NAME;
	       File currentDB = new File(data, currentDBPath);
	       File backupDB = new File(sd, backupDBPath);
	       try {
	            source = new FileInputStream(currentDB).getChannel();
	            destination = new FileOutputStream(backupDB).getChannel();
	            destination.transferFrom(source, 0, source.size());
	            source.close();
	            destination.close();
	            Toast.makeText(context, "DB Exported!", Toast.LENGTH_LONG).show();
	        } catch(IOException e) {
	        	e.printStackTrace();
	        }
	}

}
