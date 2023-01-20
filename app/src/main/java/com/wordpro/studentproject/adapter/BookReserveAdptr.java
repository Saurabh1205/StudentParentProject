package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.BookReservtnModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookReserveAdptr extends RecyclerView.Adapter<BookReserveAdptr.ViewHolder> {

    Context context;
    ArrayList<BookReservtnModel.LibBokReserStatusBean> reservedBookArraylist;
    String type;

    public BookReserveAdptr(Context context, ArrayList<BookReservtnModel.LibBokReserStatusBean> reservedBookArraylist, String type) {
        this.context = context;
        this.reservedBookArraylist = reservedBookArraylist;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reserve_book_row, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String RESERVATION_STATUS = reservedBookArraylist.get(position).getRESERVATION_STATUS();
        holder.txtbookTitle.setText(reservedBookArraylist.get(position).getBOOK_TITLE());
        holder.txtAuthor.setText(reservedBookArraylist.get(position).getComb_author_l_name_wise());
        holder.txtReservStatus.setText(RESERVATION_STATUS);


        //date
        String date = reservedBookArraylist.get(position).getRESERVATION_DATE();
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
        holder.txtReserveDt.setText(dayofMonth + " " + monthString + " " + years + " " + d[1] + " " + d[2]);


        holder.txtPosition.setText("Position : " + reservedBookArraylist.get(position).getACCESSION_NUMBER());
        if (type.equalsIgnoreCase("DETAIL_VIEW")) {
            holder.imgVwBook.setVisibility(View.GONE);
        } else if (type.equalsIgnoreCase("THUMBNAIL_VIEW")) {
            holder.imgVwBook.setVisibility(View.VISIBLE);
        }

        if (RESERVATION_STATUS.equalsIgnoreCase("AVAILABLE_FOR_ISSUE")) {
            holder.linlytStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_round_gren));
            holder.txtReservStatus.setTextColor(Color.parseColor("#44a308"));
        } else if (RESERVATION_STATUS.equalsIgnoreCase("PENDING")) {
            holder.linlytStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_round_red));
            holder.txtReservStatus.setTextColor(Color.parseColor("#ff0000"));

        } else if (RESERVATION_STATUS.equalsIgnoreCase("PENDING")) {
            holder.linlytStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_round_red));
            holder.txtReservStatus.setTextColor(Color.parseColor("#ff0000"));
        }

    }

    @Override
    public int getItemCount() {
        return reservedBookArraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgVwBook;
        LinearLayout linlytStatus;
        TextView txtbookTitle, txtAuthor, txtBookType, txtReserveDt, txtPosition, txtISBNNo, txtReservStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            Typeface type_faceHeading = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Regular.ttf");

            imgVwBook = (ImageView) itemView.findViewById(R.id.imgVwBook);
            linlytStatus = (LinearLayout) itemView.findViewById(R.id.linlytStatus);
            txtbookTitle = (TextView) itemView.findViewById(R.id.txtbookTitle);
            txtbookTitle.setTypeface(type_faceHeading);
            txtAuthor = (TextView) itemView.findViewById(R.id.txtAuthor);
            txtAuthor.setTypeface(type_faceContent);
            txtBookType = (TextView) itemView.findViewById(R.id.txtBookType);
            txtBookType.setTypeface(type_faceContent);
            txtReserveDt = (TextView) itemView.findViewById(R.id.txtReserveDt);
            txtReserveDt.setTypeface(type_faceContent);
            txtPosition = (TextView) itemView.findViewById(R.id.txtPosition);
            txtPosition.setTypeface(type_faceContent);
            txtISBNNo = (TextView) itemView.findViewById(R.id.txtISBNNo);
            txtISBNNo.setTypeface(type_faceContent);
            txtReservStatus = (TextView) itemView.findViewById(R.id.txtReservStatus);
            txtReservStatus.setTypeface(type_faceContent);

        }
    }
}
