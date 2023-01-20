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
import com.wordpro.studentproject.model.DocumentListDetails;
import com.wordpro.studentproject.model.ListDocDownloadModel;

import java.util.List;

/**
 * Created by wccs1980 on 27-04-2018.
 */

public class D_Wallet_DownloadDetails_Adapter extends RecyclerView.Adapter<D_Wallet_DownloadDetails_Adapter.ViewHolder> {

    Context Mcontext;

    private List<ListDocDownloadModel> documentListDtls;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    private D_Wallet_DownloadDetails_Adapter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(D_Wallet_DownloadDetails_Adapter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }
    public D_Wallet_DownloadDetails_Adapter(Context context , List<ListDocDownloadModel> details) {
       this. Mcontext = context;
       this. documentListDtls = details;


    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_download, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

            holder.lblDocName.setText(documentListDtls.get(position).getDOCUMENTNAME());
            holder.lblSubmDate.setText(documentListDtls.get(position).getSUBMISSIONDATE());

            holder.card_view_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallbacks != null) {
                        String download="download";

                        if (position != RecyclerView.NO_POSITION) {
                            mCallbacks.onItemClick(v, position);
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
        return documentListDtls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView lblDocName,lblSubmDate;
        ImageView imgVwDownload;
        CardView card_view_details;

        public ViewHolder(View itemView) {
            super(itemView);

            lblDocName =(TextView) itemView.findViewById(R.id.lblDocName);
            lblSubmDate =(TextView) itemView.findViewById(R.id.lblSubmDate);
            card_view_details =(CardView) itemView.findViewById(R.id.card_view_details);;

            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");
            lblDocName.setTypeface(type_faceHeading);
            lblSubmDate.setTypeface(type_faceContent);

        }
    }


}
