package com.wordpro.studentproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.DaywiseAttndModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SubjectDayWiseAttendanceAdpter extends RecyclerView.Adapter<SubjectDayWiseAttendanceAdpter.ViewHolder>{

    Context Mcontext;
    public static DaywiseAttndModel daywiseAttndModel;
    public static ArrayList<DaywiseAttndModel.DataBean> daywiseArrayList;

    public SubjectDayWiseAttendanceAdpter(Context context, ArrayList<DaywiseAttndModel.DataBean> pendingJobDetails) {
        this.Mcontext =context;
        this.daywiseArrayList =pendingJobDetails;
    }

    @NonNull
    @Override
    public SubjectDayWiseAttendanceAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.subject_wise_details_list, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int postion) {

       // holder.userName.setText(daywiseArrayList.get(postion).getSTUDENT_NAME());


        holder.txt_time.setText(daywiseArrayList.get(postion).getPERIOD_FROM_TIME() +" "+ "to"+" "+daywiseArrayList.get(postion).getPERIOD_UPTO_TIME());

       String th = daywiseArrayList.get(postion).getTYPE_DESCRIPTION();
       String Re =daywiseArrayList.get(postion).getPERIOD_TYPE();
        th.substring(0, 1).toUpperCase();
        th.substring(1).toLowerCase();

        Re.substring(0, 1).toUpperCase();
        Re.substring(1).toLowerCase();


        System.out.println(th);
        System.out.println(Re);

        holder.txt_Theory.setText("[ "+th+" ]");
        holder.txt_Regular.setText("[ "+Re+" ]");

       /* holder.txt_Theory.setText("[ "+th+" ]");
        holder.txt_Regular.setText("[ "+Re+" ]");
*/
        String dateStr = daywiseArrayList.get(postion).getCommonDate();


        holder.txt_date.setText(dateStr);

        String ATTENDANCE_LOCK=daywiseArrayList.get(postion).getATTENDANCE_LOCK();
        if(ATTENDANCE_LOCK.equalsIgnoreCase("N")){
            holder.txt_present.setText("NOT UPDATED");
            // holder.txt_present.setBackgroundResource(R.color.UnMarkAttend);
            holder.txt_present.setTextColor(Color.parseColor("#f26c4f"));
            holder.txt_name.setText(" ");
            holder.src_img.setBackgroundResource(R.drawable.icon_warning);
            holder.src_img.setImageDrawable(ContextCompat.getDrawable(Mcontext, R.drawable.icon_warning));
            // holder.txt_present.setTextColor(R.color.UnMarkAttend);
        }else if(ATTENDANCE_LOCK.equalsIgnoreCase("Y")){

            String STUDENT_STATUS=daywiseArrayList.get(postion).getSTUDENT_STATUS();
            String ATTENDANCE_STATUS=daywiseArrayList.get(postion).getATTENDANCE_STATUS();
            if(ATTENDANCE_STATUS.equalsIgnoreCase("PERIOD_TAKEN")){
                if(STUDENT_STATUS.equalsIgnoreCase("ABSENT")){
                    holder.txt_present.setText("ABSENT");
                    holder.txt_present.setTextColor(Color.parseColor("#f26c4f"));
                    holder.src_img.setBackgroundResource(R.drawable.icon_close);
                    holder.src_img.setImageDrawable(ContextCompat.getDrawable(Mcontext, R.drawable.icon_close));
                    holder.txt_name.setText(daywiseArrayList.get(postion).getTOPIC_DESCRIPTION());
                }else if(STUDENT_STATUS.equalsIgnoreCase("PRESENT")){
                    holder.txt_present.setText("PRESENT");
                    holder.txt_present.setTextColor(Color.parseColor("#39b54a"));
                    holder.src_img.setBackgroundResource(R.drawable.icon_pre);
                    holder.txt_name.setText(daywiseArrayList.get(postion).getTOPIC_DESCRIPTION());
                    holder.src_img.setImageDrawable(ContextCompat.getDrawable(Mcontext, R.drawable.icon_pre));
                }else if(STUDENT_STATUS.equalsIgnoreCase("C-work")){
                    holder.txt_present.setText("COLLEGE_WORK");
                    holder.txt_present.setTextColor(Color.parseColor("#00aeef"));
                    holder.src_img.setBackgroundResource(R.drawable.icon_work);
                    holder.src_img.setImageDrawable(ContextCompat.getDrawable(Mcontext, R.drawable.icon_work));
                    holder.txt_name.setText(" ");
                }else if(STUDENT_STATUS.equalsIgnoreCase("S-LEAVE")){
                    holder.txt_present.setText("LEAVE");
                    holder.txt_present.setTextColor(Color.parseColor("#f26c4f"));
                    holder.src_img.setBackgroundResource(R.drawable.icon_holidy_user);
                    holder.src_img.setImageDrawable(ContextCompat.getDrawable(Mcontext, R.drawable.icon_holidy_user));
                    holder.txt_name.setText(" ");
                }
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("COLLEGE_WORK")){
                holder.txt_present.setText("COLLEGE_WORK");
                holder.txt_present.setTextColor(Color.parseColor("#00aeef"));
                holder.src_img.setBackgroundResource(R.drawable.icon_work);
                holder.src_img.setImageDrawable(ContextCompat.getDrawable(Mcontext, R.drawable.icon_work));
                holder.txt_name.setText(" ");
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("BUNK")){
                holder.txt_present.setText("ABSENT");
                holder.txt_present.setTextColor(Color.parseColor("#f26c4f"));
                holder.src_img.setBackgroundResource(R.drawable.icon_close);
                holder.src_img.setImageDrawable(ContextCompat.getDrawable(Mcontext, R.drawable.icon_close));
                holder.txt_name.setText(daywiseArrayList.get(postion).getTOPIC_DESCRIPTION());

            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("COLLEGE_EVENT")){
                holder.txt_present.setText("COLLEGE_EVENT");
                holder.txt_present.setTextColor(Color.parseColor("#00aeef"));
                holder.src_img.setBackgroundResource(R.drawable.icon_work);
                holder.src_img.setImageDrawable(ContextCompat.getDrawable(Mcontext, R.drawable.icon_work));
                holder.txt_name.setText(" ");
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("HOLIDAY")){
                holder.txt_present.setText("TEACHER ON LEAVE");
                holder.txt_present.setTextColor(Color.parseColor("#f26c4f"));
                holder.src_img.setBackgroundResource(R.drawable.icon_holidy_user);
                holder.src_img.setImageDrawable(ContextCompat.getDrawable(Mcontext, R.drawable.icon_holidy_user));
                holder.txt_name.setText(" ");
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("ABSENT") && STUDENT_STATUS.equalsIgnoreCase("")){
                holder.txt_present.setText("ABSENT");
                holder.txt_present.setTextColor(Color.parseColor("#f26c4f"));
                holder.src_img.setBackgroundResource(R.drawable.icon_close);
                holder.src_img.setImageDrawable(ContextCompat.getDrawable(Mcontext, R.drawable.icon_close));
                holder.txt_name.setText(daywiseArrayList.get(postion).getTOPIC_DESCRIPTION());
            }

        }


    }

    @Override
    public int getItemCount() {
        return daywiseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  txt_present,txt_name,txt_Theory,txt_Regular,txt_date,wise_attendance,wise_Value,userName,txt_basic,txt_time;
        private ImageView src_img;
        private RecyclerView even_img_recyclerView;
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
            src_img= (ImageView) itemView.findViewById(R.id.src_img);

            txt_present.setTypeface(type_faceHeading);
            txt_date.setTypeface(type_faceHeading);
            txt_name.setTypeface(type_faceContent);
            txt_time.setTypeface(type_faceContent);
            txt_Theory.setTypeface(type_faceHeading);
            txt_Regular.setTypeface(type_faceHeading);
            userName.setTypeface(type_faceHeading);
        }
    }

}
