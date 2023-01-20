package com.wordpro.studentproject.activities.Library;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NavigationActivity;
import com.wordpro.studentproject.adapter.BookReserveAdptr;
import com.wordpro.studentproject.fragment.LibraryFragment;

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.fragment.LibraryFragment.bookReservArraylist;
import static com.wordpro.studentproject.fragment.LibraryFragment.bookReserveAdptr;
import static com.wordpro.studentproject.fragment.LibraryFragment.fineModel;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class ReserveActivity extends AppCompatActivity {

    TextView txtStudName, txtYear, txtSem, txtSection,txtHead,txtDetailVw,txtthumbnail;
    LinearLayout lytDetail,lytThumbnail;
    RecyclerView recyVwDetail;

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
            setContentView(R.layout.activity_reserve);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            txtHead=(TextView)findViewById(R.id.txtHead);
            txtHead.setTypeface(typefaceHeading);
            txtStudName = (TextView) findViewById(R.id.txtStudName);
            txtStudName.setTypeface(typefaceContent);
            txtStudName.setText(studentName.toUpperCase());
            txtYear = (TextView) findViewById(R.id.txtYear);
            txtYear.setTypeface(typefaceContent);
            txtYear.setText(studAcadSessName.toUpperCase());
            txtSem = (TextView) findViewById(R.id.txtSem);
            txtSem.setTypeface(typefaceContent);
            txtSem.setText(studMainSemName.toUpperCase());
            txtSection = (TextView) findViewById(R.id.txtSection);
            txtSection.setTypeface(typefaceContent);
            txtSection.setText(studBranchStandDesc.toUpperCase());
            txtDetailVw=(TextView)findViewById(R.id.txtDetailVw);
            txtDetailVw.setTypeface(typefaceContent);
            txtthumbnail=(TextView)findViewById(R.id.txtthumbnail);
            txtthumbnail.setTypeface(typefaceContent);
            lytDetail = (LinearLayout) findViewById(R.id.lytDetail);
            lytThumbnail = (LinearLayout) findViewById(R.id.lytThumbnail);
            recyVwDetail = (RecyclerView) findViewById(R.id.recyVwDetail);
            recyVwDetail.setLayoutManager(new LinearLayoutManager(ReserveActivity.this));
            recyVwDetail.setAdapter(bookReserveAdptr);

            lytDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String type = "DETAIL_VIEW";
                    bookReserveAdptr = new BookReserveAdptr(ReserveActivity.this, bookReservArraylist, type);
                    recyVwDetail.setAdapter(bookReserveAdptr);
                }
            });
            lytThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String type = "THUMBNAIL_VIEW";
                    bookReserveAdptr = new BookReserveAdptr(ReserveActivity.this, bookReservArraylist, type);
                    recyVwDetail.setAdapter(bookReserveAdptr);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ReserveActivity.this, NavigationActivity.class);
        intent.putExtra("activity","ReserveActivity");
        startActivity(intent);
        finish();
    }
}
