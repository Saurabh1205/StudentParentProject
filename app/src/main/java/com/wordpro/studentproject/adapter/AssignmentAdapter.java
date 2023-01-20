package com.wordpro.studentproject.adapter;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NavigationActivity;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.model.AssignmentModel;
import com.wordpro.studentproject.model.DownloadURLModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.github.mikephil.charting.charts.Chart.LOG_TAG;
import static com.wordpro.studentproject.fragment.FragE_Learning.studAssignDtlsArrayList;
import static com.wordpro.studentproject.fragment.PublishFragment.mProgressDialog;

/**
 * Created by wccs1980 on 14-06-2018.
 */

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {

    public static String TAG = AssignmentAdapter.class.getSimpleName();
    Context context;
    ArrayList<AssignmentModel.StudAssignDtlsBean> studAssignDtlsArrayList;
    private AssignDownUpListner assignDownUpListner;
    private SparseBooleanArray selectedItems;
    ProgressDialog progressDialog;
    DownloadURLModel downloadURLModel;
    ArrayList<DownloadURLModel.DataBean> downloadURLArrayList;
    private DownloadManager downloadManager;
    private long refid;
    private Uri Download_Uri;


    //initialize root directory
    File rootDir = Environment.getExternalStorageDirectory();

    //defining file name and url
    public String fileName = "";
    public String fileURL = "";


    public AssignmentAdapter(Context context, ArrayList<AssignmentModel.StudAssignDtlsBean> studAssignDtlsArrayList, AssignDownUpListner assignDownUpListner) {

        this.context = context;
        this.studAssignDtlsArrayList = studAssignDtlsArrayList;
        selectedItems = new SparseBooleanArray();
        this.assignDownUpListner = assignDownUpListner;


        for (int i = 0; i < studAssignDtlsArrayList.size(); i++) {

            studAssignDtlsArrayList.get(i).setSTUD_UPLOAD_FILE("");

        }


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.assign_row_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        progressDialog = new ProgressDialog(context);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String fileName = studAssignDtlsArrayList.get(position).getTASK_FILE_NAME();
        String ASSIGN_FILE_DETAILS=studAssignDtlsArrayList.get(position).getASSIGN_FILE_DETAILS();
        String duplicatefile="download file";
        /*if(!ASSIGN_FILE_DETAILS.isEmpty()){
            String[] value=ASSIGN_FILE_DETAILS.split(Pattern.quote("\\"));
            duplicatefile=value[1];
        }*/
        if (fileName.isEmpty()) {
            holder.txtDownloadFileName.setText(duplicatefile);
        } else
            holder.txtDownloadFileName.setText(fileName);

        holder.txtSubj.setText(studAssignDtlsArrayList.get(position).getSUBJECT_DESCRIPTION());
        holder.txtAssigName.setText(studAssignDtlsArrayList.get(position).getASSIGN_DESCRIPTION());
        holder.txtAssignType.setText("Submission Type : " + studAssignDtlsArrayList.get(position).getASSIGN_SUBMISSION_TYPE());
        if(studAssignDtlsArrayList.get(position).getSTUD_UPLOAD_FILE().isEmpty()){
            holder.txtFileName.setText("upload file upto 300 KB");

        }else{
            holder.txtFileName.setText(studAssignDtlsArrayList.get(position).getSTUD_UPLOAD_FILE());

        }
        String facRemark = studAssignDtlsArrayList.get(position).getEMP_REMARK();
        if (facRemark.equalsIgnoreCase(null) || facRemark.isEmpty() || facRemark.equalsIgnoreCase("")) {
            facRemark = "-";
        }
        holder.txtEmpRemark.setText("Lecturer Remark : " + facRemark);
        if (studAssignDtlsArrayList.get(position).getASSIGN_SUBMISSION_TYPE().equalsIgnoreCase("HARD COPY")) {
            holder.btnAssignSubmit.setVisibility(View.GONE);
            holder.lytUploadSoftCopy.setVisibility(View.GONE);
        } else {
            holder.btnAssignSubmit.setVisibility(View.VISIBLE);
            holder.lytUploadSoftCopy.setVisibility(View.VISIBLE);
        }

        String STU_SUBMISSION_STATUS = studAssignDtlsArrayList.get(position).getSTU_SUBMISSION_STATUS();
        if (STU_SUBMISSION_STATUS != null) {
            if (STU_SUBMISSION_STATUS.equalsIgnoreCase("SUBMITTED")) {
                holder.btnAssignSubmit.setVisibility(View.GONE);
                holder.lytUploadSoftCopy.setVisibility(View.GONE);
                holder.txtAssignLstDate.setText("Submitted Date : " + studAssignDtlsArrayList.get(position).getSUBMISSION_DATE());
                holder.txtCorrectionRemark.setVisibility(View.GONE);
                holder.txtFacltyRemark.setVisibility(View.GONE);
                holder.linLytStatus.setBackgroundResource(R.color.CEvent);

            } else if (STU_SUBMISSION_STATUS.equalsIgnoreCase("REJECTED_BY_FACULTY")) {
                holder.btnAssignSubmit.setVisibility(View.GONE);
                holder.lytUploadSoftCopy.setVisibility(View.GONE);
                holder.txtAssignLstDate.setText("Submitted Date : " + studAssignDtlsArrayList.get(position).getSUBMISSION_DATE());
                holder.txtCorrectionRemark.setVisibility(View.GONE);
                holder.txtFacltyRemark.setText("Faculty Remark : " + studAssignDtlsArrayList.get(position).getFACULTY_REMARK());
                holder.linLytStatus.setBackgroundResource(R.color.absent);

            } else if (STU_SUBMISSION_STATUS.equalsIgnoreCase("APPROVED_BY_FACULTY")) {
                holder.btnAssignSubmit.setVisibility(View.GONE);
                holder.lytUploadSoftCopy.setVisibility(View.GONE);
                holder.txtAssignLstDate.setText("Submitted Date : " + studAssignDtlsArrayList.get(position).getSUBMISSION_DATE());
                holder.txtCorrectionRemark.setVisibility(View.GONE);
                holder.txtFacltyRemark.setText("Faculty Remark : " + studAssignDtlsArrayList.get(position).getFACULTY_REMARK());
                holder.linLytStatus.setBackgroundResource(R.color.present);

            } else if (STU_SUBMISSION_STATUS.equalsIgnoreCase("SENT_FOR_CORRECTION")) {
                holder.btnAssignSubmit.setVisibility(View.VISIBLE);
                holder.lytUploadSoftCopy.setVisibility(View.VISIBLE);
                holder.txtAssignLstDate.setText("Submission Last Date : " + studAssignDtlsArrayList.get(position).getASSIGN_SUBMI_UPTO_DATE());
                holder.txtCorrectionRemark.setText("Correction Remark : " + studAssignDtlsArrayList.get(position).getCORRECTION_REMARK());
                holder.txtFacltyRemark.setText("Faculty Remark : " + studAssignDtlsArrayList.get(position).getFACULTY_REMARK());
                holder.linLytStatus.setBackgroundResource(R.color.FacultyLeave);

            } else if (STU_SUBMISSION_STATUS.equalsIgnoreCase("") || STU_SUBMISSION_STATUS.isEmpty()) {
                holder.txtAssignLstDate.setText("Submission Last Date : " + studAssignDtlsArrayList.get(position).getASSIGN_SUBMI_UPTO_DATE());
                holder.txtCorrectionRemark.setVisibility(View.GONE);
                holder.txtFacltyRemark.setVisibility(View.GONE);


            }
        }


        // apply click events
        applyClickEvents(holder, position);
    }

    private void applyClickEvents(final ViewHolder holder, final int position) {

        holder.imgVwDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Assignment Adapter", "Download click.................");
                assignDownUpListner.onDownloadClick(position);
            }
        });

        holder.imgVwUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assignDownUpListner.onUploadClick(position);
            }
        });

        holder.btnAssignSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assignDownUpListner.onSubmitAssignClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return studAssignDtlsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtSubj, txtAssigName, txtAssignType, txtAssignLstDate, txtFileName, txtEmpRemark, txtFacltyRemark, txtCorrectionRemark, txtDownloadFileName;
        ImageView imgVwDownload, imgVwUpload;
        Button btnAssignSubmit;
        RelativeLayout assignLyt;
        LinearLayout lytUploadSoftCopy, linLytStatus;

        public ViewHolder(View itemView) {

            super(itemView);

            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            txtSubj = (TextView) itemView.findViewById(R.id.txtSubj);
            txtSubj.setTypeface(customTypeOne);
            txtAssigName = (TextView) itemView.findViewById(R.id.txtAssigName);
            txtAssigName.setTypeface(customTypeOne);
            txtAssignType = (TextView) itemView.findViewById(R.id.txtAssignType);
            txtAssignType.setTypeface(customTypeOne);
            txtAssignLstDate = (TextView) itemView.findViewById(R.id.txtAssignLstDate);
            txtAssignLstDate.setTypeface(customTypeOne);
            txtFileName = (TextView) itemView.findViewById(R.id.txtFileName);
            txtFileName.setTypeface(customTypeOne);
            imgVwDownload = (ImageView) itemView.findViewById(R.id.imgVwDownload);
            imgVwUpload = (ImageView) itemView.findViewById(R.id.imgVwUpload);
            btnAssignSubmit = (Button) itemView.findViewById(R.id.btnAssignSubmit);
            btnAssignSubmit.setTypeface(customTypeOne);
            assignLyt = (RelativeLayout) itemView.findViewById(R.id.assignLyt);
            lytUploadSoftCopy = (LinearLayout) itemView.findViewById(R.id.lytUploadSoftCopy);
            txtEmpRemark = (TextView) itemView.findViewById(R.id.txtEmpRemark);
            txtEmpRemark.setTypeface(customTypeOne);
            txtFacltyRemark = (TextView) itemView.findViewById(R.id.txtFacltyRemark);
            txtFacltyRemark.setTypeface(customTypeOne);
            txtCorrectionRemark = (TextView) itemView.findViewById(R.id.txtCorrectionRemark);
            txtCorrectionRemark.setTypeface(customTypeOne);
            linLytStatus = (LinearLayout) itemView.findViewById(R.id.linLytStatus);
            txtDownloadFileName = (TextView) itemView.findViewById(R.id.txtDownloadFileName);
            txtDownloadFileName.setTypeface(customTypeOne);


        }
    }


    public interface AssignDownUpListner {

        void onDownloadClick(int position);

        void onUploadClick(int position);

        void onSubmitAssignClick(int position);
    }

}
