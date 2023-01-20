package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.GetStudentDetailsModel;
import com.wordpro.studentproject.model.NewGetNoticAttachment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wccs1980 on 27-04-2018.
 */

public class Info_Jobtype_Adapter extends RecyclerView.Adapter<Info_Jobtype_Adapter.ViewHolder> {

    Context Mcontext;
    private ArrayList<NewGetNoticAttachment> getAttachment;

    public Info_Jobtype_Adapter(Context context, ArrayList<NewGetNoticAttachment> pendingJobAttachment) {
        this. Mcontext = context;
        this. getAttachment = pendingJobAttachment;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
    private Info_Jobtype_Adapter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(Info_Jobtype_Adapter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_details_attechment_ad, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_headerNme.setText(getAttachment.get(position).getaTTACHMENTNAME());

        holder.img_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (mCallbacks != null) {

                    if (position != RecyclerView.NO_POSITION) {
                        mCallbacks.onItemClick(v, position);
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return getAttachment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_headerNme;
        CardView   card_view_adpter;
        ImageView img_url,img_download;
        public ViewHolder(View itemView) {
            super(itemView);

            txt_headerNme =(TextView) itemView.findViewById(R.id.txt_headerNme);
            card_view_adpter =(CardView) itemView.findViewById(R.id.card_view_adpter);
            img_url =(ImageView) itemView.findViewById(R.id.img_url);
            img_download =(ImageView) itemView.findViewById(R.id.img_download);
         /*   Typeface typefaceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.otf");
            Typeface typefaceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Medium.otf");

            txt_headerNme.setTypeface(typefaceHeading);
*/

        }
    }
}
