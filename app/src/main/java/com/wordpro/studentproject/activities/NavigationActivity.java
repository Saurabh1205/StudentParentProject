package com.wordpro.studentproject.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fenchtose.tooltip.Tooltip;
import com.fenchtose.tooltip.TooltipAnimation;


import com.google.gson.Gson;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.profile_menu.ProfileActivity;
import com.wordpro.studentproject.adapter.JobManagerAdpter;
import com.wordpro.studentproject.adapter.StudentDetailsAdpter;
import com.wordpro.studentproject.adapter.homeAdpter.EventRecyVwAdpter;
import com.wordpro.studentproject.adapter.homeAdpter.GalleryRecyVwAdpter;
import com.wordpro.studentproject.adapter.homeAdpter.NewsRecyVwAdpter;
import com.wordpro.studentproject.adapter.homeAdpter.VideoRecyVwAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.fragment.ActionNewsFragment;
import com.wordpro.studentproject.fragment.DashboardFragment;
import com.wordpro.studentproject.fragment.FragAttendance;
import com.wordpro.studentproject.fragment.FragE_Learning;
import com.wordpro.studentproject.fragment.FragPayables;
import com.wordpro.studentproject.fragment.FragSchedule;
import com.wordpro.studentproject.fragment.FragSyllabus;
import com.wordpro.studentproject.fragment.HomeFragment;
import com.wordpro.studentproject.fragment.New_Dashbord_Fragment;
import com.wordpro.studentproject.helper.CustomDialogClass;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.GetStudentDetailsModel;
import com.wordpro.studentproject.model.JobCategoryModel;
import com.wordpro.studentproject.model.JobManagerModel;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.model.ProfileModel;
import com.wordpro.studentproject.model.ProfilePicModel;
import com.wordpro.studentproject.receivers.CheckNetworkconnectivityReceiver;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;


import de.hdodenhof.circleimageview.CircleImageView;

import static com.wordpro.studentproject.activities.VerficationActivity.eventsArrayList;
import static com.wordpro.studentproject.activities.VerficationActivity.galleryArrayList;
import static com.wordpro.studentproject.activities.VerficationActivity.jobCatCountArrayList;
import static com.wordpro.studentproject.activities.VerficationActivity.listDataHeaderEvents;
import static com.wordpro.studentproject.activities.VerficationActivity.listDataHeaderGallery;
import static com.wordpro.studentproject.activities.VerficationActivity.listDataHeaderNews;
import static com.wordpro.studentproject.activities.VerficationActivity.listDataHeaderVideos;
import static com.wordpro.studentproject.activities.VerficationActivity.newsArrayList;
import static com.wordpro.studentproject.activities.VerficationActivity.noticesArrayList;
import static com.wordpro.studentproject.activities.VerficationActivity.noticesArrayListupdated;
import static com.wordpro.studentproject.activities.VerficationActivity.videosArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;
import static com.wordpro.studentproject.webConfig.URLEndPoints.Constance_AlertVale;
import static com.wordpro.studentproject.webConfig.URLEndPoints.Constance_ApprovalVale;
import static com.wordpro.studentproject.webConfig.URLEndPoints.Constance_InfoVale;
import static com.wordpro.studentproject.webConfig.URLEndPoints.Constance_WARNINGVale;
import static com.wordpro.studentproject.webConfig.URLEndPoints.Constance_WellDoneVale;


public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = NavigationActivity.class.getSimpleName();
    private PrefManager pref;
    private BroadcastReceiver mBroadcastReceiver;
    /*RelativeLayout lytCoordnate;
    LinearLayout lytAttendance, lytSyllabus, lytE_Learning, lytFees, lytProfile, lytSchedule;*/
    TextView headerName, txtInstitute;
    private View navHeader;
    private ImageView img_logo;
    ProgressDialog navg_progress;
    CoordinatorLayout lytCoordnate;
    String fromActivity = "";
    TextView txtWellDoneCount, txtInfoCount, txtAlertCount, txtWarningCount, txtApprovalCount;
    TextView txtWellDone,txtInfo,txtWarning,txtApproval,txtAlert;
    LinearLayout lytWellDone, lytInfo, lytWarnings, lytApproval, lytAlerts;
    ProgressDialog mProgressDialog;
    ImageButton btnRefresh;
    SwipeRefreshLayout mSwipeRefreshLayout;
    public static String imageString = "no_image";
    private boolean showingFirst;
    //JobCategoryModel
    public static JobCategoryModel jobCategoryModel1;
    public static ArrayList<JobCategoryModel.RecordsBean> jobCatCountArrayList1;
    //NoticesModel
    public static NoticesModel noticesModel1;
    public static ArrayList<NoticesModel.DataBean> noticesArrayList1;
    public static ArrayList<NoticesModel.DataBean> noticesArrayListupdated1;
    public static ArrayList<NoticesModel.DataBean> newsArrayList1;
    public static ArrayList<NoticesModel.DataBean> eventsArrayList1;
    public static ArrayList<NoticesModel.DataBean> videosArrayList1;
    public static ArrayList<NoticesModel.DataBean> galleryArrayList1;
    public static List<String> listDataHeaderNews1;
    public static List<String> listDataHeaderEvents1;
    public static List<String> listDataHeaderVideos1;
    public static List<String> listDataHeaderGallery1;
    //JobCategoryModel
    public static JobCategoryModel jobCategoryModel2;
    public static ArrayList<JobCategoryModel.RecordsBean> jobCategoryArrayList;
    public static List<GetStudentDetailsModel.PendingJobDetail> pendingJobDetails;
    public static GetStudentDetailsModel getStudentDetailsModel;
    private TextView txt_Sem, txt_Name,txt_email,txt_email_2;

    //JobManager Model
    public static JobManagerModel jobManagerModel;
    public static ArrayList<JobManagerModel.PendingJobSummeryBean> jobManagerModelArrayList;

    public static JobManagerAdpter jobManagerAdpter;
    public static StudentDetailsAdpter studentDetailsAdpter;
    public static EventRecyVwAdpter eventRecyVwAdpter;
    public static NewsRecyVwAdpter newsRecyVwAdpter;
    public static VideoRecyVwAdpter videoRecyVwAdpter;
    public static GalleryRecyVwAdpter galleryRecyVwAdpter;
    public static String BASE_URL;
    Toolbar myToolbar;

    private  RelativeLayout  relative_layoutMain;
    private LinearLayout lyt1;
    private BottomNavigationView navigation;

    private TextView toolbar_title;
    private ImageView Menu_Navigation,action_Menu;

   // public static LinearLayout myToolbar;
    public static Fragment fragment;
    public static String studAcadSessName;
    public static String studCenterCode;
    public static String studSemester;
    public static String studBranchCode;
    public static String studEmailID;
    public static String studentName;
    public static String studSessionId;
    public static String studSemesterType;
    public static String studMainSemName;
    public static String studBranchStandardId;
    public static String studDepartmentNumber;
    public static String studBranchStandDesc;
    public static String student_id;
    public static String stud_main_semester_mst_id;
    public static String studBranch_Standard_GRP_ID;
    public static FragAttendance fragAttendance = new FragAttendance();
    public static FragSyllabus fragSyllabus = new FragSyllabus();
    public static FragPayables fragPayables = new FragPayables();
    public static FragSchedule fragSchedule = new FragSchedule();
    public static FragE_Learning fragE_learning = new FragE_Learning();

    //Profile Model
    public static ProfileModel profileModel;
    public static ArrayList<ProfileModel.StudentDtlsBean> studentDtlsArrayList;
    public static ArrayList<ProfileModel.AddressDtlsBean> addressDtlsArrayList;
    //  public static ArrayList<ProfileModel.CollegeDtlsBean> collegeDtlsArrayList;
    public static ArrayList<ProfileModel.ParentDtlsBean> parentDtlsArrayList;
    public static ArrayList<ProfileModel.DocumnetDtlsBean> documnetDtlsArrayList;
    public static ArrayList<ProfileModel.EntExamDtlsBean> entExamDtlsArrayList;
    public static ArrayList<ProfileModel.SelfRegistionDtlsBean> selfRegistionDtlsArrayList;

    //Profile Pic Model
    public static ProfilePicModel profilePicModel;
    public static ArrayList<ProfilePicModel.StudentIMGBean> profilePicArrayList;

    CircleImageView imageView;
    private boolean isLightOn;

    ArrayList<String> studMenuList;
    public static ArrayList<String> studMainMenu;
    public static ArrayList<String> studMainMenuADD;
    public static ArrayList<String> studSubMenu;
    public static ArrayList<String> studOtherSubMenu;
    //private Tooltip mClosePolicy = Tooltip;
    private CustomDialogClass customDialogClass;
    NavigationView navigationView;
    FragmentTransaction fTrans;
    private Tooltip mTooltip;
    public  ActionNewsFragment frag;
    private TextView tvCartItemCount;

    private ExpandableListAdapter mExpandableListAdapter;
    private List<String> mExpandableListTitle;

    private  ExpandableListView  mExpandableListView;
    private Map<String, List<String>> mExpandableListData;
    DrawerLayout drawer;
    private String[] items;

    private Handler mHandler;

    private int tooltipColor;
    private int tooltipSize;
    private int tooltipPadding;

    private int tipSizeSmall;
    private int tipSizeRegular;
    private int tipRadius;
    private Boolean TooltipOpen=false;
    String PassValue,TotalCount;
    private Toolbar toolbar;
    Tooltip customTooltip;
    private LinearLayout profile_Layout;
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:

                 /*   fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;*/
                if (noticesArrayList != null && noticesArrayList.size() != 0) {

                    eventRecyVwAdpter=new EventRecyVwAdpter(NavigationActivity.this,listDataHeaderEvents,eventsArrayList);
                    newsRecyVwAdpter=new NewsRecyVwAdpter(NavigationActivity.this,listDataHeaderNews,newsArrayList);
                    videoRecyVwAdpter=new VideoRecyVwAdpter(NavigationActivity.this,listDataHeaderVideos,videosArrayList);
                    galleryRecyVwAdpter = new GalleryRecyVwAdpter(NavigationActivity.this, listDataHeaderGallery, galleryArrayList);

                    //ArrayList<YoutubeVideo> youtubeVideosEventsGroup = new ArrayList<>();
                    // newsAdapterEvents = new NewsAdapter(MainHomeActivity.this, youtubeVideosEvents);

                    //fragment = new HomeFragment();
                    //loadFragment(fragment);
                    // true;
                } else {
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                }
                case R.id.navigation_dashboard:
                    fragment = new DashboardFragment();
                    loadFragment(fragment);
                    return true;
                // myToolbar.setTitle("Messages");
                   /* fragment = new GiftsFragment();
                    loadFragment(fragment);*/
                case R.id.navigation_notifications:

                    return true;
                case R.id.navigation_logout:

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NavigationActivity.this);
                    alertDialogBuilder.setMessage("Are you sure, You wanted to Logout ECHO App");
                    alertDialogBuilder.setPositiveButton("NO",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int arg1) {
                                    dialog.dismiss();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //super.onBackPressed();
                            logout();
                        }
                    });



                   // AlertDialog alertDialog = alertDialogBuilder.create();
                  //  alertDialog.show();

                   // return true;
                // myToolbar.setTitle("Events");
                   /* fragment = new ProfileFragment();
                    loadFragment(fragment);*/

            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mBroadcastReceiver = new CheckNetworkconnectivityReceiver();
        //Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/KaushanScript-Regular.otf");

        /*if (Build.VERSION.SDK_INT >= 21) {
            if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
                getWindow().setNavigationBarColor(getResources().getColor(R.color.text_blue));
                getWindow().setStatusBarColor(getResources().getColor(R.color.text_blue));

            }else {
                getWindow().setNavigationBarColor(getResources().getColor(R.color.present));
                getWindow().setStatusBarColor(getResources().getColor(R.color.present));
            }


        }*/

        Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-Bold.ttf");
        Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Regular.ttf");
        //Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
        //Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");
        //initActionBar();
        pref = new PrefManager(getApplicationContext());
        //customDialogClass = new CustomDialogClass(this);
        navg_progress =new ProgressDialog(NavigationActivity.this);
        lytCoordnate=(CoordinatorLayout)findViewById(R.id.lytCoordnate);
        relative_layoutMain=(RelativeLayout)findViewById(R.id.relative_layoutMain);
        lyt1=(LinearLayout) findViewById(R.id.lyt1);
        toolbar =(Toolbar)findViewById(R.id.toolbar);
        profile_Layout  =(LinearLayout) findViewById(R.id.profile_Layout);
        Intent intent = getIntent();
        fromActivity = intent.getStringExtra("activity");
        mProgressDialog=new ProgressDialog(NavigationActivity.this);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        LayoutInflater inflater = getLayoutInflater();


        lyt1=(LinearLayout) findViewById(R.id.lyt1);
        navigation=(BottomNavigationView)findViewById(R.id.navigation);
        txt_Sem=(TextView) findViewById(R.id.txt_Sem);
        txt_Name=(TextView) findViewById(R.id.txt_Name);
        txt_email=(TextView) findViewById(R.id.txt_email);
        txt_email_2=(TextView) findViewById(R.id.txt_email_2);
        imageView=(CircleImageView) findViewById(R.id.imageView);


        BASE_URL = pref.getURL();
        // Displaying user information from shared preferences
        HashMap<String, String> studentDetails = pref.getStudentDetails();
        studSemester = studentDetails.get("branch_semester_name");
        studBranchCode = studentDetails.get("branch_standard_code");
        studSessionId = studentDetails.get("acad_session_id");
        studSemesterType = studentDetails.get("main_semester_type");
        studBranchStandardId = studentDetails.get("branch_standard_id");
        studDepartmentNumber = studentDetails.get("department_number");
        studCenterCode = studentDetails.get("centre_code");
        studAcadSessName = studentDetails.get("acad_sess_name");
        studMainSemName = studentDetails.get("main_semester_name");
        studBranchStandDesc = studentDetails.get("branch_standard_description");
        student_id = studentDetails.get("student_id");
        URLEndPoints.Constance_StudentID = student_id;
        URLEndPoints.Constance_StudentCenterCode =studCenterCode;
        URLEndPoints.Constance_StudentDepartmentNumber =studDepartmentNumber;
        URLEndPoints.Constance_StudBranchStandardId =studBranchStandardId;
        URLEndPoints.Constance_StudSemesterType = studSemesterType;
        URLEndPoints.Constance_StudSessionId = studSessionId;
        URLEndPoints.Constance_stander_code =studBranchCode;

        stud_main_semester_mst_id = studentDetails.get("main_semester_mst_id");
        studBranch_Standard_GRP_ID = studentDetails.get("branch_standard_grp_id");
        URLEndPoints.Constance_stud_main_semester_mst_id =stud_main_semester_mst_id;
        URLEndPoints.Constance_studBranch_Standard_GRP_ID =studBranch_Standard_GRP_ID;
        // Displaying user information from shared preferences
        HashMap<String, String> studentPersonalDetails = pref.getStudentPersonalDetails();
        studentName = studentPersonalDetails.get("stud_ml_name_short");
        studEmailID = studentPersonalDetails.get("student_email_id_p");

        studMainMenu = new ArrayList<>();
        studSubMenu = new ArrayList<>();
        studOtherSubMenu = new ArrayList<>();

        Set<String> menuSet = pref.getMenuArrayList();
        ArrayList<String> menuList = new ArrayList<>();
        menuList.addAll(menuSet);



      /*  mToolTipFrameLayout = (ToolTipRelativeLayout) findViewById(R.id.activity_main_tooltipframelayout);
        addRedToolTipView();
*/


        txt_Sem.setText(studSemester);
        txt_Name.setText(studentName);
        txt_email.setText(studEmailID);
       // txt_email_2.setText(studEmailID);

        String menu = pref.getMenuStringBuilder();

        Log.d(TAG, "Navigation Menu List : " + menu);

        if (menu != null) {

            //            menu.split("(,@)|(\\#\\@)");
            //String[] mainmenu = menu.split(",@");

            String[] mainmenu = menu.split("(,@)|(\\#\\@)");

            Log.d("Main menu length : ", String.valueOf(mainmenu.length));

            for (int i = 0; i < mainmenu.length; i++) {

                String menuwithSubmenu = mainmenu[i];
                if(menuwithSubmenu.contains("#")){
                    String[] array = menuwithSubmenu.split("#");

                    String main = array[0];

                    studMainMenu.add(main);

                    String submenu = null;

                    if (array[1] != null && !array[1].equalsIgnoreCase("") && !array[1].isEmpty()) {
                        submenu = array[1];
                    }


                    String[] submenuElement = submenu.split(",");
                    for (int j = 0; j < submenuElement.length; j++) {

                        studSubMenu.add(submenuElement[j]);
                    }

                    Log.d(TAG, "Main Menu : " + mainmenu[i]);
                    Log.d(TAG, "Main Menu : " + main);
                    Log.d(TAG, "Main submenu : " + submenu);

                }else{
                    if (!menuwithSubmenu.equalsIgnoreCase("Profile")){
                        studMainMenu.add(menuwithSubmenu);
                    }


                }


            }
            String element = "Logout";
            studMainMenu.add(element);
            Log.d(TAG, "Emp Menu : " + studMainMenu);
            Log.d(TAG, "Emp Submenu : " + studSubMenu);

        }



        String otherSubMenu = pref.getSubOtherMenu();
        if (otherSubMenu != null) {

            String[] otherSubmenuElement = otherSubMenu.split(",");
            for (int j = 0; j < otherSubmenuElement.length; j++) {

                studOtherSubMenu.add(otherSubmenuElement[j]);
            }

            Log.d(TAG, "Emp OtherSubmenu : " + studOtherSubMenu);

        }

      //  listHeaderView = inflater.inflate(R.layout.nav_header_navigation, null, false);

       /* mExpandableListView = (ExpandableListView) findViewById(R.id.navList);
        mExpandableListData = ExpandableListDataSource.getData(this);

        mExpandableListTitle = new ArrayList(mExpandableListData.keySet());
        mExpandableListView.addHeaderView(listHeaderView);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                50, r.getDisplayMetrics());
        int px2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                10, r.getDisplayMetrics());
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            mExpandableListView.setIndicatorBounds(width - px, width - px2);
        } else {
            mExpandableListView.setIndicatorBoundsRelative(width - px, width - px2);
        }
        initItems();
        addDrawerItems();*/

        // Navigation view header

        navHeader = navigationView.getHeaderView(0);
        headerName = (TextView) navHeader.findViewById(R.id.headerName);
        img_logo = (ImageView) navHeader.findViewById(R.id.img_logo);
       // headerName.setText("dgbdgjdgdg");
        headerName.setTypeface(typefaceHeading);
        Log.d(TAG, studentName);
        txtInstitute = (TextView) navHeader.findViewById(R.id.txtInstitute);
        txtInstitute.setText(studEmailID);
        txtInstitute.setTypeface(typefaceHeading);



        try {
            TooltipOpen  =false;
            customTooltip.dismiss(true);
        }catch (Exception e){
            e.printStackTrace();
        }


        //imageView = (CircleImageView) navHeader.findViewById(R.id.imageView);

        funProfilePic("navigationPic");
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        //myToolbar = (LinearLayout) findViewById(R.id.toolbar);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title) ;
       // Menu_Navigation = (ImageView) findViewById(R.id.Menu_Navigation);
       // action_Menu = (ImageView) findViewById(R.id.action_Menu) ;
        studentName = studentName.replace("Mr. ", "");
        studentName = studentName.replace("Ms. ", "");

       // toolbar_title.setText(studentName);


        myToolbar.setTitle(studentName);

        TextView tv = (TextView) myToolbar.getChildAt(0);
        tv.setTypeface(Typeface.create("serif-monospace", Typeface.BOLD));
          tv.setTypeface(typefaceHeading);

       // toolbar_title.setTypeface(typefaceHeading);

       // myToolbar.setSubtitle(studBranchCode + "  (" + studSemester + ")");
       // TextView textView = (TextView) myToolbar.getChildAt(1);
       // textView.setTypeface(Typeface.create("serif-monospace", Typeface.BOLD));
       // textView.setTypeface(typefaceHeading);
        setSupportActionBar(myToolbar);

       // getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
       // getSupportActionBar().setCustomView(R.layout.custom_toolbar);



        txtWellDoneCount = (TextView) findViewById(R.id.txtWellDoneCount);
        txtWellDoneCount.setTypeface(typefaceContent);
        txtInfoCount = (TextView) findViewById(R.id.txtInfoCount);
        txtInfoCount.setTypeface(typefaceContent);
        txtAlertCount = (TextView) findViewById(R.id.txtAlertCount);
        txtAlertCount.setTypeface(typefaceContent);
        txtWarningCount = (TextView) findViewById(R.id.txtWarningCount);
        txtWarningCount.setTypeface(typefaceContent);
        txtApprovalCount = (TextView) findViewById(R.id.txtApprovalCount);
        txtApprovalCount.setTypeface(typefaceContent);
        txtWellDone = (TextView) findViewById(R.id.txtWellDone);
        txtWellDone.setTypeface(typefaceContent);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        txtInfo.setTypeface(typefaceContent);
        txtAlert = (TextView) findViewById(R.id.txtAlert);
        txtAlert.setTypeface(typefaceContent);
        txtWarning = (TextView) findViewById(R.id.txtWarning);
        txtWarning.setTypeface(typefaceContent);
        txtApproval = (TextView) findViewById(R.id.txtApproval);
        txtApproval.setTypeface(typefaceContent);

        lytWellDone = (LinearLayout) findViewById(R.id.lytWellDone);
        lytAlerts = (LinearLayout) findViewById(R.id.lytAlerts);
        lytInfo = (LinearLayout) findViewById(R.id.lytInfo);
        lytApproval = (LinearLayout) findViewById(R.id.lytApproval);
        lytWarnings = (LinearLayout) findViewById(R.id.lytWarnings);


         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


/*
        if (!pref.getString("AppliedTheme","").equals(""))
        {
            if (pref.getString("AppliedTheme","").equals("Green"))
            {
                setTheme(R.style.ThemeApp_Green);
            }
            else if (pref.getString("AppliedTheme","").equals("Green_Dark"))
            {
                setTheme(R.style.ThemeApp_Green_Dark);
            }
            else if (pref.getString("AppliedTheme","").equals("Purple_Dark"))
            {
                setTheme(R.style.ThemeApp_Purple_Dark);
            }
            else if (pref.getString("AppliedTheme","").equals("Purple"))
            {
                setTheme(R.style.ThemeApp_Purple);
            }
        }
        else
        {
            setTheme(R.style.ThemeApp_Green);
        }*/
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
       /* mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);

              //  funJobManager();
            }
        });*/
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // buttonClick(v);
                if(isLightOn){
                  //  pref.getString("AppliedTheme", "Green_Dark");
                } else{
                  //  pref.getString("AppliedTheme", "Purple_Dark");
                }
                isLightOn = !isLightOn;
            }
        });

        if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){

            img_logo.setBackgroundResource(R.drawable.new_logo);
            headerName.setText(R.string.Student_app_name);
            myToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.text_blue));
            profile_Layout.setBackgroundColor(ContextCompat.getColor(this, R.color.text_blue));
        }else {
            img_logo.setBackgroundResource(R.drawable.parapplogo);
            headerName.setText(R.string.Parent_app_name);
            myToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.present));
            profile_Layout.setBackgroundColor(ContextCompat.getColor(this, R.color.present));
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
       // Fragment fragment;
        addMenuItemInNavMenuDrawer();
        fromActivity = intent.getStringExtra("activity");
        if (fromActivity.equalsIgnoreCase("VerifyActivity") || fromActivity.equalsIgnoreCase("YoutubeListActivity")) {
            if (jobCatCountArrayList.size() != 0 && jobCatCountArrayList != null) {

                for (int i = 0; i < jobCatCountArrayList.size(); i++) {

                    String welldone = jobCatCountArrayList.get(0).getWELLDONE();
                    if (welldone.equalsIgnoreCase("") || welldone.isEmpty()) {
                        txtWellDoneCount.setText("0");
                        URLEndPoints.Constance_WellDoneVale ="0";
                    } else {
                        txtWellDoneCount.setText(welldone);
                        URLEndPoints.Constance_WellDoneVale =welldone;
                    }

                    String alert = jobCatCountArrayList.get(0).getALERT();
                    if (alert.equalsIgnoreCase("") || alert.isEmpty()) {
                        txtAlertCount.setText("0");
                        Constance_AlertVale ="0";
                    } else {
                        txtAlertCount.setText(alert);
                        Constance_AlertVale =alert;
                    }

                    String approval = jobCatCountArrayList.get(0).getAPPROVALS();
                    if (approval.equalsIgnoreCase("") || approval.isEmpty()) {
                        txtApprovalCount.setText("0");
                        Constance_ApprovalVale="0";
                    } else {
                        txtApprovalCount.setText(approval);
                        Constance_ApprovalVale=approval;

                    }

                    String info = jobCatCountArrayList.get(0).getINFO();
                    if (info.equalsIgnoreCase("") || info.isEmpty()) {
                        txtInfoCount.setText("0");
                        Constance_InfoVale ="0";
                    } else {
                        txtInfoCount.setText(info);
                        Constance_InfoVale =info;
                    }

                    String warning = jobCatCountArrayList.get(0).getWARNING();
                    if (warning.equalsIgnoreCase("") || warning.isEmpty()) {
                        txtWarningCount.setText("0");
                        Constance_WARNINGVale ="0";
                    } else {
                        txtWarningCount.setText(warning);
                        Constance_WARNINGVale =warning;
                    }
                }
            }

            /* eventRecyVwAdpter=new EventRecyVwAdpter(NavigationActivity.this,listDataHeaderEvents,eventsArrayList);
             newsRecyVwAdpter=new NewsRecyVwAdpter(NavigationActivity.this,listDataHeaderNews,newsArrayList);
             videoRecyVwAdpter=new VideoRecyVwAdpter(NavigationActivity.this,listDataHeaderVideos,videosArrayList);
             galleryRecyVwAdpter = new GalleryRecyVwAdpter(NavigationActivity.this, listDataHeaderGallery, galleryArrayList);*/
            //fragment = new HomeFragment();
           // loadFragment(fragment);
           fragment =new New_Dashbord_Fragment();
           loadFragment(fragment);
        }else if(fromActivity.equalsIgnoreCase("NavigationActivity")){


            Log.d(TAG,"NavigationActivity refresh..............................");

            if (jobCatCountArrayList.size() != 0 && jobCatCountArrayList != null) {

                for (int i = 0; i < jobCatCountArrayList.size(); i++) {

                    String welldone = jobCatCountArrayList.get(0).getWELLDONE();
                    if (welldone.equalsIgnoreCase("") || welldone.isEmpty()) {
                        txtWellDoneCount.setText("0");
                        URLEndPoints.Constance_WellDoneVale ="0";
                    } else {
                        txtWellDoneCount.setText(welldone);
                       URLEndPoints.Constance_WellDoneVale =welldone;

                    }

                    String alert = jobCatCountArrayList.get(0).getALERT();
                    if (alert.equalsIgnoreCase("") || alert.isEmpty()) {
                        txtAlertCount.setText("0");
                        Constance_AlertVale ="0";
                    } else {
                        txtAlertCount.setText(alert);
                        Constance_AlertVale =alert;
                    }

                    String approval = jobCatCountArrayList.get(0).getAPPROVALS();
                    if (approval.equalsIgnoreCase("") || approval.isEmpty()) {
                        txtApprovalCount.setText("0");
                        Constance_ApprovalVale= "0";
                    } else {
                        txtApprovalCount.setText(approval);
                        Constance_ApprovalVale=approval;
                    }

                    String info = jobCatCountArrayList.get(0).getINFO();
                    if (info.equalsIgnoreCase("") || info.isEmpty()) {
                        txtInfoCount.setText("0");
                        Constance_InfoVale ="0";
                    } else {
                        Constance_InfoVale =info;
                        txtInfoCount.setText(info);
                    }

                    String warning = jobCatCountArrayList.get(0).getWARNING();
                    if (warning.equalsIgnoreCase("") || warning.isEmpty()) {
                        txtWarningCount.setText("0");
                        Constance_WARNINGVale ="0";
                    } else {
                        txtWarningCount.setText(warning);
                        Constance_WARNINGVale =warning;
                    }

                }

            }


          /*  eventRecyVwAdpter=new EventRecyVwAdpter(NavigationActivity.this,listDataHeaderEvents1,eventsArrayList1);
            newsRecyVwAdpter=new NewsRecyVwAdpter(NavigationActivity.this,listDataHeaderNews1,newsArrayList1);
            videoRecyVwAdpter=new VideoRecyVwAdpter(NavigationActivity.this,listDataHeaderVideos1,videosArrayList1);
            galleryRecyVwAdpter = new GalleryRecyVwAdpter(NavigationActivity.this, listDataHeaderGallery1, galleryArrayList1);*/
           /* fragment = new HomeFragment();
            loadFragment(fragment);*/
            fragment = new New_Dashbord_Fragment();
            loadFragment(fragment);
        } else {
            //loadFragment(new DashboardFragment());
          //  navigation.setSelectedItemId(R.id.navigation_dashboard);
          //  funJobManager();
        }

        /*lytWellDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkAvailable(NavigationActivity.this)) {
                    // Create an Alert Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
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
                }else {
                    String count = txtWellDoneCount.getText().toString();
                    if (count.equalsIgnoreCase("0") || count.isEmpty() || count.equalsIgnoreCase("")) {
                     *//*   Snackbar.make(lytCoordnate, "Record not available", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*//*
                    } else {

                        funTaskMAnager("WELLDONE");
                    }
                }

            }
        });


        lytWarnings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkAvailable(NavigationActivity.this)) {
                    // Create an Alert Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
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
                }else {
                    String count = txtWarningCount.getText().toString();
                    if (count.equalsIgnoreCase("0") || count.isEmpty() || count.equalsIgnoreCase("")) {
                       *//* Snackbar.make(lytCoordnate, "Record not available", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*//*
                    } else {
                        funTaskMAnager("WARNING");
                    }
                }

            }
        });


        lytInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkAvailable(NavigationActivity.this)) {
                    // Create an Alert Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
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
                }else {
                    String count = txtInfoCount.getText().toString();
                    if (count.equalsIgnoreCase("0") || count.isEmpty() || count.equalsIgnoreCase("")) {
                       *//* Snackbar.make(lytCoordnate, "Record not available", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*//*
                    } else {
                        funTaskMAnager("INFO");
                        //funTaskMAnagerGetApi("INFO");

                    }
                }

            }
        });

        lytApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkAvailable(NavigationActivity.this)) {
                    // Create an Alert Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
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
                }else {
                    String count = txtApprovalCount.getText().toString();
                    if (count.equalsIgnoreCase("0") || count.isEmpty() || count.equalsIgnoreCase("")) {
                       *//* Snackbar.make(lytCoordnate, "Record not available", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*//*
                    } else {
                        funTaskMAnager("APPROVALS");
                    }
                }

            }
        });

        lytAlerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkAvailable(NavigationActivity.this)) {
                    // Create an Alert Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
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
                }else {
                    String count = txtAlertCount.getText().toString();
                    if (count.equalsIgnoreCase("0") || count.isEmpty() || count.equalsIgnoreCase("")) {
                       *//* Snackbar.make(lytCoordnate, "Record not available", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*//*
                    } else {
                        funTaskMAnager("ALERT");
                    }
                }

            }
        });*/


     /*   action_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in  =new Intent(NavigationActivity.this,NavigationDrawerActivity.class);
                startActivity(in);
            }
        });*/


    }
    private void addMenuItemInNavMenuDrawer() {
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);


        Menu menu = navView.getMenu();
        try {
            for (int i = 0; i <studMainMenu.size(); i++) {
                //if()
                menu.add(studMainMenu.get(i));
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        navView.invalidate();
    }
    /*private void initActionBar() {

        ActionBar actionBar = getActionBar();

        if (actionBar == null) {
            return;
        }


        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        actionBar.setCustomView(R.layout.action_bar_layout_new);
        View customView = actionBar.getCustomView().findViewById(
                R.id.icSlidingMenu);
        tvCartItemCount=customView.findViewById(R.id.tvCartItemCount);

        ImageView slideButton = (ImageView) customView
                .findViewById(R.id.icSlidingMenu);

        slideButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // getSlidingMenu().toggle(true);
            }
        });

        actionBar.setDisplayHomeAsUpEnabled(true);
    }
*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_action_setting, menu);

        return true;
    }




    private void handleVolleyError(VolleyError error) {
        //if any error occurs in the network operations, show the TextView that contains the error message
        String message = null;
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            message = getResources().getString(R.string.error_timeout);
        } else if (error instanceof AuthFailureError) {
            message = getResources().getString(R.string.error_auth_failure);
            //TODO
        } else if (error instanceof ServerError) {
            message = getResources().getString(R.string.error_auth_failure);
            //TODO
        } else if (error instanceof NetworkError) {
            message = getResources().getString(R.string.error_network);
            //TODO
        } else if (error instanceof ParseError) {
            message = getResources().getString(R.string.error_parser);
            //TODO
        }

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }
    public void removeFragment(Fragment fragment){
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    @SuppressLint("CutPasteId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case R.id.action_bell_icon:

                Bundle bundle1 = new Bundle();
                bundle1.putString("studCenterCode",studCenterCode);
                bundle1.putString("studDepartmentNumber", studDepartmentNumber);
                bundle1.putString("student_id", student_id);
                bundle1.putString("studBranchStandardId",studBranchStandardId);

                if(TooltipOpen==false){
                    showCustomTooltip(findViewById(R.id.action_bell_icon));
                }
               //showCustomTooltip(findViewById(R.id.action_bell_icon));



              /*fragment = new NewsDetailsFragment();
                loadFragmentMain(fragment);*/




               break;


            case R.id.action_new:
                try {
                    TooltipOpen  =false;
                    customTooltip.dismiss(true);
                }catch (Exception e){
                    e.printStackTrace();
                }

                Bundle bundle = new Bundle();
                bundle.putString("studCenterCode",studCenterCode);
                bundle.putString("studDepartmentNumber", studDepartmentNumber);
                bundle.putString("student_id", student_id);
                bundle.putString("studBranchStandardId",studBranchStandardId);
                ActionNewsFragment fragobj = new ActionNewsFragment();
                fragobj.setArguments(bundle);

                fragment = new ActionNewsFragment();
                loadFragmentMain(fragment);

                break;

        }
        return false;

       // return super.onOptionsItemSelected(item);
    }


    private void displayPopupMenu() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View popupView = layoutInflater.inflate(R.layout.custom_notification_dilog, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
       // RefreshListView myListView = (RefreshListView) popupView.findViewById(R.id.popup_menu_list_listview);
        //mAdapter = new myAdapter(this, getAdapterData());
       // myListView.setAdapter(mAdapter);
       // popupWindow.showAsDropDown(mMyAnchor);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        String menuName  = String.valueOf(item.getTitle());
        int id = item.getItemId();


        if (menuName.equalsIgnoreCase("Attendance")){
            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent intent =new Intent(this,AttendanceActivity.class);
            startActivity(intent);
        }else if (menuName.equalsIgnoreCase("Syllabus")) {
            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent intent =new Intent(this,SyllabusNewActivity.class);
            startActivity(intent);
        } else if (menuName.equalsIgnoreCase("Schedule")) {
            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent intent =new Intent(this,NewScheduleActivity.class);
            startActivity(intent);
        } else if (menuName.equalsIgnoreCase("Fees")) {
            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent intent =new Intent(this,FeesActivity.class);
            startActivity(intent);
        } else if (menuName.equalsIgnoreCase("E-Learning")) {

            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent intent =new Intent(this,E_Learning_Activity.class);
            startActivity(intent);

        } else if (id == R.id.profile) {
           /* if (!isNetworkAvailable(NavigationActivity.this)) {
                // Create an Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
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
            }else {
                funProfileDetails();

            }*/
        }else if (menuName.equalsIgnoreCase("Logout")) {
            logout();
        }
        else if (id == R.id.schedule) {

        } else if (id == R.id.about) {

        } else if (id == R.id.policies) {

        } else if (id == R.id.logout) {
            logout();
        }
        else if (id == R.id.dashboard_new) {
           // fragment = new DashboardFragment();
           // loadFragment(fragment);
            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(id ==R.id.attendance_new){
           /* FragmentManager fragmentManager = getSupportFragmentManager();
            fragAttendance.setRetainInstance(true);
            fragAttendance.show(fragmentManager, "fragAttendance");*/
            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent intent =new Intent(this,AttendanceActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.syllabus_new) {
           /* FragmentManager fragmentManager = getSupportFragmentManager();
            fragSyllabus.setRetainInstance(true);
            fragSyllabus.show(fragmentManager, "fragSyllabus");*/
            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent intent =new Intent(this,SyllabusNewActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.schedule_new) {
           /* FragmentManager fragmentManager = getSupportFragmentManager();
            new_schedule_fragmen.setRetainInstance(true);
            new_schedule_fragmen.show(fragmentManager, "new_schedule_fragmen");*/
            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent intent =new Intent(this,NewScheduleActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.fees_new) {
            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent intent =new Intent(this,FeesActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.e_learning_new) {
            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent intent =new Intent(this,E_Learning_Activity.class);
            startActivity(intent);
           /* FragmentManager fragmentManager =getSupportFragmentManager();
            fragE_learning.setRetainInstance(true);
            fragE_learning.show(fragmentManager, "fragE_learning");*/
        }else if (id == R.id.d_wallet_new) {
            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent intent =new Intent(this,D_Wallet_Activity.class);
            startActivity(intent);

        }
        else if (id == R.id.library_new) {
            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent intent =new Intent(this,LibraryActivity.class);
            startActivity(intent);

        }else if (id==R.id.logout_new){
            try {
                TooltipOpen  =false;
                customTooltip.dismiss(true);
            }catch (Exception e){
                e.printStackTrace();
            }
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragmentMain(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void logout() {
        pref.clearSession();
        URLEndPoints.ConstanceLoginUser=null;
        Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }



    private void funProfilePic(final String picType) {

       /* navg_progress.setMessage("Please wait loading!");
        navg_progress.setCanceledOnTouchOutside(false);
        navg_progress.show();*/
        String url = pref.getURL() + URLEndPoints.getProfilepic_URL(student_id);
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {

                    JSONObject responseObj = new JSONObject(response);
                    Gson gson = new Gson();
                    profilePicModel = gson.fromJson(response, ProfilePicModel.class);

                    if (profilePicModel.getStatus() == 1) {

                        navg_progress.dismiss();
                        profilePicArrayList = (ArrayList<ProfilePicModel.StudentIMGBean>) profilePicModel.getStudentIMG();
                        if (profilePicArrayList != null && profilePicArrayList.size() != 0) {

                            if (profilePicArrayList.get(0).getSTU_EMP_IMG() != null && !profilePicArrayList.get(0).getSTU_EMP_IMG().isEmpty()) {
                                imageString = profilePicArrayList.get(0).getSTU_EMP_IMG();

                                if (picType.equals("navigationPic")) {
                                    byte[] imageAsBytes = Base64.decode(imageString.getBytes(), Base64.DEFAULT);
                                 //   imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
                                } else if (picType.equals("profilePic")) {
                                    Intent intent = new Intent(NavigationActivity.this, ProfileActivity.class);
                                    intent.putExtra("imageString", imageString);
                                    startActivity(intent);
                                    finish();
                                }

                            } else {
                                if (picType.equals("navigationPic")) {
                                    //encode image to base64 string
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.studicon);
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                    byte[] imageBytes = baos.toByteArray();
                                    imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                                    //decode base64 string to image
                                    imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                                   // imageView.setImageBitmap(decodedImage);

                                } else if (picType.equals("profilePic")) {
                                    Intent intent = new Intent(NavigationActivity.this, ProfileActivity.class);
                                    intent.putExtra("imageString", "no_image");
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }

                    } else if (profileModel.getStatus() == 0) {
                        Snackbar.make(lytCoordnate, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                   // navg_progress.dismiss();

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Error: " + e.getMessage());

                    Snackbar.make(lytCoordnate, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                   // navg_progress.hide();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
               handleVolleyError(error);
                lytCoordnate.setVisibility(View.GONE);
                //navg_progress.hide();
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest);


    }

    private void funProfileDetails() {
        navg_progress.setMessage("Please wait loading!");
        navg_progress.setCanceledOnTouchOutside(false);
        navg_progress.show();

        String url = pref.getURL() + URLEndPoints.getProfileDetails(student_id,studCenterCode);
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {

                    JSONObject responseObj = new JSONObject(response);
                    Gson gson = new Gson();
                    profileModel = gson.fromJson(response, ProfileModel.class);

                    if (profileModel.getStatus() == 1) {

                        navg_progress.dismiss();
                        studentDtlsArrayList = (ArrayList<ProfileModel.StudentDtlsBean>) profileModel.getStudent_Dtls();
                        addressDtlsArrayList = (ArrayList<ProfileModel.AddressDtlsBean>) profileModel.getAddress_Dtls();
                        //collegeDtlsArrayList=(ArrayList<ProfileM>) profileModel.getCollege_Dtls();
                        parentDtlsArrayList = (ArrayList<ProfileModel.ParentDtlsBean>) profileModel.getParent_Dtls();
                        documnetDtlsArrayList = (ArrayList<ProfileModel.DocumnetDtlsBean>) profileModel.getDocumnet_Dtls();
                        entExamDtlsArrayList = (ArrayList<ProfileModel.EntExamDtlsBean>) profileModel.getEntExam_Dtls();
                        selfRegistionDtlsArrayList = (ArrayList<ProfileModel.SelfRegistionDtlsBean>) profileModel.getSelfRegistion_Dtls();

                        if (studentDtlsArrayList != null) {

                           // funProfilePic("profilePic");


                        } else {
                            Snackbar.make(lytCoordnate, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    } else if (profileModel.getStatus() == 0) {
                        navg_progress.dismiss();
                        Snackbar.make(lytCoordnate, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Error: " + e.getMessage());

                    Snackbar.make(lytCoordnate, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    navg_progress.hide();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                lytCoordnate.setVisibility(View.GONE);
                navg_progress.hide();
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest);

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to exit SASHA App");
        alertDialogBuilder.setPositiveButton("NO",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();
                    }
                });

        alertDialogBuilder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //super.onBackPressed();
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }



    private void funNotices() {


        String url = pref.getURL() + URLEndPoints.getNoticesURL(studCenterCode,studDepartmentNumber,student_id,studBranchStandardId);

        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    NoticesModel  noticesModel = gson.fromJson(response, NoticesModel.class);

                    if (noticesModel.getStatus() == 1) {

                        noticesArrayList = (ArrayList<NoticesModel.DataBean>) noticesModel.getData();

                       // funGetDownloadLink();

                    } else {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mProgressDialog.hide();
                        Snackbar.make(lytCoordnate, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (Exception e) {
                    mProgressDialog.hide();
                    //Snackbar.make(lytCoordnate, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                    //        .setAction("Action", null).show();
                  //  Log.d(TAG, "Error : " + e.getMessage());


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressDialog.hide();
                handleVolleyError(error);
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);


    }

    private void funGetDownloadLink() {

        noticesArrayListupdated = new ArrayList<>();

        newsArrayList = new ArrayList<>();
        eventsArrayList = new ArrayList<>();
        videosArrayList = new ArrayList<>();
        galleryArrayList = new ArrayList<>();

        for (int i = 0; i < noticesArrayList.size(); i++) {

            NoticesModel.DataBean data = new NoticesModel.DataBean();
            String ATTACHMENT_SOURCE = noticesArrayList.get(i).getATTACHMENT_SOURCE();
            String ATTACHMENT_LINK = noticesArrayList.get(i).getATTACHMENT_LINK();

            if(ATTACHMENT_SOURCE.equalsIgnoreCase("")){

            }else if(ATTACHMENT_SOURCE.equalsIgnoreCase("")){

            }



            String APP_NOTIFICATION = noticesArrayList.get(i).getAPP_NOTIFICATION();

            if (ATTACHMENT_SOURCE.equalsIgnoreCase("OFFLINE")) {
                ATTACHMENT_LINK = noticesArrayList.get(i).getATTACHMENT_LINK();
                String str = pref.getURL();          //http://117.247.82.252:500/api/
                String[] s = str.split(Pattern.quote("api"));
                ATTACHMENT_LINK = s[0] + ATTACHMENT_LINK;
                Log.d("download URL : ", ATTACHMENT_LINK);
            }

            String ATTACHMENT_NAME = noticesArrayList.get(i).getATTACHMENT_NAME();
            ATTACHMENT_SOURCE = noticesArrayList.get(i).getATTACHMENT_SOURCE();
            String ATTACHMENT_TYPE = noticesArrayList.get(i).getATTACHMENT_TYPE();
            String GROUP_NAMEE = noticesArrayList.get(i).getGROUP_NAME();
            String MARQUEE = noticesArrayList.get(i).getMARQUEE();
            String NOTC_ATTACHMENT_DTLS_ID = noticesArrayList.get(i).getNOTC_ATTACHMENT_DTLS_ID();
            String NOTC_DISPLAY_PATTRN_ID = noticesArrayList.get(i).getNOTC_DISPLAY_PATTRN_ID();
            String NOTC_GROUP_MASTER_ID = noticesArrayList.get(i).getNOTC_GROUP_MASTER_ID();
            String NOTC_MASTER_ID = noticesArrayList.get(i).getNOTC_MASTER_ID();
            String NOTICE_DESCRIPTION = noticesArrayList.get(i).getNOTICE_DESCRIPTION();
            String NOTICE_HEADER = noticesArrayList.get(i).getNOTICE_HEADER();
            String NOTICE_PATTERN = noticesArrayList.get(i).getNOTICE_PATTERN();
            String SOFT_COPY_NAME = noticesArrayList.get(i).getSOFT_COPY_NAME();
            String SOFT_COPY_PATH = noticesArrayList.get(i).getSOFT_COPY_PATH();
            String SOFT_COPY_SIZE = noticesArrayList.get(i).getSOFT_COPY_SIZE();
            String STANDARD_PATH = noticesArrayList.get(i).getSTANDARD_PATH();


            data.setAPP_NOTIFICATION(APP_NOTIFICATION);
            data.setATTACHMENT_LINK(ATTACHMENT_LINK);
            data.setATTACHMENT_NAME(ATTACHMENT_NAME);
            data.setATTACHMENT_SOURCE(ATTACHMENT_SOURCE);
            data.setATTACHMENT_TYPE(ATTACHMENT_TYPE);
            data.setGROUP_NAME(GROUP_NAMEE);
            data.setMARQUEE(MARQUEE);
            data.setNOTC_ATTACHMENT_DTLS_ID(NOTC_ATTACHMENT_DTLS_ID);
            data.setNOTC_DISPLAY_PATTRN_ID(NOTC_DISPLAY_PATTRN_ID);
            data.setNOTC_GROUP_MASTER_ID(NOTC_GROUP_MASTER_ID);
            data.setNOTC_MASTER_ID(NOTC_MASTER_ID);
            data.setNOTICE_DESCRIPTION(NOTICE_DESCRIPTION);
            data.setNOTICE_HEADER(NOTICE_HEADER);
            data.setNOTICE_PATTERN(NOTICE_PATTERN);
            data.setSOFT_COPY_NAME(SOFT_COPY_NAME);
            data.setSOFT_COPY_PATH(SOFT_COPY_PATH);
            data.setSOFT_COPY_SIZE(SOFT_COPY_SIZE);
            data.setSTANDARD_PATH("\\\\" + STANDARD_PATH);

            noticesArrayListupdated.add(data);


        }

        if (noticesArrayListupdated.size() != 0 && noticesArrayListupdated != null) {

            // noticesArrayList=noticesArrayListupdated;

            for (int i = 0; i < noticesArrayListupdated.size(); i++) {

                String NOTICE_PATTERN = noticesArrayListupdated.get(i).getNOTICE_PATTERN();

                if (NOTICE_PATTERN.equalsIgnoreCase("NEWSBOX")) {

                    newsArrayList.add(noticesArrayListupdated.get(i));

                } else if (NOTICE_PATTERN.equalsIgnoreCase("EVENTBOX")) {

                    eventsArrayList.add(noticesArrayListupdated.get(i));

                } else if (NOTICE_PATTERN.equalsIgnoreCase("VIDEOBOX")) {

                    videosArrayList.add(noticesArrayListupdated.get(i));

                } else if (NOTICE_PATTERN.equalsIgnoreCase("GALLERYBOX")) {

                    galleryArrayList.add(noticesArrayListupdated.get(i));

                }

            }

            //funArrangeData();

        } else {
            mProgressDialog.dismiss();
            mSwipeRefreshLayout.setRefreshing(false);
        }


    }


    private void funArrangeData() {

        if (newsArrayList != null && newsArrayList.size() != 0) {
            listDataHeaderNews = new ArrayList<String>();

            for (int i = 0; i < newsArrayList.size(); i++) {

                String GROUP_NAME = newsArrayList.get(i).getGROUP_NAME();
                listDataHeaderNews.add(GROUP_NAME);
            }
            Object[] st = listDataHeaderNews.toArray();
            for (Object s : st) {
                if (listDataHeaderNews.indexOf(s) != listDataHeaderNews.lastIndexOf(s)) {
                    listDataHeaderNews.remove(listDataHeaderNews.lastIndexOf(s));
                }
            }
            for (String ListDataHeader : listDataHeaderNews) {
                Log.d(TAG, "ListDataHeader :  " + ListDataHeader);
            }

        }

        if (eventsArrayList != null && eventsArrayList.size() != 0) {
            listDataHeaderEvents = new ArrayList<String>();

            for (int i = 0; i < eventsArrayList.size(); i++) {

                String GROUP_NAME = eventsArrayList.get(i).getGROUP_NAME();
                listDataHeaderEvents.add(GROUP_NAME);
            }
            Object[] st = listDataHeaderEvents.toArray();
            for (Object s : st) {
                if (listDataHeaderEvents.indexOf(s) != listDataHeaderEvents.lastIndexOf(s)) {
                    listDataHeaderEvents.remove(listDataHeaderEvents.lastIndexOf(s));
                }
            }
            for (String ListDataHeader : listDataHeaderEvents) {
                Log.d(TAG, "ListDataHeader :  " + ListDataHeader);
            }

        }


        if (videosArrayList != null && videosArrayList.size() != 0) {
            listDataHeaderVideos = new ArrayList<String>();

            for (int i = 0; i < videosArrayList.size(); i++) {

                String GROUP_NAME = videosArrayList.get(i).getGROUP_NAME();
                listDataHeaderVideos.add(GROUP_NAME);
            }
            Object[] st = listDataHeaderVideos.toArray();
            for (Object s : st) {
                if (listDataHeaderVideos.indexOf(s) != listDataHeaderVideos.lastIndexOf(s)) {
                    listDataHeaderVideos.remove(listDataHeaderVideos.lastIndexOf(s));
                }
            }
            for (String ListDataHeader : listDataHeaderVideos) {
                Log.d(TAG, "ListDataHeader :  " + ListDataHeader);
            }

        }
        if (galleryArrayList != null && galleryArrayList.size() != 0) {
            listDataHeaderGallery = new ArrayList<String>();

            for (int i = 0; i < galleryArrayList.size(); i++) {

                String GROUP_NAME = galleryArrayList.get(i).getGROUP_NAME();
                listDataHeaderGallery.add(GROUP_NAME);
            }
            Object[] st = listDataHeaderGallery.toArray();
            for (Object s : st) {
                if (listDataHeaderGallery.indexOf(s) != listDataHeaderGallery.lastIndexOf(s)) {
                    listDataHeaderGallery.remove(listDataHeaderGallery.lastIndexOf(s));
                }
            }
            for (String ListDataHeader : listDataHeaderGallery) {
                Log.d(TAG, "ListDataHeader :  " + ListDataHeader);
            }
        }

        mProgressDialog.dismiss();
        mSwipeRefreshLayout.setRefreshing(false);
        if (noticesArrayList != null && noticesArrayList.size() != 0) {
            eventRecyVwAdpter = new EventRecyVwAdpter(NavigationActivity.this, listDataHeaderEvents, eventsArrayList);
            newsRecyVwAdpter = new NewsRecyVwAdpter(NavigationActivity.this, listDataHeaderNews, newsArrayList);
            videoRecyVwAdpter = new VideoRecyVwAdpter(NavigationActivity.this, listDataHeaderVideos, videosArrayList);
            galleryRecyVwAdpter = new GalleryRecyVwAdpter(NavigationActivity.this, listDataHeaderGallery, galleryArrayList);

            if (fromActivity.equalsIgnoreCase("VerifyActivity") || fromActivity.equalsIgnoreCase("YoutubeListActivity")) {
                /*fragment = new HomeFragment();
                loadFragment(fragment);*/

                fragment = new New_Dashbord_Fragment();
                loadFragment(fragment);
            } else {
                fragment = new DashboardFragment();
                loadFragment(fragment);
            }

            // stopping swipe refresh

        } else {
            mProgressDialog.dismiss();
           /* fragment = new HomeFragment();
            loadFragment(fragment);*/
            // stopping swipe refresh
          //  mSwipeRefreshLayout.setRefreshing(false);

            fragment = new New_Dashbord_Fragment();
            loadFragment(fragment);
        }

    }
   /* private void addDrawerItems() {

        mExpandableListAdapter = new CustomExpandableListAdapter(this, mExpandableListTitle, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(mExpandableListTitle.get(groupPosition).toString());
            }
        });

        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String selectedItem = ((List) (mExpandableListData.get(mExpandableListTitle.get(groupPosition))))
                        .get(childPosition).toString();
                getSupportActionBar().setTitle(selectedItem);

                if (items[0].equals(mExpandableListTitle.get(groupPosition).equalsIgnoreCase("Dashboard"))) {

                   // mNavigationManager.showFragmentAction(selectedItem);
                } else if (items[1].equals(mExpandableListTitle.get(groupPosition).equalsIgnoreCase("Attendance"))) {
                   // mNavigationManager.showFragmentComedy(selectedItem);
                } else if (items[2].equals(mExpandableListTitle.get(groupPosition).equalsIgnoreCase("Syllabus"))) {
                   // mNavigationManager.showFragmentDrama(selectedItem);
                } else if (items[3].equals(mExpandableListTitle.get(groupPosition).equalsIgnoreCase("Schedule"))) {
                   // mNavigationManager.showFragmentMusical(selectedItem);
                } else if (items[4].equals(mExpandableListTitle.get(groupPosition).equalsIgnoreCase("Fees"))) {
                   // mNavigationManager.showFragmentThriller(selectedItem);
                }else if (items[4].equals(mExpandableListTitle.get(groupPosition).equalsIgnoreCase("E-Learning"))) {
                    // mNavigationManager.showFragmentThriller(selectedItem);
                } else {
                    throw new IllegalArgumentException("Not supported fragment type");
                }

                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
    private void initItems() {
        items = getResources().getStringArray(R.array.film_genre);
    }*/





    private void showCustomTooltip(@NonNull View anchor) {
        View content = getLayoutInflater().inflate(R.layout.custom_notification_dilog, null);
        mHandler=new Handler();
        Resources res = getResources();
        TooltipOpen =true;
        tooltipSize = res.getDimensionPixelOffset(R.dimen.one_dp);
        tooltipColor = ContextCompat.getColor(this, R.color.reddish);
        tooltipPadding = res.getDimensionPixelOffset(R.dimen.one_dp);
        TextView txt_wellDone,txt_welldone_date,txt_wellDoneViewAll,txt_wellD_count;
        TextView txt_info,txt_info_Date,txt_infoViewAll,txt_info_count;
        TextView txt_Warning,txt_Warning_Date,txt_WarningViewAll,txt_Warning_count;
        TextView txt_Approvals,txt_Approvals_Date,txt_ApprovalsViewAll,txt_ApprovalsCount;
        TextView txt_Alter,txt_Alter_Date,txt_AlterViewAll,txt_Alter_count;
        tipSizeSmall = res.getDimensionPixelSize(R.dimen.one_dp);
        tipSizeRegular = res.getDimensionPixelSize(R.dimen.one_dp);

        txt_wellDone  = (TextView) content.findViewById(R.id.txt_wellDone);
        txt_wellD_count  = (TextView) content.findViewById(R.id.txt_wellD_count);
        txt_info  = (TextView) content.findViewById(R.id.txt_info);
        txt_info_count  = (TextView) content.findViewById(R.id.txt_info_count);
        txt_Warning  = (TextView) content.findViewById(R.id.txt_Warning);
        txt_Warning_count  = (TextView) content.findViewById(R.id.txt_Warning_count);
        txt_Approvals  = (TextView) content.findViewById(R.id.txt_Approvals);
        txt_ApprovalsCount  = (TextView) content.findViewById(R.id.txt_ApprovalsCount);
        txt_Alter= (TextView) content.findViewById(R.id.txt_Alter);
        txt_Alter_count= (TextView) content.findViewById(R.id.txt_Alter_count);
        txt_wellD_count.setText(Constance_WellDoneVale);
        txt_info_count.setText(Constance_InfoVale);
        txt_ApprovalsCount.setText(Constance_ApprovalVale);
        txt_Warning_count.setText(Constance_WARNINGVale);
        txt_Alter_count.setText(Constance_AlertVale);

        customTooltip = new Tooltip.Builder(this)
                .anchor(anchor, Tooltip.BOTTOM)
                .animate(new TooltipAnimation(TooltipAnimation.SCALE_AND_FADE, 400))
                .autoAdjust(true)
                .withPadding(tooltipPadding)
                .content(content)
                .cancelable(true)
                .checkForPreDraw(true)
                .withTip(new Tooltip.Tip(tipSizeRegular, tipSizeRegular, tooltipColor, tipRadius))
                .into(relative_layoutMain)
                .debug(true)
                .show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TooltipOpen  =false;
                customTooltip.dismiss();
                TooltipOpen  =false;
            }

        }, 5000);

        content.findViewById(R.id.lay_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TooltipOpen =false;
                customTooltip.dismiss(true);
            }
        });
        content.findViewById(R.id.lay_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PassValue ="INFO";
                    TotalCount = Constance_InfoVale;
                    URLEndPoints.Constance_JobName =PassValue;
                    if(!TotalCount.equalsIgnoreCase("0")){
                        Intent  intent1 = new Intent(NavigationActivity.this, AlertInfoNotificationActivity.class);
                        intent1.putExtra("NamePassVal", PassValue);
                        startActivity(intent1);
                    }else {
                        Snackbar.make(relative_layoutMain, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                TooltipOpen =false;

            }
        });

        content.findViewById(R.id.lay_wellDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TooltipOpen =false;

                PassValue ="WELLDONE";
                TotalCount = Constance_WellDoneVale;
                URLEndPoints.Constance_JobName =PassValue;
                if(!TotalCount.equalsIgnoreCase("0")){
                    Intent  intent2 = new Intent(NavigationActivity.this, AlertInfoNotificationActivity.class);
                    intent2.putExtra("NamePassVal", PassValue);
                    startActivity(intent2);
                }else {
                    Snackbar.make(relative_layoutMain, "Record not available.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        content.findViewById(R.id.lay_Approvals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TooltipOpen =false;
                TotalCount = Constance_ApprovalVale;

                URLEndPoints.Constance_JobName =PassValue;

                if(!TotalCount.equalsIgnoreCase("0")){
                    Intent  intent3 = new Intent(NavigationActivity.this, AlertInfoNotificationActivity.class);
                    intent3.putExtra("NamePassVal", PassValue);
                    startActivity(intent3);
                }else {
                    Snackbar.make(relative_layoutMain, "Record not available.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        content.findViewById(R.id.lay_Warning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TooltipOpen =false;
                PassValue ="WARNING";
                TotalCount = Constance_WARNINGVale;

                URLEndPoints.Constance_JobName =PassValue;

                if(!TotalCount.equalsIgnoreCase("0")){
                    Intent  intent4 = new Intent(NavigationActivity.this, AlertInfoNotificationActivity.class);
                    intent4.putExtra("NamePassVal", PassValue);
                    startActivity(intent4);
                }else {
                    Snackbar.make(relative_layoutMain, "Record not available.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        content.findViewById(R.id.lay_Alert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TooltipOpen =false;
                    PassValue ="ALERT";
                    TotalCount = Constance_AlertVale;
                    URLEndPoints.Constance_JobName =PassValue;

                    if(!TotalCount.equalsIgnoreCase("0")){
                        Intent  intent5 = new Intent(NavigationActivity.this, AlertInfoNotificationActivity.class);
                        intent5.putExtra("NamePassVal", PassValue);
                        startActivity(intent5);
                    }else {
                        Snackbar.make(relative_layoutMain, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }

}
