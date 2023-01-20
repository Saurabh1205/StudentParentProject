package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.AssignmentModel;
import com.wordpro.studentproject.model.DocumentListDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wccs1980 on 27-04-2018.
 */

public class D_wallet_download_List_Adapter extends RecyclerView.Adapter<D_wallet_download_List_Adapter.ViewHolder> {

    Context Mcontext;
    private List<DocumentListDetails> documentListDetails;
    private String FileName;


    public interface OnItemClickListener {
        void onItemClick(View itemView, int position, String download);
    }
    private D_wallet_download_List_Adapter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(D_wallet_download_List_Adapter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }
    public D_wallet_download_List_Adapter(Context context , List<DocumentListDetails> details,String fileName) {
       this. Mcontext = context;
       this. documentListDetails = details;
       this.FileName = fileName;


    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.d_wallet_download_list, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

            holder.txt_subject.setText(documentListDetails.get(position).getdOCUMENTNAME());
            if (FileName.equalsIgnoreCase("Upload")){
                holder.txt_count.setVisibility(View.INVISIBLE);
            }else {
                holder.txt_count.setVisibility(View.VISIBLE);
                holder.txt_count.setText(documentListDetails.get(position).getdOCCOUNT());
            }






           holder.card_view_sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallbacks != null) {
                        String download="download";

                        if (position != RecyclerView.NO_POSITION) {
                            mCallbacks.onItemClick(v, position,download);
                        }
                    }
                }
            });
            /* holder.lay_upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String download="upload";
                    if (position != RecyclerView.NO_POSITION) {
                        mCallbacks.onItemClick(v, position,download);
                    }
                }
            });*/



    }

    @Override
    public int getItemCount() {
        return documentListDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_subject,txt_count;
        CardView card_view_sub;


        public ViewHolder(View itemView) {
            super(itemView);

            txt_count =(TextView) itemView.findViewById(R.id.txt_count);
            txt_subject =(TextView) itemView.findViewById(R.id.txt_subject);
            card_view_sub =(CardView) itemView.findViewById(R.id.card_view_sub);;


            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");



            txt_subject.setTypeface(type_faceHeading);
            txt_count.setTypeface(type_faceContent);


        }
    }


}
