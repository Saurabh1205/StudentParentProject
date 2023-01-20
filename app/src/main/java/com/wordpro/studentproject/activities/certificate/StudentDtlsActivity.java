package com.wordpro.studentproject.activities.certificate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wordpro.studentproject.R;

public class StudentDtlsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dtls);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(StudentDtlsActivity.this,CertificateApplnActivity.class);
        startActivity(intent);
        finish();
    }
}
