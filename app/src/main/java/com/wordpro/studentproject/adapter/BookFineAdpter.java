package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.FineModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookFineAdpter extends RecyclerView.Adapter<BookFineAdpter.ViewHolder> {

    Context context;
    ArrayList<FineModel.BookFineDetailsBean> bookFineDtlsArrayList;


    public BookFineAdpter(Context context, ArrayList<FineModel.BookFineDetailsBean> bookFineDtlsArrayList) {
        this.context = context;
        this.bookFineDtlsArrayList = bookFineDtlsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_fine_row, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtBookTitle.setText(bookFineDtlsArrayList.get(position).getBOOKTITLE());
        // holder.txtExpctRtrnDt.setText(bookFineDtlsArrayList.get(position).getBookTentitiveReturningDate());
        holder.txtActualRtrnDt.setText(bookFineDtlsArrayList.get(position).getBookReturningDate());
        holder.txtFineDue.setText(bookFineDtlsArrayList.get(position).getReceivableAmount());
        holder.txtFinePaid.setText(bookFineDtlsArrayList.get(position).getActualReceivedAmt());
        holder.txtFineOutstanding.setText(bookFineDtlsArrayList.get(position).getBalanceAmt());

        String attendanceDate, day1 = null, month1 = null, year1 = null, day2 = null, month2 = null, year2 = null;

        if (bookFineDtlsArrayList.get(position).getBookTentitiveReturningDate() != null && !bookFineDtlsArrayList.get(position).getBookTentitiveReturningDate().isEmpty()) {
            String date[] = bookFineDtlsArrayList.get(position).getBookTentitiveReturningDate().split(" ");
            attendanceDate = date[0];
            String d[] = attendanceDate.split("/");
            month1 = d[0];
            day1 = d[1];
            year1 = d[2];
        }

        if (day1 != null && month1 != null && year1 != null) {
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date d = null;
            try {
                d = sdf.parse(day1 + "-" + month1 + "-" + year1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String date = df.format(d);

            holder.txtExpctRtrnDt.setText(date);
        } else {
            holder.txtExpctRtrnDt.setText("");
        }


        if (bookFineDtlsArrayList.get(position).getBookReturningDate() != null && !bookFineDtlsArrayList.get(position).getBookReturningDate().isEmpty()) {
            String date[] = bookFineDtlsArrayList.get(position).getBookReturningDate().split(" ");
            attendanceDate = date[0];
            String d[] = attendanceDate.split("/");
            month2 = d[0];
            day2 = d[1];
            year2 = d[2];
        }

        if (day2 != null && month2 != null && year2 != null) {
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date d = null;
            try {
                d = sdf.parse(day2 + "-" + month2 + "-" + year2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String date = df.format(d);

            holder.txtActualRtrnDt.setText(date);
        } else {
            holder.txtActualRtrnDt.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return bookFineDtlsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtBookTitle, txtExpctRtrnDt, txtActualRtrnDt, txtFineDue, txtFinePaid, txtFineOutstanding;

        public ViewHolder(View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            txtBookTitle = (TextView) itemView.findViewById(R.id.txtBookTitle);
            txtBookTitle.setTypeface(customTypeOne);
            txtExpctRtrnDt = (TextView) itemView.findViewById(R.id.txtExpctRtrnDt);
            txtExpctRtrnDt.setTypeface(customTypeOne);
            txtActualRtrnDt = (TextView) itemView.findViewById(R.id.txtActualRtrnDt);
            txtActualRtrnDt.setTypeface(customTypeOne);
            txtFineDue = (TextView) itemView.findViewById(R.id.txtFineDue);
            txtFineDue.setTypeface(customTypeOne);
            txtFinePaid = (TextView) itemView.findViewById(R.id.txtFinePaid);
            txtFinePaid.setTypeface(customTypeOne);
            txtFineOutstanding = (TextView) itemView.findViewById(R.id.txtFineOutstanding);
            txtFineOutstanding.setTypeface(customTypeOne);

        }
    }
}
