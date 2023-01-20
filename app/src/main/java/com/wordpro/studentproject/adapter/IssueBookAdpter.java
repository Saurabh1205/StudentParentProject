package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.IssuedBookModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class IssueBookAdpter extends RecyclerView.Adapter<IssueBookAdpter.ViewHolder>{

    Context context;
    ArrayList<IssuedBookModel.CirculationRuleDtlsBean> issueBookArrayList;
    String type;

    public IssueBookAdpter(Context context, ArrayList<IssuedBookModel.CirculationRuleDtlsBean> issueBookArrayList, String type) {
        this.context = context;
        this.issueBookArrayList = issueBookArrayList;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.issue_book_row_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (type.equalsIgnoreCase("DETAIL_VIEW")) {
            holder.imgVwBook.setVisibility(View.GONE);
        } else if (type.equalsIgnoreCase("THUMBNAIL_VIEW")) {
            holder.imgVwBook.setVisibility(View.VISIBLE);
        }


        //date
        String date = issueBookArrayList.get(position).getBOOK_ALLOCATION_DATE();
        String fromDate = "";
        if (!date.equalsIgnoreCase("") || !date.isEmpty()) {
            String d[] = date.split(" ");
            String splitdate = d[0];

            splitdate = splitdate.replace("-", "/");
            String dt[] = splitdate.split("/");
            String day = dt[1];
            String month = dt[0];
            String year = dt[2];

            Date dt1 = null;
            //holder.txtDate.setText(day + "/" + month + "/" + year);
            String input_date = day + "/" + month + "/" + year;
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            try {
                dt1 = format1.parse(input_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dayOfTheWeek = (String) DateFormat.format("EEEE", dt1); // Thursday
            String dayofMonth = (String) DateFormat.format("dd", dt1); // 20
            String monthString = (String) DateFormat.format("MMM", dt1); // Jun
            String monthNumber = (String) DateFormat.format("MM", dt1); // 06
            String years = (String) DateFormat.format("yyyy", dt1); // 2013

            //  holder.txtDay.setText(dayOfTheWeek);
          //  fromDate = dayofMonth + " " + monthString + " " + years + " " + d[1] + " " + d[2];
            fromDate = dayofMonth + " " + monthString + " " + years;
        }

        String date1 = issueBookArrayList.get(position).getBOOK_TENTITIVE_RETURNING_DATE(); //"8/1/2019 12:00:00 AM"
        String uptoDate="";

        if(!date1.equalsIgnoreCase("") || !date1.isEmpty()){

            String[] d=date1.split(Pattern.quote(" "));
            String[] d1=d[0].split(Pattern.quote("/"));
            String lastDate=d1[1]+"/"+d1[0]+"/"+d1[2]+" "+d[1]+" "+d[2];

            Date dt1 = null;
            //holder.txtDate.setText(day + "/" + month + "/" + year);
            String input_date = d1[1] + "/" + d1[0] + "/" + d1[2];
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            try {
                dt1 = format1.parse(input_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dayOfTheWeek = (String) DateFormat.format("EEEE", dt1); // Thursday
            String dayofMonth = (String) DateFormat.format("dd", dt1); // 20
            String monthString = (String) DateFormat.format("MMM", dt1); // Jun
            String monthNumber = (String) DateFormat.format("MM", dt1); // 06
            String years = (String) DateFormat.format("yyyy", dt1); // 2013

            //uptoDate = dayofMonth + " " + monthString + " " + years + " " + d[1] + " " + d[2];
            uptoDate = dayofMonth + " " + monthString + " " + years;


            Date todayDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
            String currentDtTm=format.format(todayDate);


            //Last Date :               1/8/2019 12:00:00 AM
            //current date & time :     07/08/2019 11:46:43 AM




            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
            String difference = null;

            try {
                Date startdate = simpleDateFormat.parse(lastDate);
                Date enddate = simpleDateFormat.parse(currentDtTm);
                difference=printDifference(startdate, enddate);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            String[] value=difference.split(Pattern.quote("$")); //2 days, 11 hours, 58 minutes, 22 seconds
            String day=value[0];
            String[] v=day.split(Pattern.quote(" "));
            int dayDifference= Integer.parseInt(v[0]);


            if (dayDifference > 0) {

                String BOOK_RETURNING_DATE=issueBookArrayList.get(position).getBOOK_RETURNING_DATE();   //"8/2/2019 12:00:00 AM"
                if(!BOOK_RETURNING_DATE.equalsIgnoreCase("") || !BOOK_RETURNING_DATE.isEmpty()){
                    String[] returnDt=date1.split(Pattern.quote(" "));
                    String[] returnDate=returnDt[0].split(Pattern.quote("/"));

                    SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        dt1 = format2.parse(returnDate[1]+"/"+returnDate[0]+"/"+returnDate[2]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String dayOfTheWeek2 = (String) DateFormat.format("EEEE", dt1); // Thursday
                    String dayofMonth2 = (String) DateFormat.format("dd", dt1); // 20
                    String monthString2 = (String) DateFormat.format("MMM", dt1); // Jun
                    String monthNumber2 = (String) DateFormat.format("MM", dt1); // 06
                    String years2 = (String) DateFormat.format("yyyy", dt1); // 2013

                    String returnBookDate=dayofMonth2 + " " + monthString2 + " " + years2 +" "+returnDt[1]+" "+returnDt[2];

                    holder.lytBgColor.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_round_blue));
                    holder.txtValidSpan.setTextColor(Color.parseColor("#3f03ac"));
                    holder.txtValidSpan.setText("Returned Book on :  " + returnBookDate);


                }else{
                    holder.lytBgColor.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_round_red));
                    holder.txtValidSpan.setTextColor(Color.parseColor("#ff0000"));
                    holder.txtValidSpan.setText("Overdue :  " + fromDate + "  to   " + uptoDate + " (" + dayDifference + " days)");
                }

            } else {
                holder.lytBgColor.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_round_gren));
                holder.txtValidSpan.setTextColor(Color.parseColor("#44a308"));
                holder.txtValidSpan.setText("Validity :  " + fromDate + "  to   " + uptoDate);

            }

        }else{

            holder.txtValidSpan.setText("Validity :  " + fromDate + "  to   " + uptoDate);
        }



        holder.txtBookName.setText(issueBookArrayList.get(position).getBOOK_TITLE());
        holder.txtAuthor.setText(issueBookArrayList.get(position).getCOMB_AUTH_LASTNAME());
        holder.txtBookRefernce.setText(issueBookArrayList.get(position).getBOOK_TYPE_DESCRIPTION());
        holder.txtISBNNo.setText("ISBN No : " + issueBookArrayList.get(position).getACCESSION_NUMBER());


    }

    @Override
    public int getItemCount() {
        return issueBookArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout lytBgColor;
        ImageView imgVwBook;
        TextView txtBookName, txtAuthor, txtBookRefernce, txtValidSpan, txtISBNNo;

        public ViewHolder(View itemView) {
            super(itemView);

            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            lytBgColor = (LinearLayout) itemView.findViewById(R.id.lytBgColor);
            imgVwBook = (ImageView) itemView.findViewById(R.id.imgVwBook);
            txtBookName = (TextView) itemView.findViewById(R.id.txtBookName);
            txtBookName.setTypeface(customTypeOne);
            txtAuthor = (TextView) itemView.findViewById(R.id.txtAuthor);
            txtAuthor.setTypeface(customTypeOne);
            txtBookRefernce = (TextView) itemView.findViewById(R.id.txtBookRefernce);
            txtBookRefernce.setTypeface(customTypeOne);
            txtValidSpan = (TextView) itemView.findViewById(R.id.txtValidSpan);
            txtValidSpan.setTypeface(customTypeOne);
            txtISBNNo = (TextView) itemView.findViewById(R.id.txtISBNNo);
            txtISBNNo.setTypeface(customTypeOne);

        }
    }

    //1 minute = 60 seconds
//1 hour = 60 x 60 = 3600
//1 day = 3600 x 24 = 86400
    public String printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
        String value = elapsedDays + "$" + elapsedHours + "$" + elapsedMinutes + "$" + elapsedSeconds;

        return value;
    }

}
