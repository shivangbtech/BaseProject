package com.example.baseproject.utilities;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.example.baseproject.helper.ImageOrientationHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

public class FileUtils {

	/**
	 * Private Instance Variable
	 */
	private static FileUtils classInstance = null;
	private static final String TAG = "FileUtils";
	private static final boolean DEBUG = false; // Set to true to enable logging
	public static final String MIME_TYPE_AUDIO = "audio/*";
	public static final String MIME_TYPE_TEXT = "text/*";
	public static final String MIME_TYPE_IMAGE = "image/*";
	public static final String MIME_TYPE_VIDEO = "video/*";
	public static final String MIME_TYPE_APP = "application/*";
	public static final String HIDDEN_PREFIX = ".";

	/**
	 * Private Constructor to make this class singleton
	 */
	private FileUtils(){}

	/**
	 * Method return the class instance
	 * @return FileUtils
	 */
	public static FileUtils getInstance(){
		if(classInstance == null){
			classInstance = new FileUtils();
		}
		return classInstance;
	}

	/**
	 * Gets the extension of a file name, like ".png" or ".jpg".
	 *
	 * @param uri
	 * @return Extension including the dot("."); "" if there is no extension;
	 *         null if uri was null.
	 */
	public String getExtension(String uri) {
		if (uri == null) {
			return null;
		}

		int dot = uri.lastIndexOf(".");
		if (dot >= 0) {
			return uri.substring(dot);
		} else {
			// No extension.
			return "";
		}
	}

	/**
	 * @return Whether the URI is a local one.
	 */
	public boolean isLocal(String url) {
		if (url != null && !url.startsWith("http://") && !url.startsWith("https://")) {
			return true;
		}
		return false;
	}

	/**
	 * @return True if Uri is a MediaStore Uri.
	 * @author paulburke
	 */
	public boolean isMediaUri(Uri uri) {
		return "media".equalsIgnoreCase(uri.getAuthority());
	}

	/**
	 * Convert File into Uri.
	 *
	 * @param file
	 * @return uri
	 */
	public Uri getUri(File file) {
		if (file != null) {
			return Uri.fromFile(file);
		}
		return null;
	}

	/**
	 * Returns the path only (without file name).
	 *
	 * @param file
	 * @return
	 */
	public File getPathWithoutFilename(File file) {
		if (file != null) {
			if (file.isDirectory()) {
				// no file to be split off. Return everything
				return file;
			} else {
				String filename = file.getName();
				String filepath = file.getAbsolutePath();

				// Construct path without file name.
				String pathwithoutname = filepath.substring(0,
						filepath.length() - filename.length());
				if (pathwithoutname.endsWith("/")) {
					pathwithoutname = pathwithoutname.substring(0, pathwithoutname.length() - 1);
				}
				return new File(pathwithoutname);
			}
		}
		return null;
	}

	/**
	 * @return The MIME type for the given file.
	 */
	public String getMimeType(File file) {

		String extension = getExtension(file.getName());

		if (extension.length() > 0)
			return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.substring(1));

		return "application/octet-stream";
	}

	/**
	 * @return The MIME type for the give Uri.
	 */
	public String getMimeType(Context context, Uri uri) {
		File file = new File(getPath(context, uri));
		return getMimeType(file);
	}

	/**
	 * @param uri The Uri to check.
	 * @author paulburke
	 */
	public boolean isLocalStorageDocument(Uri uri) {
		return "com.ianhanniballake.localstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 * @author paulburke
	 */
	public boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 * @author paulburke
	 */
	public boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 * @author paulburke
	 */
	public boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri.getAuthority());
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 *
	 * @param context The context.
	 * @param uri The Uri to query.
	 * @param selection (Optional) Filter used in the query.
	 * @param selectionArgs (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 * @author paulburke
	 */
	public String getDataColumn(Context context, Uri uri, String selection,
									   String[] selectionArgs) {

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = {
				column
		};

		try {
			cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
					null);
			if (cursor != null && cursor.moveToFirst()) {
				if (DEBUG)
					android.database.DatabaseUtils.dumpCursor(cursor);

				final int column_index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(column_index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * Get a file path from a Uri. This will get the the path for Storage Access
	 * Framework Documents, as well as the _data field for the MediaStore and
	 * other file-based ContentProviders.<br>
	 * <br>
	 * Callers should check whether the path is local before assuming it
	 * represents a local file.
	 *
	 * @param context The context.
	 * @param uri The Uri to query.
	 * @see #isLocal(String)
	 * @see #getFile(Context, Uri)
	 * @author paulburke
	 */
	public String getPath(final Context context, final Uri uri) {

		if (DEBUG)
			Log.d(TAG + " File -",
					"Authority: " + uri.getAuthority() +
							", Fragment: " + uri.getFragment() +
							", Port: " + uri.getPort() +
							", Query: " + uri.getQuery() +
							", Scheme: " + uri.getScheme() +
							", Host: " + uri.getHost() +
							", Segments: " + uri.getPathSegments().toString()
			);

		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

		// DocumentProvider
		if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
			// LocalStorageProvider
			if (isLocalStorageDocument(uri)) {
				// The path is the id
				return DocumentsContract.getDocumentId(uri);
			}
			// ExternalStorageProvider
			else if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/" + split[1];
				}

				// TODO handle non-primary volumes
			}
			// DownloadsProvider
			else if (isDownloadsDocument(uri)) {

				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

				return getDataColumn(context, contentUri, null, null);
			}
			// MediaProvider
			else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}

				final String selection = "_id=?";
				final String[] selectionArgs = new String[] {
						split[1]
				};

				return getDataColumn(context, contentUri, selection, selectionArgs);
			}
		}
		// MediaStore (and general)
		else if ("content".equalsIgnoreCase(uri.getScheme())) {

			// Return the remote address
			if (isGooglePhotosUri(uri))
				return uri.getLastPathSegment();

			return getDataColumn(context, uri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}

		return null;
	}

	/**
	 * Convert Uri into File, if possible.
	 *
	 * @return file A local file that the Uri was pointing to, or null if the
	 *         Uri is unsupported or pointed to a remote resource.
	 * @see #getPath(Context, Uri)
	 * @author paulburke
	 */
	public File getFile(Context context, Uri uri) {
		if (uri != null) {
			String path = getPath(context, uri);
			if (path != null && isLocal(path)) {
				return new File(path);
			}
		}
		return null;
	}

	/**
	 * Get the file size in a human-readable string.
	 *
	 * @param size
	 * @return
	 * @author paulburke
	 */
	public String getReadableFileSize(int size) {
		final int BYTES_IN_KILOBYTES = 1024;
		final DecimalFormat dec = new DecimalFormat("###.#");
		final String KILOBYTES = " KB";
		final String MEGABYTES = " MB";
		final String GIGABYTES = " GB";
		float fileSize = 0;
		String suffix = KILOBYTES;

		if (size > BYTES_IN_KILOBYTES) {
			fileSize = size / BYTES_IN_KILOBYTES;
			if (fileSize > BYTES_IN_KILOBYTES) {
				fileSize = fileSize / BYTES_IN_KILOBYTES;
				if (fileSize > BYTES_IN_KILOBYTES) {
					fileSize = fileSize / BYTES_IN_KILOBYTES;
					suffix = GIGABYTES;
				} else {
					suffix = MEGABYTES;
				}
			}
		}
		return String.valueOf(dec.format(fileSize) + suffix);
	}

	/**
	 * Attempt to retrieve the thumbnail of given File from the MediaStore. This
	 * should not be called on the UI thread.
	 *
	 * @param context
	 * @param file
	 * @return
	 * @author paulburke
	 */
	public Bitmap getThumbnail(Context context, File file) {
		return getThumbnail(context, getUri(file), getMimeType(file));
	}

	/**
	 * Attempt to retrieve the thumbnail of given Uri from the MediaStore. This
	 * should not be called on the UI thread.
	 *
	 * @param context
	 * @param uri
	 * @return
	 * @author paulburke
	 */
	public Bitmap getThumbnail(Context context, Uri uri) {
		return getThumbnail(context, uri, getMimeType(context, uri));
	}

	/**
	 * Attempt to retrieve the thumbnail of given Uri from the MediaStore. This
	 * should not be called on the UI thread.
	 *
	 * @param context
	 * @param uri
	 * @param mimeType
	 * @return
	 * @author paulburke
	 */
	public Bitmap getThumbnail(Context context, Uri uri, String mimeType) {
		if (DEBUG)
			Log.d(TAG, "Attempting to get thumbnail");

		if (!isMediaUri(uri)) {
			Log.e(TAG, "You can only retrieve thumbnails for images and videos.");
			return null;
		}

		Bitmap bm = null;
		if (uri != null) {
			final ContentResolver resolver = context.getContentResolver();
			Cursor cursor = null;
			try {
				cursor = resolver.query(uri, null, null, null, null);
				if (cursor.moveToFirst()) {
					final int id = cursor.getInt(0);
					if (DEBUG)
						Log.d(TAG, "Got thumb ID: " + id);

					if (mimeType.contains("video")) {
						bm = MediaStore.Video.Thumbnails.getThumbnail(
								resolver,
								id,
								MediaStore.Video.Thumbnails.MINI_KIND,
								null);
					}
					else if (mimeType.contains(FileUtils.MIME_TYPE_IMAGE)) {
						bm = MediaStore.Images.Thumbnails.getThumbnail(
								resolver,
								id,
								MediaStore.Images.Thumbnails.MINI_KIND,
								null);
					}
				}
			} catch (Exception e) {
				if (DEBUG)
					Log.e(TAG, "getThumbnail", e);
			} finally {
				if (cursor != null)
					cursor.close();
			}
		}
		return bm;
	}

	/**
	 * File and folder comparator. TODO Expose sorting option method
	 *
	 * @author paulburke
	 */
	public Comparator<File> sComparator = new Comparator<File>() {
		@Override
		public int compare(File f1, File f2) {
			// Sort alphabetically by lower case, which is much cleaner
			return f1.getName().toLowerCase().compareTo(f2.getName().toLowerCase());
		}
	};

	/**
	 * File (not directories) filter.
	 *
	 * @author paulburke
	 */
	public FileFilter sFileFilter = new FileFilter() {
		@Override
		public boolean accept(File file) {
			final String fileName = file.getName();
			// Return files only (not directories) and skip hidden files
			return file.isFile() && !fileName.startsWith(HIDDEN_PREFIX);
		}
	};

	/**
	 * Folder (directories) filter.
	 *
	 * @author paulburke
	 */
	public FileFilter sDirFilter = new FileFilter() {
		@Override
		public boolean accept(File file) {
			final String fileName = file.getName();
			// Return directories only and skip hidden directories
			return file.isDirectory() && !fileName.startsWith(HIDDEN_PREFIX);
		}
	};

	/**
	 * Get the Intent for selecting content to be used in an Intent Chooser.
	 *
	 * @return The intent for opening a file with Intent.createChooser()
	 * @author paulburke
	 */
	public Intent createGetContentIntent() {
		// Implicitly allow the user to select a particular kind of data
		final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		// The MIME data type filter
		intent.setType("*/*");
		// Only return URIs that can be opened with ContentResolver
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		return intent;
	}

	/**
	 * Method call to get file from Assets folder
	 * @param fileName
	 * @param inputStream
	 * @return
	 */

	/**
	 * Use in code to work
	 *  AssetManager am = getAssets();
	 InputStream inputStream = am.open("myfoldername/myfilename");
	 File file = createFileFromInputStream(inputStream);
	 */
	public File createFileFromInputStream(String fileName, InputStream inputStream) {

	   try{
	      File f = new File(fileName);
	      OutputStream outputStream = new FileOutputStream(f);
	      byte buffer[] = new byte[1024];
	      int length = 0;

	      while((length=inputStream.read(buffer)) > 0) {
	        outputStream.write(buffer,0,length);
	      }

	      outputStream.close();
	      inputStream.close();

	      return f;
	   }catch (IOException e) {
		   e.printStackTrace();
		   Log.d(TAG, "", e);
	   }

	   return null;
	}

	/**
	 * Method used to create file to take photo by camera
	 * @param context
	 * @return
	 * @throws IOException
     */
	public File createImageFile(Context context) throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		File imageFile = File.createTempFile(
				imageFileName,  /* prefix */
				".jpg",         /* suffix */
				storageDir      /* directory */
		);

		// Save a file: path for use with ACTION_VIEW intents
		return imageFile;
	}


	private Bitmap displayImageFromGallery(Context context, Uri selectedImage) throws IOException {
		InputStream is = context.getContentResolver().openInputStream(selectedImage);
		BitmapFactory.Options dbo = new BitmapFactory.Options();
		dbo.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(is, null, dbo);
		is.close();

		int rotatedWidth, rotatedHeight;
		int orientation = ImageOrientationHelper.getInstance().getChangeOrientation(context, selectedImage);

		if (orientation == 90 || orientation == 270) {
			rotatedWidth = dbo.outHeight;
			rotatedHeight = dbo.outWidth;
		} else {
			rotatedWidth = dbo.outWidth;
			rotatedHeight = dbo.outHeight;
		}

		Bitmap srcBitmap;
		is = context.getContentResolver().openInputStream(selectedImage);
		if (rotatedWidth > 512 || rotatedHeight > 512) {
			float widthRatio = ((float) rotatedWidth) / ((float) 512);
			float heightRatio = ((float) rotatedHeight) / ((float) 512);
			float maxRatio = Math.max(widthRatio, heightRatio);

			// Create the bitmap from file
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = (int) maxRatio;
			srcBitmap = BitmapFactory.decodeStream(is, null, options);
		} else {
			srcBitmap = BitmapFactory.decodeStream(is);
		}
		is.close();

    /*
     * if the orientation is not 0 (or -1, which means we don't know), we
     * have to do a rotation.
     */
		if (orientation > 0) {
			Matrix matrix = new Matrix();
			matrix.postRotate(orientation);
			srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);
		}
		return srcBitmap;
	}













	/**
	 * Methos call to save Image in internal storage
	 * @param context
	 * @param image
	 * @param imageName
	 * @return boolean
	 */
	public boolean saveImageToInternalStorage(Context context, Bitmap image, String imageName) {
		try {
			// Use the compress method on the Bitmap object to write image to
			// the OutputStream
			FileOutputStream fos = context.openFileOutput(imageName + ".png", Context.MODE_PRIVATE);

			// Writing the bitmap to the output stream
			image.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();

			return true;
		} catch (Exception e) {
			Logger.getInstance().log_info("Exception in save image to internal storage...", e.getMessage());
			return false;
		}
	}

	/**
	 * Method call to check is SD card readable
	 * @return boolean
	 */
	public boolean isSdReadable() {

		boolean mExternalStorageAvailable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// We can read and write the media
			mExternalStorageAvailable = true;
			Logger.getInstance().log_info("External storage card is readable.");
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			// We can only read the media
			Logger.getInstance().log_info("External storage card is readable.");
			mExternalStorageAvailable = true;
		} else {
			// Something else is wrong. It may be one of many other
			// states, but all we need to know is we can neither read nor write
			mExternalStorageAvailable = false;
		}

		return mExternalStorageAvailable;
	}



	/**
	 * Method call to get image from internal storage
	 * @param path
	 * @return Bitmap
	 */
	public Bitmap loadImageFromStorage(String path) {
		Bitmap b = null;
		try {
			File f=new File(path, "profile.jpg");
			b = BitmapFactory.decodeStream(new FileInputStream(f));

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return b;

	}


	public void saveBitmapToSdcard(Bitmap bitmap){

		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/saved_images");
		myDir.mkdirs();
		Random generator = new Random();
		int n = 10000;
		n = generator.nextInt(n);
		String fname = "Image-"+ n +".jpg";
		File file = new File (myDir, fname);
		if (file.exists ()) file.delete ();
		try {
			FileOutputStream out = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String readFileTextFromSDCard(String path) {
		File directory = Environment.getExternalStorageDirectory();
		// Assumes that a file article.rss is available on the SD card
		//	        File file = new File(directory + "/article.rss");
		File file = new File(directory + path);
		if (!file.exists()) {
			throw new RuntimeException("File not found");
		}
		Log.e("Testing", "Starting to read");
		BufferedReader reader = null;
		StringBuilder builder = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			builder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return(builder.toString());
	}

	public byte[] readFileFromSDCard(String path) {
		File file = new File(path);

		byte[] b = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(b);
			//		for (int i = 0; i < b.length; i++) {
			//			System.out.print((char)b[i]);
			//		}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found."+ e.getMessage());
			e.printStackTrace();
		}
		catch (IOException e1) {
			System.out.println("Error Reading The File."+ e1.getMessage());
			e1.printStackTrace();
		}
		return b;
	}


	public File byteArraytoFile(byte[] array, String fileType){
		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/saved_images");
		myDir.mkdirs();
		Random generator = new Random();
		int n = 10000;
		n = generator.nextInt(n);
		String fname = "Image-"+ n +"." + fileType;
		File file = new File (myDir, fname);
		if (file.exists ()) file.delete ();
		try {
			FileOutputStream fos = new FileOutputStream(file);
			//              String strContent = "Write File using Java ";

			fos.write(array);
			fos.close();
		}
		catch(FileNotFoundException ex)   {
			System.out.println("FileNotFoundException : " + ex);
		}
		catch(IOException ioe)  {
			System.out.println("IOException : " + ioe);
		}
		return file;

	}

	/**
	 * Method call to clean object from memory
	 */
	public void cleanObject(){
		classInstance = null;
	}

}
