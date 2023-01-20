package com.wordpro.studentproject.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.DynamicNoticArray;
import com.wordpro.studentproject.model.NoticPattenChaild;

import java.util.ArrayList;
import java.util.List;

import static com.wordpro.studentproject.webConfig.URLEndPoints.ConstanceNoticPattenChaild;
import static com.wordpro.studentproject.webConfig.URLEndPoints.ConstanceNoticPattenParents;


public class GalleryMobileActivity extends AppCompatActivity {
   private ImageView selectedImage;
    List<DynamicNoticArray>noticarray;
    List<NoticPattenChaild>noticTest;
    String groupName;
    String  groupImg;
    TextView txt_toolbarName;
    private ImageView back_press;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_mobile);
        Intent intent = getIntent();
        groupName=intent.getStringExtra("NoticeHeader");

        noticTest = new ArrayList<>();
        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        selectedImage=(ImageView)findViewById(R.id.imageView);
        txt_toolbarName = (TextView)findViewById(R.id.txt_toolbarName);
        back_press = (ImageView)findViewById(R.id.back_press);

        Typeface type_faceHeading = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Regular.ttf");

        txt_toolbarName.setTypeface(type_faceHeading);


        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        for (int  i =0;i<ConstanceNoticPattenParents.size();i++){
            for (int  b =0;b<ConstanceNoticPattenParents.get(i).getChild().size();b++){
                if (groupName.equalsIgnoreCase(ConstanceNoticPattenParents.get(i).getChild().get(b).getnOTICEHEADER())){
                    noticTest =ConstanceNoticPattenParents.get(i).getChild();
                }
            }
        }


     //   noticarray = ConstanceNoticArray;
        gallery.setSpacing(1);
        final GalleryImageAdapter galleryImageAdapter= new GalleryImageAdapter(this,noticTest);
        gallery.setAdapter(galleryImageAdapter);

        try {
            groupImg = "http://"+noticTest.get(0).getIMAGEPATH_STATIC();
            //groupImg =  "http://192.168.1.72:500/"+noticTest.get(0).getiMAGEPATH();
            Picasso.with(getApplicationContext()).load(groupImg).fit().centerCrop()
                    .placeholder(R.drawable.collage_event)
                    .error(R.drawable.collage_event)
                    .into(selectedImage);
        }catch (Exception e){
            e.printStackTrace();
        }


        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // show the selected Image
               // selectedImage.setImageResource(Integer.parseInt(noticarray.get(position).getaTTACHMENTLINK()));
                groupImg = "http://"+noticTest.get(position).getIMAGEPATH_STATIC();
               // groupImg =  "http://192.168.1.72:500/"+noticTest.get(position).getiMAGEPATH();
                Picasso.with(getApplicationContext()).load(groupImg).fit().centerCrop()

                        .into(selectedImage);
                //selectedImage.setImageResource(R.drawable.collage_event);

                try {
                    Toast.makeText(getApplicationContext(),position,Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }

    public class GalleryImageAdapter extends BaseAdapter {
        private Context mContext;
        List<NoticPattenChaild>noticarrayTest;


        public GalleryImageAdapter(Context context, List<NoticPattenChaild> noticarray) {
            mContext = context;
            noticarrayTest =noticarray;
        }

        public int getCount() {
            return noticarrayTest.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ImageView i = new ImageView(mContext);
            String  imgurl =  "http:/"+noticarrayTest.get(position).getIMAGEPATH_STATIC();
           // String  imgurl =  "http://192.168.1.72:500/"+noticarrayTest.get(position).getiMAGEPATH();
            Picasso.with(getApplicationContext()).load(imgurl).fit().centerCrop()
                    .placeholder(R.drawable.event_img)
                    .error(R.drawable.event_img)
                    .into(i);

            //i.setImageResource(Integer.parseInt(noticarray.get(position).getaTTACHMENTLINK()));

           // i.setImageResource(R.drawable.collage_event);
            i.setLayoutParams(new Gallery.LayoutParams(300, 300));

            i.setScaleType(ImageView.ScaleType.FIT_XY);


            return i;
        }


    }

}
