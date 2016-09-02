package com.example.baseproject.fragments.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.baseproject.R;

public class AddPhotoDialogFragment extends DialogFragment {

    private boolean onEditMode;
    private OnAddPhotoListener mListener;

    public interface OnAddPhotoListener {
        public void selectFromGallery();
        public void takeNewPhoto();
    }

    public void setListener(OnAddPhotoListener mListener) {
        this.mListener = mListener;
    }


    public void setOnEditMode(boolean onEditMode) {
        this.onEditMode = onEditMode;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int arraySet = (onEditMode)? R.array.add_photo_edit: R.array.add_photo;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.label_dialog_add_photo_title)
                .setItems(arraySet, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: //select from gallery
                                mListener.selectFromGallery();
                                break;
                            case 1: //take new photo
                                mListener.takeNewPhoto();
                                break;
                        }
                    }
                });

        return builder.create();
    }
}
