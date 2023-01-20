package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.NotesDetailModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wccs1980 on 26-06-2018.
 */

public class SubjectWiseNotesAdpter extends RecyclerView.Adapter<SubjectWiseNotesAdpter.ViewHolder> {

    Context context;
    ArrayList<NotesDetailModel.StudNotesDtlsBean> notesDtlsArrayList;
    private ButtonClickListner buttonClickListner;
    private SparseBooleanArray selectedItems;

    public SubjectWiseNotesAdpter(Context context, ArrayList<NotesDetailModel.StudNotesDtlsBean> notesDtlsArrayList,ButtonClickListner buttonClickListner) {
        this.context = context;
        this.notesDtlsArrayList = notesDtlsArrayList;
        this.buttonClickListner=buttonClickListner;
        selectedItems = new SparseBooleanArray();

        for(int i=0;i<notesDtlsArrayList.size();i++){
            NotesDetailModel.StudNotesDtlsBean note = notesDtlsArrayList.get(i);

            String LIKE_COUNT=notesDtlsArrayList.get(i).getLIKE_COUNT();
            if(LIKE_COUNT.equalsIgnoreCase("") || LIKE_COUNT.isEmpty()){
                note.setLIKE_COUNT("0");
                notesDtlsArrayList.set(i, note);
            }

            String DISLIKE_COUNT=notesDtlsArrayList.get(i).getDISLIKE_COUNT();
            if(DISLIKE_COUNT.equalsIgnoreCase("") || DISLIKE_COUNT.isEmpty()){
                note.setDISLIKE_COUNT("0");
                notesDtlsArrayList.set(i, note);
            }

            String VIEW_COUNT=notesDtlsArrayList.get(i).getVIEW_COUNT();
            if(VIEW_COUNT.equalsIgnoreCase("") || VIEW_COUNT.isEmpty()){
                note.setVIEW_COUNT("0");
                notesDtlsArrayList.set(i, note);
            }

        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtUnitName.setText(notesDtlsArrayList.get(position).getNOTES_DESCRIPTION());
        String date=notesDtlsArrayList.get(position).getINSERT_USER_DATE();
        String[] dte=date.split(" ");
        if(dte[0]!=null){
           // holder.txtDate.setText(d[0]);
            String noteDate=dte[0];
            String[] value=noteDate.split(" ");
            String dt=value[0];
            dt = dt.replace("-", "/");
            String d[] = dt.split("/");
            String month = d[0];
            String day = d[1];
            String year = d[2];

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


            holder.txtDate.setText(dayofMonth+" "+monthString+" "+years);

        }
        holder.txtRemark.setText(notesDtlsArrayList.get(position).getREMARK().toLowerCase());
        holder.txtFacName.setText(notesDtlsArrayList.get(position).getFACULTY_NAME());
        holder.txtLike.setText(notesDtlsArrayList.get(position).getLIKE_COUNT()+" LIKE");
        holder.txtUnLike.setText(notesDtlsArrayList.get(position).getDISLIKE_COUNT()+" UNLIKE");
        //holder.txtView.setText(notesDtlsArrayList.get(position).getVIEW_COUNT()+" VIEW");
        holder.txtView.setText("VIEW");


        // apply click events
        applyClickEvents(holder, position);

    }

    private void applyClickEvents(ViewHolder holder, final int position) {


        holder.fabLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notesDtlsArrayList.get(position).getLIKE_DISLIKE_STATUS().equalsIgnoreCase("ENABLE")){
                    buttonClickListner.onLikeClick(position);
                }
            }
        });

        holder.fabUnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notesDtlsArrayList.get(position).getLIKE_DISLIKE_STATUS().equalsIgnoreCase("ENABLE")){
                    buttonClickListner.onUnlikeClick(position);
                }
            }
        });

        holder.fabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonClickListner.onViewClick(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return notesDtlsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtUnitName,txtDate,txtFacName,txtRemark,txtLike,txtUnLike,txtView;
        ImageView fabLike,fabUnLike,fabView;

        public ViewHolder(View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

            txtUnitName=(TextView)itemView.findViewById(R.id.txtUnitName);
            txtUnitName.setTypeface(customTypeOne);
            txtDate=(TextView)itemView.findViewById(R.id.txtDate);
            txtDate.setTypeface(customTypeOne);
            txtFacName=(TextView)itemView.findViewById(R.id.txtFacName);
            txtFacName.setTypeface(customTypeOne);
            txtRemark=(TextView)itemView.findViewById(R.id.txtRemark);
            txtRemark.setTypeface(customTypeOne);

            fabLike=(ImageView)itemView.findViewById(R.id.fabLike);
            fabUnLike=(ImageView)itemView.findViewById(R.id.fabUnLike);
            fabView=(ImageView)itemView.findViewById(R.id.fabView);

            txtLike=(TextView)itemView.findViewById(R.id.txtLike);
            txtLike.setTypeface(customTypeOne);
            txtUnLike=(TextView)itemView.findViewById(R.id.txtUnLike);
            txtUnLike.setTypeface(customTypeOne);
            txtView=(TextView)itemView.findViewById(R.id.txtView);
            txtView.setTypeface(customTypeOne);

        }
    }

    public interface ButtonClickListner{

        void onLikeClick(int position);

        void onUnlikeClick(int position);

        void onViewClick(int position);
    }
}
