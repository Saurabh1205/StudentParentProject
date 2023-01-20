package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.AssignmentModel;
import com.wordpro.studentproject.model.NewArrvlMnthModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wccs1980 on 27-04-2018.
 */

public class New_Arrivals_List_Adapter extends RecyclerView.Adapter<New_Arrivals_List_Adapter.ViewHolder> {

    Context Mcontext;
    ArrayList<NewArrvlMnthModel.LibBokNewArrAbstBean> libBokNewArrAbstBeans;

    private New_Arrivals_List_Adapter selectedItems;
    private String AssignmentListName;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    private New_Arrivals_List_Adapter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(New_Arrivals_List_Adapter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }
    public New_Arrivals_List_Adapter(Context context , ArrayList<NewArrvlMnthModel.LibBokNewArrAbstBean>data) {
       this. Mcontext = context;
       this. libBokNewArrAbstBeans = data;


    }

  /*  public Assignment_List_Adapter(Context context, ArrayList<AssignmentModel.StudAssignDtlsBean> studAssignDtlsArrayList, AssignDownUpListner assignDownUpListner) {
        this. Mcontext = context;
        this. assignDtlsBeans = studAssignDtlsArrayList;
        selectedItems = new SparseBooleanArray();
        this.assignDownUpListner = (AssignmentAdapter.AssignDownUpListner) assignDownUpListner;}
*/


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_arrival_adapter_view, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String date = libBokNewArrAbstBeans.get(position).getStart_Day_Month();
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


        holder.txt_date_arrivals.setText(monthString + "  " + years);
        holder.txt_total_arrivals.setText(libBokNewArrAbstBeans.get(position).getNo_of_Book_Title() + " ARRIVAL");

         holder.card_view_arrivals.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (position != RecyclerView.NO_POSITION) {
                        mCallbacks.onItemClick(v, position);
                    }
                }
            });



    }

    @Override
    public int getItemCount() {
        return libBokNewArrAbstBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_date_arrivals,txt_total_arrivals;
        private LinearLayout  lay_download,lay_upload,layout_avlType;
        private ImageView img_download,img_upload;
        private CardView card_view_arrivals;
        public ViewHolder(View itemView) {
            super(itemView);

            txt_date_arrivals =(TextView) itemView.findViewById(R.id.txt_date_arrivals);
            txt_total_arrivals =(TextView) itemView.findViewById(R.id.txt_total_arrivals);
            card_view_arrivals = (CardView)itemView.findViewById(R.id.card_view_arrivals);
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");

            txt_date_arrivals.setTypeface(type_faceHeading);
            txt_total_arrivals.setTypeface(type_faceHeading);

        }
    }


}
