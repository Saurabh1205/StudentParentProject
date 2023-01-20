package com.wordpro.studentproject.activities.certificate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NavigationActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CertificateApplnActivity extends AppCompatActivity {

    TextView txtCurrentDt;
    Button btnStudDtls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate_appln);

        txtCurrentDt=(TextView)findViewById(R.id.txtCurrentDt);
        String pattern = "dd-MM-yyyy";
        String dateInString =new SimpleDateFormat(pattern).format(new Date());
        txtCurrentDt.setText(dateInString);

        btnStudDtls=(Button)findViewById(R.id.btnStudDtls);
        btnStudDtls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CertificateApplnActivity.this,StudentDtlsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(CertificateApplnActivity.this,NavigationActivity.class);
        intent.putExtra("activity","CertificateApplnActivity");
        startActivity(intent);
        finish();
    }
}
