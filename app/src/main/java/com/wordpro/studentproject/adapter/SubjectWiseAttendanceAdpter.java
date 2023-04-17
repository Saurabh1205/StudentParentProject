package com.wordpro.studentproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.SubjectwiseFacultyModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SubjectWiseAttendanceAdpter extends RecyclerView.Adapter<SubjectWiseAttendanceAdpter.ViewHolder>{

    Context Mcontext;
    public static DaywiseAttndModel daywiseAttndModel;
    public static ArrayList<SubjectwiseFacultyModel.DataBean> subjectWiseData;
    String SubjectDetailsId,SubName,SubSHortName;
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    private SubjectWiseAttendanceAdpter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }
    public SubjectWiseAttendanceAdpter(Context context, ArrayList<SubjectwiseFacultyModel.DataBean> pendingJobDetails) {
        this.Mcontext =context;
        this.subjectWiseData =pendingJobDetails;


    }

    @NonNull
    @Override
    public SubjectWiseAttendanceAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.subject_wise_all_list_dashbord, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int postion) {

        String userName = subjectWiseData.get(postion).getPROF_EMP_FN_MN_SHORT().toLowerCase();
        String subDes = subjectWiseData.get(postion).getSUBJECT_DESCRIPTION().toLowerCase();
        Log.e("data","iss==="+subDes);
        String[] strArray = userName.split("[  .]");
        StringBuilder builder = new StringBuilder();
        for (String s : strArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            builder.append(cap + " ");
        }
//        String[] strArray1 = subDes.split(" ");
//        StringBuilder builder1 = new StringBuilder();
//        for (String s : strArray1) {
//            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
//            builder1.append(cap + " ");
//        }


        holder.userName.setText(builder);
        // holder.userName.setText(WordUtils.capitalize(userName));
        holder.txt_name.setText(subDes);
       String th = subjectWiseData.get(postion).getTYPE_DESCRIPTION();
       //String Re =subjectWiseData.get(postion).getPERIOD_TYPE();

       // th.toLowerCase();
       // System.out.println(th);
       // Re.toLowerCase();
      //  System.out.println(Re);
        if (subjectWiseData.get(postion).getAPPLICABLE_NUMBER().equalsIgnoreCase("1")){
            holder.txt_Theory.setVisibility(View.VISIBLE);
        }else  if (subjectWiseData.get(postion).getAPPLICABLE_NUMBER().equalsIgnoreCase("2")){
            holder.txt_Regular.setVisibility(View.VISIBLE);
        }

        holder.txt_Theory.setText("[ "+th+" ]");
       // holder.txt_Regular.setText("[ "+Re+" ]");

       /* holder.txt_Theory.setText("[ "+th+" ]");
        holder.txt_Regular.setText("[ "+Re+" ]");
*/
        //String dateStr = daywiseArrayList.get(postion).getATTENDANCE_DATE();

        try {
            DateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
            Date d = f.parse("");
            DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat time = new SimpleDateFormat("hh:mm:ss a");

            System.out.println("Date: " + date.format(d));
            System.out.println("Time: " + time.format(d));
            holder.txt_date.setText(date.format(d));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SubjectDetailsId = subjectWiseData.get(postion).getSUBJECT_DETAIL_ID();
        SubName = subjectWiseData.get(postion).getSUBJECT_DESCRIPTION();
        SubSHortName = subjectWiseData.get(postion).getPROF_EMP_FN_MN_SHORT();


        holder.card_view_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (mCallbacks != null) {

                    if (postion != RecyclerView.NO_POSITION) {
                        mCallbacks.onItemClick(v, postion);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return subjectWiseData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  txt_present,txt_name,txt_Theory,txt_Regular,txt_date,wise_attendance,wise_Value,userName,txt_basic,txt_time;
        private ImageView gallery_img;
        private RecyclerView even_img_recyclerView;
        private CardView  card_view_sub;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");

            txt_present= (TextView) itemView.findViewById(R.id.txt_present);
            txt_name= (TextView)itemView.findViewById(R.id.txt_name);
            txt_time= (TextView)itemView.findViewById(R.id.txt_time);
            txt_Theory= (TextView)itemView.findViewById(R.id.txt_Theory);
            txt_Regular = (TextView)itemView.findViewById(R.id.txt_Regular);
            txt_date= (TextView)itemView.findViewById(R.id.txt_date);
            wise_attendance= (TextView)itemView.findViewById(R.id.wise_attendance);
            wise_Value  = (TextView)itemView.findViewById(R.id.wise_Value);
            userName  = (TextView)itemView.findViewById(R.id.userName);
            txt_basic  = (TextView)itemView.findViewById(R.id.txt_basic);
            card_view_sub  = (CardView) itemView.findViewById(R.id.card_view_sub);

            txt_present.setTypeface(type_faceHeading);
            txt_date.setTypeface(type_faceHeading);
            txt_name.setTypeface(type_faceHeading);
            txt_time.setTypeface(type_faceContent);
            txt_Theory.setTypeface(type_faceHeading);
            txt_Regular.setTypeface(type_faceHeading);
            userName.setTypeface(type_faceHeading);
            wise_Value.setTypeface(type_faceHeading);
            wise_attendance.setTypeface(type_faceContent);
        }
    }

}
