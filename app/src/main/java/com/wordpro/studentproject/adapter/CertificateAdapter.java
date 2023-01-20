package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.ProfileModel;

import java.util.ArrayList;

/**
 * Created by wccs1980 on 11-06-2018.
 */

public class CertificateAdapter extends RecyclerView.Adapter<CertificateAdapter.Viewholder> {

    Context context;
    ArrayList<ProfileModel.DocumnetDtlsBean> documnetDtlsArrayList;

    public CertificateAdapter(Context context, ArrayList<ProfileModel.DocumnetDtlsBean> documnetDtlsArrayList) {
        this.context = context;
        this.documnetDtlsArrayList = documnetDtlsArrayList;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_certificate_info_row_layout, null);
        Viewholder viewholder=new Viewholder(itemLayoutView);
        return  viewholder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {

        holder.txtSTU_DOCUMENT_NAME.setText(documnetDtlsArrayList.get(position).getSTU_DOCUMENT_NAME());
        holder.txtSTU_DODUMENT_DESC.setText(": "+documnetDtlsArrayList.get(position).getSTU_DODUMENT_DESC());

    }

    @Override
    public int getItemCount() {
        return documnetDtlsArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView txtSTU_DOCUMENT_NAME,txtSTU_DODUMENT_DESC;

        public Viewholder(View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            txtSTU_DOCUMENT_NAME=(TextView)itemView.findViewById(R.id.txtSTU_DOCUMENT_NAME);
            txtSTU_DOCUMENT_NAME.setTypeface(customTypeOne);
            txtSTU_DODUMENT_DESC=(TextView)itemView.findViewById(R.id.txtSTU_DODUMENT_DESC);
            txtSTU_DODUMENT_DESC.setTypeface(customTypeOne);
        }
    }
}
