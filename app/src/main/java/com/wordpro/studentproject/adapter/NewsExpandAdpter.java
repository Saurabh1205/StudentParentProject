package com.wordpro.studentproject.adapter;

import android.Manifest;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.SyncStateContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NewRightActionActivity;
import com.wordpro.studentproject.activities.ShowDetailsViewActivity;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.fragment.DynamicEventFragmen;
import com.wordpro.studentproject.model.DownloadURLModel;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.regex.Pattern;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.fragPayables;
import static com.wordpro.studentproject.activities.VerficationActivity.TAG;
import static com.wordpro.studentproject.activities.e_learning_menu.NotesActivity.downloadURLArrayList;
import static com.wordpro.studentproject.activities.e_learning_menu.NotesActivity.downloadURLModel;

/**
 * Created by wccs1980 on 30-04-2018.
 */

public class NewsExpandAdpter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    private HashMap<String, List<String>> _listDataChild;
    List<String> listDataHeaderExp;
    private int countList=0;
    private String cot;
    private TextView txt_readMore;
    private LinearLayout linearLayout;
    HashMap<String, List<String>> moduleNamesWithAllParameters = new
            HashMap<String, List<String>>();
    private Uri Download_Uri;
    int  countPage;
    ProgressDialog mProgressDialog;
    String fileName,Extent,sourcePath,imageType,Employee_id,Persone_Type,Originalfilename;
    private long refid;
    private DownloadManager downloadManager;
    public NewsExpandAdpter(Context context, ArrayList<String> groupName, HashMap<String, List<String>> listDataChild) {
        this._context = context;
        this._listDataHeader = groupName;
        this._listDataChild = listDataChild;
        mProgressDialog = new ProgressDialog(_context);
        downloadManager = (DownloadManager) _context.getSystemService(Context.DOWNLOAD_SERVICE);
        //  countList =listDataChild.size();
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        /*return this._listDataChild.get(groupPosition)
                .size();*/
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);


    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
      countPage =groupPosition;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expandebellist_group, null);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        TextView Count = (TextView) convertView
                .findViewById(R.id.itemCount);
        Typeface type_faceHeading = Typeface.createFromAsset(_context.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(_context.getAssets(), "font/Poppins-Regular.ttf");

        lblListHeader.setTypeface(type_faceHeading);
        Count.setTypeface(type_faceHeading);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            _listDataChild.entrySet().forEach(e -> {
                countList = e.getValue().size();
            });
        }



        cot= String.valueOf(countList);
        Count.setText(cot);
        lblListHeader.setText(headerTitle);

      /*  String[] header=headerTitle.split(Pattern.quote(" "));
        String unitname=header[0];
        String weightage=header[1];
        String noLec=header[2];
        Typeface customTypeOne = Typeface.createFromAsset(convertView.getContext().getAssets(), "font/Poppins-Medium.otf");

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(customTypeOne);
        lblListHeader.setText(unitname);

        TextView lblListCount=(TextView)convertView.findViewById(R.id.lblListCount);
        lblListCount.setTypeface(customTypeOne);
        lblListCount.setText("UNIT "+String.valueOf(groupPosition+1)+" : ");

        TextView lblListWeightage=(TextView)convertView.findViewById(R.id.lblListWeightage);
        lblListWeightage.setTypeface(customTypeOne);
        lblListWeightage.setText(weightage);

        TextView lblListPeriod=(TextView)convertView.findViewById(R.id.lblListPeriod);
        lblListPeriod.setTypeface(customTypeOne);
        lblListPeriod.setText(noLec);*/

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        // data = (NoticesModel.DataBean) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.dynamic_fragment, null);
        }

        String[] value=childText.split(Pattern.quote("$"));
        String topic_name=value[0];
        String topic_descrptn=value[1];
        String topic_img =value[2];
        String topic_id=value[3];
       // String file_path =value[4];
        ImageView img = (ImageView) convertView.findViewById(R.id.img_ex_news);
      //  ImageView download_img = (ImageView) convertView.findViewById(R.id.download_img);
        TextView txt_header_Name = (TextView) convertView.findViewById(R.id.txt_header_Name);
        TextView txt_header_date = (TextView) convertView.findViewById(R.id.txt_header_date);
        TextView txt_news_details = (TextView) convertView.findViewById(R.id.txt_news_details);
       // TextView txt_downloadAttachment = (TextView) convertView.findViewById(R.id.txt_downloadAttachment);
        TextView txt_share = (TextView) convertView.findViewById(R.id.txt_share);
        TextView txt_bookmark = (TextView) convertView.findViewById(R.id.txt_bookmark);
        txt_readMore = (TextView) convertView.findViewById(R.id.txt_readMore);

        Typeface type_faceHeading = Typeface.createFromAsset(_context.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(_context.getAssets(), "font/Poppins-Regular.ttf");

        txt_header_Name.setTypeface(type_faceHeading);
        txt_header_date.setTypeface(type_faceContent);
        txt_news_details.setTypeface(type_faceContent);

        txt_readMore.setTypeface(type_faceHeading);
       // txt_downloadAttachment.setTypeface(type_faceHeading);
        txt_share.setTypeface(type_faceHeading);
        txt_bookmark.setTypeface(type_faceHeading);






        txt_header_Name.setText(topic_name);
        txt_header_date.setText("02 -02 -2020  |  12:23 AM");
        txt_news_details.setText(topic_descrptn);

        Picasso.with(_context).load(topic_img).fit().centerCrop()
                .placeholder(R.drawable.event_img)
                .error(R.drawable.event_img)
                .into(img);


        txt_readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, ShowDetailsViewActivity.class);
                intent.putExtra("topic_name",topic_name);
                intent.putExtra("topic_img",topic_img);
                intent.putExtra("topic_descrptn",topic_descrptn);
                intent.putExtra("imgUrl","ImageUrl");
                _context.startActivity(intent);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, ShowDetailsViewActivity.class);
                intent.putExtra("topic_name",topic_name);
                intent.putExtra("topic_img",topic_img);
                intent.putExtra("topic_descrptn",topic_descrptn);
                intent.putExtra("imgUrl","ImageUrl");
                _context.startActivity(intent);
            }
        });
        txt_news_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, ShowDetailsViewActivity.class);
                intent.putExtra("topic_name",topic_name);
                intent.putExtra("topic_img",topic_img);
                intent.putExtra("topic_descrptn",topic_descrptn);
                intent.putExtra("imgUrl","ImageUrl");
                _context.startActivity(intent);
            }
        });

        txt_header_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, ShowDetailsViewActivity.class);
                intent.putExtra("topic_name",topic_name);
                intent.putExtra("topic_img",topic_img);
                intent.putExtra("topic_descrptn",topic_descrptn);
                intent.putExtra("imgUrl","ImageUrl");
                _context.startActivity(intent);
            }
        });

        txt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* String ss = "https://images.cartradeexchange.com//img//800//vehicle//Honda_Brio_562672_5995_6_1438153637072.jpg";//"http://117.247.82.252:500/NOTICE/HOME//EVENTBOX//SPORTS//182020_161120152.jpg";
                String txt ="Images for download";
                Uri uri = Uri.parse(ss);
                Intent shareIntent = new Intent();
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, txt);

                shareIntent.putExtra(Intent.EXTRA_TEXT, ss);
                shareIntent.setType("image/jpg");
                _context.startActivity(Intent.createChooser(shareIntent, "Share video File"));
*/

                String image_url = "http://117.247.82.252:500/NOTICE/HOME//EVENTBOX//SPORTS//182020_161120152.jpg";

                Intent shareIntent = new Intent();
                shareIntent.setType("image/jpg");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.setAction(Intent.ACTION_SEND);
                //without the below line intent will show error
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, image_url);

                // Target whatsapp:
                shareIntent.setPackage("com.whatsapp");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    _context.startActivity(shareIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(_context,
                            "Whatsapp have not been installed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        return convertView;
    }


    private void funDownloadURL(String fileName, String extent, String sourcePath, String imageType,  String persone_Type) {
        mProgressDialog.setMessage("Please wait loading!");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        String url= BASE_URL + URLEndPoints.PostDownloadCopyTo;
        Log.d(TAG, url);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              //  Log.d(TAG, "Response : "+MainHomeActivity.BASE_URL+""+response.toString());
                try{

                    //{"status":1,"data":[{"FilePath":"APP_DOWNLOAD\\25012019024218714.docx","FileName":"documentfile"}]}
                    Gson gson=new Gson();
                    downloadURLModel=gson.fromJson(response, DownloadURLModel.class);
                    if(downloadURLModel.getStatus()==1){
                        mProgressDialog.dismiss();

                        downloadURLArrayList=(ArrayList<DownloadURLModel.DataBean>)downloadURLModel.getData();
                        if(downloadURLArrayList.size()!=0 && downloadURLArrayList!=null){

                            String str=BASE_URL;          //http://117.247.82.252:500/api/
                            String[] s=str.split(Pattern.quote("api"));
                            String downloadURL=s[0]+downloadURLArrayList.get(0).getFilePath();
                            Originalfilename=downloadURLArrayList.get(0).getFileName();
                            //http://103.208.73.138:82/APP_DOWNLOAD\05102019053326536.jpeg

                            if(downloadURL.contains("\\")){
                                downloadURL=downloadURL.replace("\\","/");
                            }
                            Download_Uri = Uri.parse(downloadURL);

                            funDownloadFile(Originalfilename+"."+extent);

                            Log.d(TAG, "File download complete. : "+downloadURL +"\n Originalfilename:   "+Originalfilename);

                          /*  Snackbar.make(relLyt, "File is downloaded in Download folder.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();*/
                        }

                    }else{
                        mProgressDialog.hide();
                      /*  Snackbar.make(relLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/
                    }


                }catch (Exception e) {
                    mProgressDialog.hide();
                    /*Snackbar.make(relLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/
                    Log.d(TAG, "Error : " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressDialog.hide();
                Log.e(TAG, "Error: " + error.getMessage());
                /*Snackbar.make(relLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("fileName", fileName);
                params.put("Extent", extent);
                params.put("sourcePath", sourcePath);
                params.put("imageType", imageType);
                params.put("Employee_id", "");
                params.put("Persone_Type", persone_Type);
                params.put("OrignalName",fileName);

                Log.e(TAG, "InActive Posting params: " + params.toString());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }

    private void funDownloadFile(String originalfilename) {

        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("Downloading " + originalfilename);
        request.setDescription("Downloading... " +originalfilename);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, originalfilename);

        refid = downloadManager.enqueue(request);

        Log.e("OUT", "" + refid);

    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (_context.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

             //   ActivityCompat.requestPermissions(_context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    BroadcastReceiver onComplete = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {

            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);


            Log.e("IN", "" + referenceId);

            Log.e("INSIDE", "" + referenceId);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(_context)
                            .setSmallIcon(R.drawable.threeechoo)
                            .setContentTitle("ECHOAPPDOWNLOAD")
                            .setContentText("All Download completed");


            NotificationManager notificationManager = (NotificationManager) _context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(455, mBuilder.build());


            //  }

        }
    };



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
