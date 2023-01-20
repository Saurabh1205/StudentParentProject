package com.wordpro.studentproject.helper;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.wordpro.studentproject.R;

public class TransparentProgressDialog extends Dialog {
    @SuppressWarnings("unused")
    private ImageView iv;
    public  TransparentProgressDialog(Context context, int resourceIdOfImage) {
        super(context, R.style.TransparentProgressDialog);
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wlmp);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        setContentView(R.layout.progresslayout);
    }

    /**
     * Start the dialog and display it on screen.
     * @since 2014-07-28
     * @return none
     */
    @Override
    public void show() {
        try {
            super.show();
        }catch (Exception e){
          e.printStackTrace();
        }

    }

}
