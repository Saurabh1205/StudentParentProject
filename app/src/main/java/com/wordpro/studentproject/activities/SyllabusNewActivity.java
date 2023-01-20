package com.wordpro.studentproject.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.fragment.HomeFragment;
import com.wordpro.studentproject.fragment.UniversitySyllabusFragmen;
import com.wordpro.studentproject.webConfig.URLEndPoints;

public class SyllabusNewActivity extends AppCompatActivity {
    BottomNavigationView SyllabusNavigation;
    private ImageView back_press;
    private TextView  txt_toolbarName;
    private LinearLayout belowId;
    public static android.support.v4.app.Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_new);

        txt_toolbarName =(TextView)findViewById(R.id.txt_toolbarName);
        back_press =(ImageView)findViewById(R.id.back_press);
        belowId =(LinearLayout) findViewById(R.id.belowId);

        Typeface type_faceHeading = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Regular.ttf");
        txt_toolbarName.setText("SYLLABUS");
        txt_toolbarName.setTypeface(type_faceHeading);
        if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
            belowId.setBackgroundColor(ContextCompat.getColor(this, R.color.text_blue));
        }else {
            belowId.setBackgroundColor(ContextCompat.getColor(this, R.color.present));
        }
        fragment = new UniversitySyllabusFragmen();
        loadFragment(fragment);
/*

        SyllabusNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.Univ_syllabus) {
                  //  toolbar.setTitle(getResources().getString(R.string.title_home));
                    loadFragment(new UniversitySyllabusFragmen());
                    txt_toolbarName.setText("University Syllabus");
                    return true;
                } else if(id == R.id.Syllabus_Status) {
                   // toolbar.setTitle(getResources().getString(R.string.title_category));
                   // loadFragment(new homeFragment());
                    txt_toolbarName.setText("Syllabus Status");
                    return true;
                } else if(id == R.id.Teaching_plan) {
                    txt_toolbarName.setText("Teaching Plan");
                    //toolbar.setTitle(getResources().getString(R.string.title_notifications));
                    //loadFragment(new Teaching_plan());
                    return true;
                }
                return true;
            }
        });
*/

        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_syllabus_container, fragment);
        transaction.commit();
    }
}
