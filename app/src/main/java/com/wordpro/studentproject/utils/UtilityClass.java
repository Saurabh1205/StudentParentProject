package com.wordpro.studentproject.utils;

import android.content.Context;

import com.wordpro.studentproject.helper.TransparentProgressDialog;

public class UtilityClass {
    Context context;
    TransparentProgressDialog progressDialogObj;

    public UtilityClass(Context contextobj){
        context =contextobj;
    }

    public void startLoader(Context context, int image_for_rotation_resource_id) {
        progressDialogObj = new TransparentProgressDialog(context, image_for_rotation_resource_id);
        progressDialogObj.show();
    }

    public void stopLoader() {
        try{
            progressDialogObj.dismiss();
        }catch (NullPointerException e){
            //  Log.d("progessDialog",progressDialogObj.toString());
        }
    }
    public void finish() {
        if(progressDialogObj != null) {
            progressDialogObj.dismiss();
        }

    }
}
