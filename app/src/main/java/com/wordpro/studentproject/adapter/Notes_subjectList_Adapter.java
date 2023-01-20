package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.AssignmentModel;

import java.util.ArrayList;

/**
 * Created by wccs1980 on 27-04-2018.
 */

public class Notes_subjectList_Adapter extends RecyclerView.Adapter<Notes_subjectList_Adapter.ViewHolder> {

    Context Mcontext;
    private ArrayList<AssignmentModel.StudAssignDtlsBean> assignDtlsBeans;

    public Notes_subjectList_Adapter(Context context , ArrayList<AssignmentModel.StudAssignDtlsBean> studAssignDtlsArrayList) {
        Mcontext = context;
        assignDtlsBeans = studAssignDtlsArrayList;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_subject_list_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        /*holder.txt_subject.setText(assignDtlsBeans.get(position).getSUBJECT_DESCRIPTION());
        holder.tv_LastDate.setText(assignDtlsBeans.get(position).getASSIGN_SUBMI_FROM_DATE());
        holder.txt_copyTex.setText(assignDtlsBeans.get(position).getASSIGN_SUBMI_UPTO_DATE());
        holder.txt_Des.setText(assignDtlsBeans.get(position).getSUBJECT_DESCRIPTION());*/
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_notes_subject,txt_note_date,txt_note_prof_na,txt_note_Des,txt_download,txt_likeCount,txt_like,txt_unlikeCount,txt_unlike;
        private ImageView  img_download;
        public ViewHolder(View itemView) {
            super(itemView);



            txt_notes_subject =(TextView) itemView.findViewById(R.id.txt_notes_subject);
            txt_note_date =(TextView) itemView.findViewById(R.id.txt_note_date);
            txt_note_prof_na =(TextView) itemView.findViewById(R.id.txt_note_prof_na);
            txt_note_Des =(TextView) itemView.findViewById(R.id.txt_note_Des);
            txt_download =(TextView) itemView.findViewById(R.id.txt_download);
            txt_likeCount  =(TextView) itemView.findViewById(R.id.txt_likeCount);
            txt_like =(TextView) itemView.findViewById(R.id.txt_like);
            txt_unlikeCount =(TextView) itemView.findViewById(R.id.txt_unlikeCount);
            txt_unlike =(TextView) itemView.findViewById(R.id.txt_unlike);
            img_download =(ImageView) itemView.findViewById(R.id.img_download);



            Typeface typefaceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Medium.otf");

            txt_notes_subject.setTypeface(typefaceHeading);
            txt_note_date.setTypeface(typefaceContent);
            txt_note_prof_na.setTypeface(typefaceContent);
            txt_note_Des.setTypeface(typefaceContent);
            txt_download.setTypeface(typefaceHeading);
            txt_likeCount.setTypeface(typefaceContent);
            txt_like.setTypeface(typefaceHeading);
            txt_unlikeCount.setTypeface(typefaceContent);
            txt_unlike.setTypeface(typefaceHeading);

        }
    }
}
