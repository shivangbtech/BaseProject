package com.example.baseproject.helper;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Shivang Goel on 31/7/16.
 */
public class ImageOrientationHelper {

    private static ImageOrientationHelper mClassInstance;
    private final String TAG = "ImageOrientationHelper";

    private ImageOrientationHelper() {
    }

    public static ImageOrientationHelper getInstance() {
        if (mClassInstance == null)
            mClassInstance = new ImageOrientationHelper();
        return mClassInstance;
    }

    public String handleOrientation(final String sourcePath) {
        int rotate = 0;
        try {
            File imageFile = new File(sourcePath);
            ExifInterface exif = new ExifInterface(
                    imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, " : Rotate angle : " + rotate);
        if (rotate != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(rotate);
            Bitmap bitmap = BitmapFactory.decodeFile(sourcePath);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            File file = new File(sourcePath);
            if (file.exists())
                file.delete();
            try {
                FileOutputStream outStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                outStream.flush();
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sourcePath;
    }

    public int getChangeOrientation(Context context, Uri photoUri) {
    /* it's on the external media. */
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[]{MediaStore.Images.ImageColumns.ORIENTATION}, null, null, null);
        if (cursor.getCount() != 1) {
            return -1;
        }
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public String getFilePath(Context context, Intent data) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor returnCursor = context.getContentResolver().query(data.getData(), filePathColumn, null, null, null);
        if (returnCursor == null)
            return "";
        returnCursor.moveToFirst();
        int columnIndex = returnCursor.getColumnIndex(filePathColumn[0]);
        final String picturePath = returnCursor.getString(columnIndex);
        returnCursor.close();
        return picturePath;
    }
}
