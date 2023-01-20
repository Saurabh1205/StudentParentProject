package com.wordpro.studentproject.activities.profile_menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NavigationActivity;
import com.wordpro.studentproject.fragment.CertificateInfoFrag;
import com.wordpro.studentproject.fragment.EduInfoFrag;
import com.wordpro.studentproject.fragment.PersonalInfoFrag;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.wordpro.studentproject.activities.NavigationActivity.selfRegistionDtlsArrayList;
import static com.wordpro.studentproject.activities.NavigationActivity.studentDtlsArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class ProfileActivity extends AppCompatActivity {

    private static String TAG = ProfileActivity.class.getSimpleName();
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    TextView txtStudentName, txtRegCode, txtYear, txtFormNo,txtProfile;
    String imageString;
    CircleImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isNetworkAvailable(this)) {
            // Create an Alert Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Set the Alert Dialog Message
            builder.setMessage("Internet Connection Required")
                    .setCancelable(false)
                    .setPositiveButton("Retry",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    // Restart the Activity
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            setContentView(R.layout.activity_profile);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            imageString = extras.getString("imageString");

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            profile_image = (CircleImageView) findViewById(R.id.profile_image);
            Log.d("Image Byte String : ", imageString);

            if (imageString.equalsIgnoreCase("no_image")) {
                //encode image to base64 string
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.studicon);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100,baos);
                byte[] imageBytes = baos.toByteArray();
                String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                //decode base64 string to image
                imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                profile_image.setImageBitmap(decodedImage);
            } else {
                byte[] imageAsBytes = Base64.decode(imageString.getBytes(), Base64.DEFAULT);
                profile_image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
            }

            txtProfile=(TextView)findViewById(R.id.txtProfile);
            txtProfile.setTypeface(typefaceHeading);
            txtStudentName = (TextView) findViewById(R.id.txtStudentName);
            txtStudentName.setTypeface(typefaceContent);
            txtStudentName.setText(studentDtlsArrayList.get(0).getFIRST_NAME() + " " + studentDtlsArrayList.get(0).getMIDDLE_NAME() + " " + studentDtlsArrayList.get(0).getLAST_NAME());

            txtRegCode = (TextView) findViewById(R.id.txtRegCode);
            txtRegCode.setTypeface(typefaceContent);
            txtRegCode.setText(studentDtlsArrayList.get(0).getSTUDENT_CODE() + "\n Registration Code");

            txtYear = (TextView) findViewById(R.id.txtYear);
            txtYear.setTypeface(typefaceContent);
            txtYear.setText(studentDtlsArrayList.get(0).getACADEMIC_YEAR() + "\n Year");

            txtFormNo = (TextView) findViewById(R.id.txtFormNo);
            txtFormNo.setTypeface(typefaceContent);
            txtFormNo.setText("\n Form No.");

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "3";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {

                case 0:
                    return new PersonalInfoFrag();
                case 1:
                    return new EduInfoFrag();
                case 2:
                    return new CertificateInfoFrag();

            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Personal Information";
                case 1:
                    return "Educational Information";
                case 2:
                    return "Certificate Information";
            }
            return null;
        }

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileActivity.this, NavigationActivity.class);
        intent.putExtra("activity","ProfileActivity");
        startActivity(intent);
        finish();
    }
}
