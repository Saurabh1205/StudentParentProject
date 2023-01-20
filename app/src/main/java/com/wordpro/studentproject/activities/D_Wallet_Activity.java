package com.wordpro.studentproject.activities;


import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wordpro.studentproject.R;

import com.wordpro.studentproject.fragment.D_Wallet_DownloadFragment;
import com.wordpro.studentproject.fragment.D_Wallet_UploadDocFragment;
import com.wordpro.studentproject.fragment.D_Wallet_Upload_DetailsFragment;

public class D_Wallet_Activity extends AppCompatActivity {
    private TextView txt_toolbarName;
    private CardView card_view_download,card_view_Upload;
    private ImageView back_press,setting_option,img_download,img_upload;
    private LinearLayout downloadView,lay_download,lay_upload,view;
    private TextView txt_upload,txt_download;
    private  Fragment fragment;
    PopupWindow mpopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d__wallet_);
        init();
        OnClickOption();

    }

    private void init(){
        txt_toolbarName =(TextView)findViewById(R.id.txt_toolbarName);
        back_press =(ImageView)findViewById(R.id.back_press);
        downloadView =(LinearLayout)findViewById(R.id.downloadView);
        setting_option = (ImageView)findViewById(R.id.setting_option);

        txt_toolbarName.setText("D - Wallet");

        try {

            txt_toolbarName.setText("D - Wallet - (Download)");
            fragment = new D_Wallet_DownloadFragment();
            Bundle bundle=new Bundle();
            //  txt_toolbarName.setText("Assignment - (Published Assignments)");
            bundle.putString("Download", "Download_Fragment");
            fragment.setArguments(bundle);
            loadFragmentMain(fragment);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void OnClickOption(){
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
            }
        });

        setting_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View popUpView = getLayoutInflater().inflate(R.layout.downlod_and_upload_layout,
                        null); // inflating popup layout
                mpopup = new PopupWindow(popUpView, ActionBar.LayoutParams.FILL_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT, true); // Creation of popup
                mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
                mpopup.showAtLocation(popUpView, Gravity.TOP, 0, 0);
                Typeface type_faceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-Bold.ttf");
                Typeface type_faceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Regular.ttf");
                lay_download =(LinearLayout)popUpView.findViewById(R.id.lay_download);
                lay_upload=(LinearLayout)popUpView.findViewById(R.id.lay_upload);

                view =(LinearLayout)popUpView.findViewById(R.id.view);
                txt_download =(TextView)popUpView.findViewById(R.id.txt_download);
                txt_upload=(TextView)popUpView.findViewById(R.id.txt_upload);


                txt_download.setTypeface(type_faceHeading);
                txt_upload.setTypeface(type_faceHeading);


                lay_download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txt_toolbarName.setText("D -Wallet  - (Download)");
                        fragment = new D_Wallet_DownloadFragment();
                        downloadView.setVisibility(View.VISIBLE);
                        Bundle bundle=new Bundle();
                        bundle.putString("Download", "Download_Fragment");
                        fragment.setArguments(bundle);
                        loadFragmentMain(fragment);
                        mpopup.dismiss();
                    }
                });

                lay_upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        txt_toolbarName.setText("D -Wallet  - (Upload)");
                        fragment = new D_Wallet_UploadDocFragment();
                       /// downloadView.setVisibility(View.VISIBLE);
                        Bundle bundle=new Bundle();
                        bundle.putString("Upload", "Upload_Fragment");
                        fragment.setArguments(bundle);
                        loadFragmentMain(fragment);

                        mpopup.dismiss();
                    }
                });

            }
        });



    }
    private void loadFragmentMain(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_e_ler, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
