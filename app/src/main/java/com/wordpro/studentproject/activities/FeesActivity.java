package com.wordpro.studentproject.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.wordpro.studentproject.activities.Payables.AcadFeesActivity;
import com.wordpro.studentproject.activities.Payables.CompleteFeesActivity;
import com.wordpro.studentproject.activities.Payables.NonAcademicFeeActivity;
import com.wordpro.studentproject.adapter.AcadFeesAdpter;
import com.wordpro.studentproject.adapter.FeesHeaderAdapterClass;
import com.wordpro.studentproject.adapter.FeesTopHeaderNameAdapterClass;
import com.wordpro.studentproject.adapter.Fees_New_StructureAdpter;
import com.wordpro.studentproject.adapter.NonAcadFeeAdpter;
import com.wordpro.studentproject.adapter.SubjectDayWiseAttendanceAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.fragment.FragPayables;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.AcademicFeeModel;
import com.wordpro.studentproject.model.FeesChildModel;
import com.wordpro.studentproject.model.FeesDataList;
import com.wordpro.studentproject.model.FeesMainDataMode;
import com.wordpro.studentproject.model.FeesParentModel;
import com.wordpro.studentproject.model.MainNoticPatten;
import com.wordpro.studentproject.model.NonAcadFeesModel;
import com.wordpro.studentproject.model.NoticPattenSub;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.wordpro.studentproject.activities.NavigationActivity.fragPayables;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;

public class FeesActivity extends AppCompatActivity  implements View.OnClickListener {

    private CardView  card_view_CompleteFee,card_view_Academic_Fees,card_view_Non_Academic,card_view_Outstanding_Fees,card_view_Exam_Fees,card_view_sub;
    private TextView txt_CompleteFee,txt_Academic_Fees,txt_Non_Academic ,txt_Outstanding_Fees,txt_Exam_Fees;
    private TextView txt_date,txt_Fee_name,txt_totalfee,txt_Outstanding,txt_total_fee_value,txt_outVal,txt_Outsatnding_val,txt_Adju_val,txt_Adj;

    //Tuition fee
    private TextView  txt_Tu_Fee,txt_tu_out,txt_Received,txt_total_Tu_val,txt_Pending,txt_Adjustment,txt_Advance,txt_Pen_val,txt_Adj_val,txt_Adv_val;
   //Tuition fee Gov
    private TextView txt_Tu_Fee_Govt,txt_Received_Govt,txt_Out_val_Govt,txt_Pending_Govt,txt_Adjustment_Govt,txt_Advance_Govt,txt_total_Tu_val_Govt,
           txt_Pen_val_Govt,txt_Adj_val_Govt,txt_Adv_val_Govt;
    private TextView txt_Exam_Fee,txt_total_Exam_val,txt_Received_Exam,txt_Out_val_Exam,txt_Pending_Exam,txt_Adjustment_Exam,txt_Advance_Exam,
    txt_Pen_val_Exam,txt_Adj_val_Exam,txt_Adv_val_Exam;
    private  TextView txt_toolbarName;
    private ImageView back_press;
    private RecyclerView fee_recycle_layout;
    private FeesMainDataMode feesMainDataMode;


    private static String TAG = FeesActivity.class.getSimpleName();
    LinearLayout lytAcadFee, lytNonAcadFee, lytCmpltFee, lytFees;
    ProgressBar progBarTimeTable;
    TextView txtPayCancel,txtPayables,txt1,txt2,txt3,txt_HeaderName;
    private PrefManager pref;
    //private RelativeLayout startView;
    private LinearLayout startView;
    //Academic Fees Model
    public static AcademicFeeModel academicFeeModel;
    public static ArrayList<AcademicFeeModel.DataBean> acadFeeArrayList;
    public static ArrayList<AcademicFeeModel.DataBean> academicArraylist;
    public static ArrayList<AcademicFeeModel.DataBean> nonacademicArraylist;
    private List<FeesDataList> feesDataLists;

    private Fees_New_StructureAdpter fees_new_structureAdpter;
    private FeesHeaderAdapterClass feesHeaderAdapterClass;
    private FeesTopHeaderNameAdapterClass feesTopHeaderNameAdapterClass;
    public static AcadFeesAdpter acadFeesAdpter;
    public static AcadFeesAdpter nonacadAdapter;

    //Non Academic Fees Model
    public static NonAcadFeesModel nonAcadFeesModel;
    public static ArrayList<NonAcadFeesModel.DataBean> nonAcadFeeArrayList;
    public static NonAcadFeeAdpter nonAcadFeeAdpter;

    //Sub Menu Name
    public static final String KEYSTUAPP_FEE_ACAD = "STUAPP_FEE_ACAD";
    public static final String KEYSTUAPP_FEE_NONACAD = "STUAPP_FEE_NONACAD";
    public static final String KEYSTUAPP_FEE_CMPLTFEE = "STUAPP_FEE_CMPLTFEE";

    private LinearLayoutManager linearLayoutManager;

    private int tooltipColor;
    private int tooltipSize;
    private int tooltipPadding;
    private LinearLayout notes;

    private int tipSizeSmall;
    private int tipSizeRegular;
    private int tipRadius;
    private LinearLayout belowId;
    private UtilityClass utilityClassObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);
        pref = new PrefManager(this);
        utilityClassObj  = new  UtilityClass(this);
        init();
        cartClickOn();
    }

    private void init(){
        card_view_CompleteFee =(CardView)findViewById(R.id.card_view_CompleteFee);
        card_view_Academic_Fees =(CardView)findViewById(R.id.card_view_Academic_Fees);
        card_view_Non_Academic =(CardView)findViewById(R.id.card_view_Non_Academic);
        card_view_Outstanding_Fees =(CardView)findViewById(R.id.card_view_Outstanding_Fees);
        card_view_Exam_Fees =(CardView)findViewById(R.id.card_view_Exam_Fees);
        card_view_sub =(CardView)findViewById(R.id.card_view_sub);

        txt_toolbarName  =(TextView) findViewById(R.id.txt_toolbarName);
        back_press  =(ImageView) findViewById(R.id.back_press);
        //startView =(RelativeLayout) findViewById(R.id.startView);
        startView =(LinearLayout) findViewById(R.id.startView);
        progBarTimeTable =(ProgressBar)findViewById(R.id.progBarTimeTable);
        txt_CompleteFee  =(TextView) findViewById(R.id.txt_CompleteFee);
        txt_Academic_Fees  =(TextView) findViewById(R.id.txt_Academic_Fees);
        txt_Non_Academic  =(TextView) findViewById(R.id.txt_Non_Academic);
        txt_Outstanding_Fees  =(TextView) findViewById(R.id.txt_Outstanding_Fees);
        txt_Exam_Fees  =(TextView) findViewById(R.id.txt_Exam_Fees);

        fee_recycle_layout=(RecyclerView)findViewById(R.id.fee_recycle_layout);
        notes = (LinearLayout)findViewById(R.id.notes);

        txt_date  =(TextView) findViewById(R.id.txt_date);
        txt_Fee_name  =(TextView) findViewById(R.id.txt_Fee_name);
        txt_totalfee  =(TextView) findViewById(R.id.txt_totalfee);
        txt_Outstanding  =(TextView) findViewById(R.id.txt_Outstanding);
        txt_total_fee_value  =(TextView) findViewById(R.id.txt_total_fee_value);
        txt_outVal  =(TextView) findViewById(R.id.txt_outVal);
        txt_Outsatnding_val  =(TextView) findViewById(R.id.txt_Outsatnding_val);
        txt_Adju_val=(TextView) findViewById(R.id.txt_Adju_val);
        txt_Adj =(TextView) findViewById(R.id.txt_Adj);
        txt_HeaderName  =(TextView) findViewById(R.id.txt_HeaderName);
        belowId =(LinearLayout) findViewById(R.id.belowId);
        Typeface type_faceHeading = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Regular.ttf");

        Resources res = getResources();

        tooltipSize = res.getDimensionPixelOffset(R.dimen.four_dp);
        tooltipColor = ContextCompat.getColor(this, R.color.reddish);
        tooltipPadding = res.getDimensionPixelOffset(R.dimen.four_dp);

        tipSizeSmall = res.getDimensionPixelSize(R.dimen.four_dp);
        tipSizeRegular = res.getDimensionPixelSize(R.dimen.four_dp);

        tipRadius = res.getDimensionPixelOffset(R.dimen.four_dp);



        txt_CompleteFee.setTypeface(type_faceHeading);
        txt_Academic_Fees.setTypeface(type_faceHeading);
        txt_Non_Academic.setTypeface(type_faceHeading);
        txt_Outstanding_Fees.setTypeface(type_faceHeading);
        txt_Exam_Fees.setTypeface(type_faceHeading);
        txt_date.setTypeface(type_faceHeading);

        txt_Fee_name.setTypeface(type_faceHeading);
        txt_totalfee.setTypeface(type_faceHeading);
        txt_Outstanding.setTypeface(type_faceHeading);
        txt_total_fee_value.setTypeface(type_faceHeading);
        txt_outVal.setTypeface(type_faceHeading);
        txt_Outsatnding_val.setTypeface(type_faceHeading);
        txt_Adju_val.setTypeface(type_faceHeading);
        txt_Adj.setTypeface(type_faceHeading);

        if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
            belowId.setBackgroundColor(ContextCompat.getColor(this, R.color.text_blue));
        }else {
            belowId.setBackgroundColor(ContextCompat.getColor(this, R.color.present));
        }

        try {
            if(URLEndPoints.Constance_StudentCenterCode.equalsIgnoreCase("JDIET_SC")){
                notes.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        /*
        txt_Tu_Fee  =(TextView) findViewById(R.id.txt_Tu_Fee);
        txt_tu_out  =(TextView) findViewById(R.id.txt_tu_out);
        txt_Received  =(TextView) findViewById(R.id.txt_Received);
        txt_total_Tu_val  =(TextView) findViewById(R.id.txt_total_Tu_val);
        txt_Pending  =(TextView) findViewById(R.id.txt_Pending);

        //txt_Adjustment  =(TextView) findViewById(R.id.txt_Adjustment);
        txt_Advance  =(TextView) findViewById(R.id.txt_Advance);
        txt_Pen_val  =(TextView) findViewById(R.id.txt_Pen_val);
       // txt_Adj_val  =(TextView) findViewById(R.id.txt_Adj_val);
        txt_Adv_val  =(TextView) findViewById(R.id.txt_Adv_val);*/

//
      /*  txt_Tu_Fee_Govt  =(TextView) findViewById(R.id.txt_Tu_Fee_Govt);
        txt_Received_Govt  =(TextView) findViewById(R.id.txt_Received_Govt);
        txt_total_Tu_val_Govt =(TextView) findViewById(R.id.txt_total_Tu_val_Govt);
        txt_Out_val_Govt  =(TextView) findViewById(R.id.txt_Out_val_Govt);
        txt_Pending_Govt  =(TextView) findViewById(R.id.txt_Pending_Govt);
       // txt_Adjustment_Govt  =(TextView) findViewById(R.id.txt_Adjustment_Govt);

        txt_Advance_Govt  =(TextView) findViewById(R.id.txt_Advance_Govt);
        txt_Pen_val_Govt  =(TextView) findViewById(R.id.txt_Pen_val_Govt);
      //  txt_Adj_val_Govt  =(TextView) findViewById(R.id.txt_Adj_val_Govt);
        txt_Adv_val_Govt  =(TextView) findViewById(R.id.txt_Adv_val_Govt);

        //

        txt_Exam_Fee  =(TextView) findViewById(R.id.txt_Exam_Fee);
        txt_total_Exam_val  =(TextView) findViewById(R.id.txt_total_Exam_val);
        txt_Received_Exam  =(TextView) findViewById(R.id.txt_Received_Exam);
        txt_Out_val_Exam  =(TextView) findViewById(R.id.txt_Out_val_Exam);
        txt_Pending_Exam  =(TextView) findViewById(R.id.txt_Pending_Exam);

       // txt_Adjustment_Exam  =(TextView) findViewById(R.id.txt_Adjustment_Exam);
        txt_Advance_Exam  =(TextView) findViewById(R.id.txt_Advance_Exam);
        txt_Pen_val_Exam  =(TextView) findViewById(R.id.txt_Pen_val_Exam);
      //  txt_Adj_val_Exam  =(TextView) findViewById(R.id.txt_Adj_val_Exam);
        txt_Adv_val_Exam =(TextView) findViewById(R.id.txt_Adv_val_Exam);*/

        getFeesData("COMPLETE");

        txt_toolbarName.setText("FEES");
    }

    private void cartClickOn(){
        back_press.setOnClickListener(this);
        card_view_CompleteFee.setOnClickListener(this);
        card_view_Academic_Fees.setOnClickListener(this);
        card_view_Non_Academic.setOnClickListener(this);
        card_view_Outstanding_Fees.setOnClickListener(this);
        card_view_Exam_Fees.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.back_press:
               // onBackPressed();
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.card_view_CompleteFee:
                /*card_view_sub.setVisibility(View.VISIBLE);
                fee_recycle_layout.setVisibility(View.VISIBLE);*/
                getFeesData("COMPLETE");

                break;

            case R.id.card_view_Academic_Fees:
                /*card_view_sub.setVisibility(View.VISIBLE);
                fee_recycle_layout.setVisibility(View.VISIBLE);*/
                //getFeesDataAcademic("NONACADEMIC");
                getFeesData("ACADEMIC");

                break;
            case R.id.card_view_Non_Academic:
               /* card_view_sub.setVisibility(View.VISIBLE);
                fee_recycle_layout.setVisibility(View.VISIBLE);*/
                getFeesData("NON_ACADEMIC");

                break;
            case R.id.card_view_Outstanding_Fees:
                getFeesData("OUTSTANDING");
               /* card_view_sub.setVisibility(View.VISIBLE);
                fee_recycle_layout.setVisibility(View.VISIBLE);*/
               /* Snackbar.make(startView, "Record not available.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
*/
                break;
            case R.id.card_view_Exam_Fees:
               /* card_view_sub.setVisibility(View.GONE);
                fee_recycle_layout.setVisibility(View.GONE);*/
                //showCustomTooltip(card_view_Exam_Fees);
                getFeesData("EXAMFEES");
                Snackbar.make(startView, "Record not available.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                break;

        }
    }
    private void getFeesData(final String feeType) {

        utilityClassObj.startLoader(this,R.drawable.image_for_rotation);

        String demoStuID= "1062180080";

        String url = pref.getURL() + URLEndPoints.GetStudFees_URL(URLEndPoints.Constance_StudentID,feeType);

        Log.d(TAG, "Fees Data :  " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
               // try {

                    Gson gson = new Gson();
                    FeesMainDataMode mainNoticPatten    = gson.fromJson(response, FeesMainDataMode.class);

                    if (mainNoticPatten.getStatus() == 1) {
                        utilityClassObj.stopLoader();
                        feesDataLists = (ArrayList<FeesDataList>) mainNoticPatten.getData();

                        List<FeesParentModel> paretData = new ArrayList<>();
                        List<FeesChildModel> ChildData = new ArrayList<>();
                        double ReceivableAmt = 0;
                        double demoReceivableAmt = 0;
                        double BalanceAmt = 0;
                        double ReceiptAmt = 0;
                        double DiscountAMT=0;
                        double TotalReceivableAmt = 0;
                        double TotalBalanceAmt = 0;
                        double TotalReceiptAmt = 0;
                        double TotalDiscountAmt= 0;
                        String totalRECEIVABLE ="" ;
                        String totalBALANCE_AMT ="";
                        String totalRECEIPT_AMT ="" ;
                        String totalDiscount_Amt ="";
                        double ReceivableAmtDouble;
                        for (int  a=0;a<feesDataLists.size();a++){

                            for (int b=0;b<feesDataLists.get(a).getParent().size();b++){
                                ChildData =feesDataLists.get(a).getParent().get(b).getChild();
                                for (int d = 0; d < ChildData.size(); d++) {

                                    try {
                                       //ReceivableAmt = Integer.parseInt(ChildData.get(d).getrECEIVABLEAMOUNT());
                                       // ReceivableAmt = Integer.valueOf(ChildData.get(d).getrECEIVABLEAMOUNT());
                                        ReceivableAmtDouble = Double.parseDouble(ChildData.get(d).getrECEIVABLEAMOUNT());
                                        System.out.println(ReceivableAmtDouble);


                                        ReceivableAmt=  Double.parseDouble(ChildData.get(d).getrECEIVABLEAMOUNT());;
                                    }catch (Exception e){
                                        /*String aa = ChildData.get(d).getrECEIVABLEAMOUNT().toString();
                                        demoReceivableAmt = Float.valueOf(aa);

                                        ReceivableAmt =Integer.parseInt(String.valueOf(demoReceivableAmt));*/

                                    }

                                    BalanceAmt = Double.parseDouble(ChildData.get(d).getbALANCEAMOUNT());
                                    ReceiptAmt=Double.parseDouble(ChildData.get(d).getfEERECEIPTAMOUNT());
                                    //ReceiptAmt = Integer.parseInt(ChildData.get(d).getfEERECEIPTAMOUNT());
                                    DiscountAMT =Double.parseDouble(ChildData.get(d).getdISCOUNTAMOUNT());
                                    TotalReceivableAmt = TotalReceivableAmt + ReceivableAmt;
                                    TotalBalanceAmt = TotalBalanceAmt + BalanceAmt;
                                    TotalReceiptAmt = TotalReceiptAmt + ReceiptAmt;
                                    TotalDiscountAmt =TotalDiscountAmt +DiscountAMT;
                                }


                                totalRECEIVABLE = String.valueOf(TotalReceivableAmt);
                                totalBALANCE_AMT = String.valueOf(TotalBalanceAmt);
                                totalRECEIPT_AMT = String.valueOf(TotalReceiptAmt);
                                totalDiscount_Amt =String.valueOf(TotalDiscountAmt);

                            }
                        }
                        txt_Fee_name.setText("Complete Fees");
                        txt_outVal.setText(totalRECEIVABLE);
                        txt_Outsatnding_val.setText(totalBALANCE_AMT);
                        txt_total_fee_value.setText(totalRECEIPT_AMT);
                        txt_Adju_val.setText(totalDiscount_Amt);

                        card_view_sub.setVisibility(View.VISIBLE);
                        fee_recycle_layout.setVisibility(View.VISIBLE);
                        feesTopHeaderNameAdapterClass =new FeesTopHeaderNameAdapterClass(getApplicationContext(),(ArrayList<FeesDataList>)feesDataLists);
                        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                        fee_recycle_layout.setLayoutManager(linearLayoutManager);
                        fee_recycle_layout.setAdapter(feesTopHeaderNameAdapterClass);
                        feesTopHeaderNameAdapterClass.notifyDataSetChanged();
                        fee_recycle_layout.setVerticalScrollBarEnabled(true);
                    }else {
                        card_view_sub.setVisibility(View.GONE);
                        fee_recycle_layout.setVisibility(View.GONE);
                        try {

                            feesTopHeaderNameAdapterClass.notifyDataSetChanged();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        utilityClassObj.stopLoader();
                        Snackbar.make(startView, "Record not available .", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }

              /*  } catch (Exception e) {
                    card_view_sub.setVisibility(View.GONE);
                    fee_recycle_layout.setVisibility(View.GONE);
                    try {

                        feesTopHeaderNameAdapterClass.notifyDataSetChanged();
                    }catch (Exception ee){
                        e.printStackTrace();
                    }

                    Snackbar.make(startView, "Server not responding.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());

                    utilityClassObj.stopLoader();

                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                handleVolleyError(error);
                utilityClassObj.stopLoader();
               // feesTopHeaderNameAdapterClass.notifyDataSetChanged();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest);

    }

   /* private void getFeesDataAcademic(final String feeType) {

        progBarTimeTable.setVisibility(View.VISIBLE);

        String demoStuID= "1032180174";

        String url = pref.getURL() + URLEndPoints.GetStudDtls_URL(demoStuID,feeType);

        Log.d(TAG, "Fees Data :  " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    Gson gson = new Gson();

                    if(feeType.equalsIgnoreCase("NONACADEMIC")) {

                        JSONObject jsonObject = new JSONObject(response);
                        int status = jsonObject.getInt("status");
                        if (status == 1){
                            academicFeeModel = gson.fromJson(response, AcademicFeeModel.class);
                            if (academicFeeModel.getStatus() == 1) {
                                acadFeeArrayList = (ArrayList<AcademicFeeModel.DataBean>) academicFeeModel.getData();
                                if (acadFeeArrayList != null) {
                                    card_view_sub.setVisibility(View.VISIBLE);
                                    int ReceivableAmt = 0;
                                    int BalanceAmt =0;
                                    int ReceiptAmt =0;

                                    int TotalReceivableAmt =0;
                                    int TotalBalanceAmt =0;
                                    int TotalReceiptAmt =0;
                                    for (int a=0;a<acadFeeArrayList.size();a++){
                                        if (acadFeeArrayList.get(a).getFEE_TYPE_DESC().equalsIgnoreCase("STRUCTURAL FEES")){
                                            ReceivableAmt = Integer.parseInt(acadFeeArrayList.get(a).getRECEIVABLE_AMOUNT());
                                            BalanceAmt= Integer.parseInt(acadFeeArrayList.get(a).getBALANCE_AMOUNT());
                                            ReceiptAmt = Integer.parseInt(acadFeeArrayList.get(a).getFEE_RECEIPT_AMOUNT());
                                            TotalReceivableAmt = TotalReceivableAmt + ReceivableAmt;
                                            TotalBalanceAmt =TotalBalanceAmt + BalanceAmt;
                                            TotalReceiptAmt =TotalReceiptAmt+ReceiptAmt;
                                        }
                                    }
                                    txt_Fee_name.setText("Academic Fees");

                                    String totalRECEIVABLE = String.valueOf(TotalReceivableAmt);
                                    String totalBALANCE_AMT = String.valueOf(TotalBalanceAmt);
                                    String totalRECEIPT_AMT = String.valueOf(TotalReceiptAmt);

                                    txt_outVal.setText(totalRECEIVABLE);
                                    txt_Outsatnding_val.setText(totalBALANCE_AMT);
                                    txt_total_fee_value.setText(totalRECEIPT_AMT);
                                    txt_Adju_val.setText(totalBALANCE_AMT);
                                    String tesfeeType ="ACADEMIC";
                                    fees_new_structureAdpter = new Fees_New_StructureAdpter(getApplicationContext(),acadFeeArrayList,tesfeeType);
                                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                                    fee_recycle_layout.setLayoutManager(mLayoutManager);
                                    fee_recycle_layout.setAdapter(fees_new_structureAdpter);

                                } else {
                                    Snackbar.make(startView, "Record not available.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }

                            } else if (academicFeeModel.getStatus() == 0) {
                                Snackbar.make(startView, "Record not available.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        }else {
                            Snackbar.make(startView, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }
                    progBarTimeTable.setVisibility(View.GONE);
                } catch (Exception e) {

                    Snackbar.make(startView, "Server not responding.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());

                    progBarTimeTable.setVisibility(View.GONE);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                handleVolleyError(error);
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest);

    }
*/

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

      //  Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }

    private void showCustomTooltip(@NonNull View anchor) {
        View content = getLayoutInflater().inflate(R.layout.alert_info_adapter_view_layout, null);

        final Tooltip customTooltip = new Tooltip.Builder(this)
                .anchor(anchor, Tooltip.BOTTOM)
                .animate(new TooltipAnimation(TooltipAnimation.SCALE_AND_FADE, 400))
                .autoAdjust(true)
                .withPadding(tooltipPadding)
                .content(content)
                .cancelable(false)
                .checkForPreDraw(true)
                .withTip(new Tooltip.Tip(tipSizeRegular, tipSizeRegular, tooltipColor, tipRadius))
                .into(startView)
                .debug(true)
                .show();

       /* content.findViewById(R.id.dismiss_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customTooltip.dismiss(true);
            }
        });*/

    }

}
