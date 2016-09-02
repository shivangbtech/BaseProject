package com.example.baseproject.managers;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.baseproject.R;
import com.example.baseproject.fragments.utility.AddPhotoDialogFragment;
import com.example.baseproject.listeners.PermissionErrorListener;
import com.example.baseproject.models.event.MessageEvent;
import com.example.baseproject.utilities.FileUtils;
import com.example.baseproject.utilities.PermissionUtils;
import com.example.baseproject.utilities.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * Created by Shivang Goel on 15/6/16.
 */
public class FilePickerManager {

    private final String TAG = "FilePickerManager";
    private Fragment mFragment;
    private Activity mActivity;
    public static int REQUEST_CODE_EVENT_BUS_FILE_PATH = 1001;
    private final int REQUEST_CODE_FILE_SELECT = 1;
    private final int REQUEST_CODE_SELECT_FROM_GALLERY = 2;
    private final int REQUEST_CODE_TAKE_PHOTO = 3;
    private final int REQUEST_CODE_FILE_PICKER_PERMIT = 101;
    private final int REQUEST_CODE_PHOTO_PICKER_PERMIT = 102;

    public FilePickerManager(Activity activity) {
        this.mActivity = activity;
    }

    public FilePickerManager(Fragment fragment) {
        this.mFragment = fragment;
        this.mActivity = fragment.getActivity();
    }

    public void showFileChooser() {
        PermissionUtils.getInstance().needPermission(mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_FILE_PICKER_PERMIT,
                new String[]{"Write on Storage", "Read Storage"}, new PermissionErrorListener() {
                    @Override
                    public void isAlreadyGranted() {
                        initFileChooser();
                    }

                    @Override
                    public void BeforeMarshmallow() {
                        initFileChooser();
                    }

                    @Override
                    public void onError(String errorMessage) {
                        ToastUtils.getInstance().showToastShort(mActivity, R.string.validation_permission_required);
                    }
                });
    }

    private void initFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            if (mFragment != null)
                mFragment.startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), REQUEST_CODE_FILE_SELECT);
            else
                mActivity.startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), REQUEST_CODE_FILE_SELECT);
        } catch (android.content.ActivityNotFoundException ex) {
            Log.d(TAG, " : initFileChooser : ", ex);
            ToastUtils.getInstance().showToastShort(mFragment.getActivity(), R.string.validation_file_picker_not_available);
        }
    }

    public void showPhotoChooser() {
        PermissionUtils.getInstance().needPermission(mActivity, new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_PHOTO_PICKER_PERMIT, new String[]{"Camera", "Write on Storage", "Read Storage"}, new PermissionErrorListener() {
                    @Override
                    public void isAlreadyGranted() {
                        initPhotoChooser();
                    }

                    @Override
                    public void BeforeMarshmallow() {
                        initPhotoChooser();
                    }

                    @Override
                    public void onError(String errorMessage) {
                        ToastUtils.getInstance().showToastShort(mActivity, R.string.validation_permission_required);
                    }
                });
    }


    public void initPhotoChooser() {
        AddPhotoDialogFragment frag = new AddPhotoDialogFragment();
        frag.setListener(mOnAddPhotoListener);
        frag.show(mActivity.getFragmentManager(), "tag_photo");
    }

    private AddPhotoDialogFragment.OnAddPhotoListener mOnAddPhotoListener =
            new AddPhotoDialogFragment.OnAddPhotoListener() {
                @Override
                public void selectFromGallery() {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    if (mFragment != null)
                        mFragment.startActivityForResult(intent, REQUEST_CODE_SELECT_FROM_GALLERY);
                    else if (mActivity != null)
                        mActivity.startActivityForResult(intent, REQUEST_CODE_SELECT_FROM_GALLERY);
                }

                @Override
                public void takeNewPhoto() {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
                        File photoFile = null;
                        try {
                            photoFile = FileUtils.getInstance().createImageFile(mActivity);
                            if (photoFile != null) {
                                Uri mPhotoUri = Uri.fromFile(photoFile);
                                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri);
                                if (mFragment != null)
                                    mFragment.startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
                                else if (mActivity != null)
                                    mActivity.startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == mActivity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FILE_SELECT:
                    Uri uri = data.getData();
                    Log.d(TAG, " : File Uri: " + uri.toString());
                    EventBus.getDefault().post(new MessageEvent(REQUEST_CODE_EVENT_BUS_FILE_PATH, MessageEvent.STATUS_SUCCESS, uri.toString()));
                    break;

                case REQUEST_CODE_SELECT_FROM_GALLERY:
                    showLoader();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA, MediaStore.Images.ImageColumns.ORIENTATION};
                    Cursor returnCursor = mActivity.getContentResolver().query(data.getData(), filePathColumn, null, null, null);
                    if (returnCursor == null)
                        return;
                    returnCursor.moveToFirst();

                    int columnIndex = returnCursor.getColumnIndex(filePathColumn[0]);
                    final String picturePath = returnCursor.getString(columnIndex);

                    int orientation = returnCursor.getInt(1);
                    returnCursor.close();

                    new Thread() {
                        @Override
                        public void run() {
                            super.run();

                            String compressImgPath = "";
                            try {
                                compressImgPath = ImageUtils.getInstance().saveToAppStorage(mActivity, displayImageFromGallery(data.getData()), fileName);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
//                            String pathToSend = handleOrientationOnUIThread(compressImgPath);
                            updateHandlerFromThread(compressImgPath);
                        }
                    }.start();

                    break;

                case REQUEST_CODE_TAKE_PHOTO:
                    showLoader();
                    // scale down
                    if (mPhotoUri != null) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(mPhotoUri.getPath(), options);
//                        showLoader();
                        options.inSampleSize = BitmapUtils.calculateInSampleSize(options, 384, 384);
                        options.inJustDecodeBounds = false;
                        mImagePath = mPhotoUri.getPath();
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                String pathToSend = handleOrientationOnUIThread(mImagePath);
                                updateHandlerFromThread(pathToSend);
                            }
                        }.start();
                    }
                    break;
            }


        }
    }

    public void onPermissionResult() {

    }
}
