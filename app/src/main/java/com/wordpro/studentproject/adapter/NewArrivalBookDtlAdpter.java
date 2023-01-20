package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.NewArrivalBookModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewArrivalBookDtlAdpter extends RecyclerView.Adapter<NewArrivalBookDtlAdpter.ViewHolder>{

    Context context;
    ArrayList<NewArrivalBookModel.LibBokNewArrDtlsBean> newArrivalBookDtlList;

    public NewArrivalBookDtlAdpter(Context context, ArrayList<NewArrivalBookModel.LibBokNewArrDtlsBean> newArrivalBookDtlList) {
        this.context = context;
        this.newArrivalBookDtlList = newArrivalBookDtlList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_arrvl_book_row, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String date = newArrivalBookDtlList.get(position).getBOOK_ARRIVAL_DATE();
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


        holder.txtArrivalDt.setText(dayOfTheWeek+" "+dayofMonth+" "+monthString+" "+years);
        holder.txtbookTitle.setText(newArrivalBookDtlList.get(position).getBook_title());
        holder.txtAuthor.setText(newArrivalBookDtlList.get(position).getAUTHOR());
        holder.txtPublication.setText(newArrivalBookDtlList.get(position).getPublication_name());
        holder.txtISBNNo.setText("ISBN No : "+newArrivalBookDtlList.get(position).getISBN_CODE());
        holder.txtSynonym.setText(newArrivalBookDtlList.get(position).getSynonyms());
        holder.txtPrice.setText("Price : "+newArrivalBookDtlList.get(position).getBOOK_PRICE()+" ₹");

        if(holder.txtSynonym.getLineCount() >= 3){

            holder.btShowmore.setVisibility(View.VISIBLE);
        }else {
            holder.btShowmore.setVisibility(View.GONE);

        }

        holder.btShowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.btShowmore.getText().toString().equalsIgnoreCase("Showmore..."))
                {
                    holder.txtSynonym.setMaxLines(Integer.MAX_VALUE);//your TextView
                    holder.btShowmore.setText("Showless");
                }
                else
                {
                    holder.txtSynonym.setMaxLines(3);//your TextView
                    holder.btShowmore.setText("Showmore...");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newArrivalBookDtlList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtArrivalDt,txtbookTitle,txtAuthor,txtPublication,txtISBNNo,txtSynonym,txtPrice;
        Button btShowmore;
        ImageView imgCoverPg;

        public ViewHolder(View itemView) {
            super(itemView);
            //Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            Typeface type_faceHeading = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Regular.ttf");
            txtArrivalDt=(TextView)itemView.findViewById(R.id.txtArrivalDt);
            txtArrivalDt.setTypeface(type_faceHeading);
            txtbookTitle=(TextView)itemView.findViewById(R.id.txtbookTitle);
            txtbookTitle.setTypeface(type_faceHeading);
            txtAuthor=(TextView)itemView.findViewById(R.id.txtAuthor);
            txtAuthor.setTypeface(type_faceContent);
            txtPublication=(TextView)itemView.findViewById(R.id.txtPublication);
            txtPublication.setTypeface(type_faceContent);
            txtISBNNo=(TextView)itemView.findViewById(R.id.txtISBNNo);
            txtISBNNo.setTypeface(type_faceHeading);
            txtSynonym=(TextView)itemView.findViewById(R.id.txtSynonym);
            txtSynonym.setTypeface(type_faceHeading);
            txtPrice=(TextView)itemView.findViewById(R.id.txtPrice);
            txtPrice.setTypeface(type_faceContent);
            btShowmore=(Button)itemView.findViewById(R.id.btShowmore);
            btShowmore.setTypeface(type_faceContent);
            imgCoverPg=(ImageView)itemView.findViewById(R.id.imgCoverPg);

        }
    }
}