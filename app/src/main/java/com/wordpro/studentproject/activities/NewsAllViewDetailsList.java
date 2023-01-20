package com.wordpro.studentproject.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.adapter.New_NewsDetailsAdpter;
import com.wordpro.studentproject.adapter.NewsViewAllAdpter;
import com.wordpro.studentproject.model.NoticPattenChaild;
import static com.wordpro.studentproject.webConfig.URLEndPoints.ConstanceNoticPattenChaildNOTES;
import java.util.List;

public class NewsAllViewDetailsList extends AppCompatActivity {
    private TextView txt_toolbarName,txt_news_details;
    private RecyclerView  newsViewAllList;
    private ImageView back_press;
    private String  NotesHeader;
    private NewsViewAllAdpter newsViewAllAdpter;
    private List<NoticPattenChaild>pattenChailds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_all_view_details_list);

        txt_toolbarName = (TextView)findViewById(R.id.txt_toolbarName);
        txt_news_details = (TextView)findViewById(R.id.txt_news_details);
        back_press = (ImageView)findViewById(R.id.back_press);
        newsViewAllList = (RecyclerView)findViewById(R.id.newsViewAllList);
        NotesHeader = getIntent().getStringExtra("NoticeHeader");

        Typeface type_faceHeading = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Regular.ttf");
        txt_toolbarName.setTypeface(type_faceHeading);

        txt_toolbarName.setText(NotesHeader);
        pattenChailds = ConstanceNoticPattenChaildNOTES;


        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        txt_news_details.setText(pattenChailds.get(0).getnOTICEDESCRIPTION());


        if (pattenChailds.size()>0){
            newsViewAllAdpter = new NewsViewAllAdpter(NewsAllViewDetailsList.this, pattenChailds,NotesHeader);
           // newsViewAllList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(NewsAllViewDetailsList.this, LinearLayoutManager.HORIZONTAL, false);
            newsViewAllList.setLayoutManager(horizontalLayoutManagaer);

            //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            //newsViewAllList.setLayoutManager(mLayoutManager);
            newsViewAllList.setAdapter(newsViewAllAdpter);
        }

    }
}
