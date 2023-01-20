package com.wordpro.studentproject.activities.Library;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wordpro.studentproject.R;

import static com.wordpro.studentproject.activities.Library.SearchBookActivity.searchDetailsArrayList;
import static com.wordpro.studentproject.activities.Library.SearchBookActivity.searchedBkAdptr;
import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;

public class SearchedBookActivity extends AppCompatActivity {

    TextView txtStudName, txtYear, txtSem, txtSection,txtSearchbook;
    TextView txtbookCount,txtModifiedSearch;
    String PI_BOOK_TITLE,PI_AUTHOR_NAME,PI_PUBLICATION_NAME,PI_TOPIC_NAME;
    RecyclerView recyVwSearchedBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_book);

        Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
        Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

        txtSearchbook=(TextView)findViewById(R.id.txtSearchbook);
        txtSearchbook.setTypeface(typefaceHeading);
        txtStudName = (TextView) findViewById(R.id.txtStudName);
        txtStudName.setText(studentName.toUpperCase());
        txtYear = (TextView) findViewById(R.id.txtYear);
        txtYear.setText(studAcadSessName.toUpperCase());
        txtSem = (TextView) findViewById(R.id.txtSem);
        txtSem.setText(studMainSemName.toUpperCase());
        txtSection = (TextView) findViewById(R.id.txtSection);
        txtSection.setText(studBranchStandDesc.toUpperCase());
        recyVwSearchedBooks=(RecyclerView)findViewById(R.id.recyVwSearchedBooks);
        recyVwSearchedBooks.setLayoutManager(new LinearLayoutManager(SearchedBookActivity.this));
        recyVwSearchedBooks.setAdapter(searchedBkAdptr);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        PI_BOOK_TITLE = extras.getString("PI_BOOK_TITLE");
        PI_AUTHOR_NAME=extras.getString("PI_AUTHOR_NAME");
        PI_PUBLICATION_NAME=extras.getString("PI_PUBLICATION_NAME");
        PI_TOPIC_NAME=extras.getString("PI_TOPIC_NAME");

        txtbookCount=(TextView)findViewById(R.id.txtbookCount);
        if(searchDetailsArrayList.size()!=0 && searchDetailsArrayList!=null){

            txtbookCount.setText("Found total books : "+searchDetailsArrayList.size());
        }

        txtModifiedSearch=(TextView)findViewById(R.id.txtModifiedSearch);
        txtModifiedSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1=new Intent(SearchedBookActivity.this,SearchBookActivity.class);
                intent1.putExtra("PI_BOOK_TITLE", PI_BOOK_TITLE);
                intent1.putExtra("PI_AUTHOR_NAME", PI_AUTHOR_NAME);
                intent1.putExtra("PI_PUBLICATION_NAME", PI_PUBLICATION_NAME);
                intent1 .putExtra("PI_TOPIC_NAME", PI_TOPIC_NAME);
                startActivity(intent1);
                finish();

            }
        });


    }


    @Override
    public void onBackPressed() {
        Intent intent=new Intent(SearchedBookActivity.this,SearchBookActivity.class);
        intent.putExtra("PI_BOOK_TITLE", PI_BOOK_TITLE);
        intent.putExtra("PI_AUTHOR_NAME", PI_AUTHOR_NAME);
        intent.putExtra("PI_PUBLICATION_NAME", PI_PUBLICATION_NAME);
        intent.putExtra("PI_TOPIC_NAME", PI_TOPIC_NAME);
        startActivity(intent);
        finish();
    }
}