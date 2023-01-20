package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
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
import com.wordpro.studentproject.model.NotesDetailModel;

import java.util.ArrayList;

/**
 * Created by wccs1980 on 27-04-2018.
 */

public class Notes_List_Adapter extends RecyclerView.Adapter<Notes_List_Adapter.ViewHolder> {

    Context Mcontext;
    private ArrayList<NotesDetailModel.StudNotesDtlsBean> notesDetailArrayList;
    private AssignmentAdapter.AssignDownUpListner assignDownUpListner;
    private SparseBooleanArray selectedItems;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position, String download);
    }
    private Notes_List_Adapter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(Notes_List_Adapter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }
    public Notes_List_Adapter(Context context , ArrayList<NotesDetailModel.StudNotesDtlsBean> notesDetail) {
       this. Mcontext = context;
       this. notesDetailArrayList = notesDetail;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_adapter_list, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txt_subject.setText(notesDetailArrayList.get(position).getNOTES_DESCRIPTION());
        holder.tv_LastDate.setText(notesDetailArrayList.get(position).getPUBLISH_DATE());
        holder.txt_facultyNme.setText(notesDetailArrayList.get(position).getFACULTY_NAME());
        holder.txt_Des.setText(notesDetailArrayList.get(position).getSUBJECT_DESCRIPTION());

        holder.txt_Des.setText(notesDetailArrayList.get(position).getSUBJECT_DESCRIPTION());

        holder.txt_like.setText(notesDetailArrayList.get(position).getLIKE_COUNT());

        holder.txt_unlike.setText(notesDetailArrayList.get(position).getDISLIKE_COUNT());

      /*  if (notesDetailArrayList.get(position).getASSIGN_SUBMISSION_TYPE().equalsIgnoreCase("SOFT COPY")){
            holder.layout_avlType.setVisibility(View.VISIBLE);
        }else {
            holder.layout_avlType.setVisibility(View.GONE);
        }*/



        holder.lay_download.setOnClickListener(new View.OnClickListener() {
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
        holder.lay_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String download="like";
                if (position != RecyclerView.NO_POSITION) {
                    mCallbacks.onItemClick(v, position,download);
                }
            }
        });
        holder.lay_unlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String download="unlike";
                if (position != RecyclerView.NO_POSITION) {
                    mCallbacks.onItemClick(v, position,download);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return notesDetailArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_Des,txt_copyTex,txt_sub_type,tv_LastDate,txt_sub_Last_date,txt_subject,txt_download,txt_upload,txt_faculty,txt_facultyNme;
        private LinearLayout  lay_download,lay_like,lay_unlike;
        private ImageView img_download,img_upload;
        private TextView txt_like,txt_like_view,txt_unlike,txt_unlikeView;
        public ViewHolder(View itemView) {
            super(itemView);



            txt_Des =(TextView) itemView.findViewById(R.id.txt_Des);
            txt_faculty =(TextView) itemView.findViewById(R.id.txt_faculty);
            txt_facultyNme =(TextView) itemView.findViewById(R.id.txt_facultyNme);
           // txt_sub_type =(TextView) itemView.findViewById(R.id.txt_sub_type);
            tv_LastDate =(TextView) itemView.findViewById(R.id.tv_LastDate);
            txt_sub_Last_date =(TextView) itemView.findViewById(R.id.txt_sub_Last_date);
            txt_subject  =(TextView) itemView.findViewById(R.id.txt_subject);
            lay_download =(LinearLayout) itemView.findViewById(R.id.lay_download);
            lay_like =(LinearLayout) itemView.findViewById(R.id.lay_like);
            lay_unlike=(LinearLayout) itemView.findViewById(R.id.lay_unlike);

            txt_download =(TextView) itemView.findViewById(R.id.txt_download);
            txt_like =(TextView) itemView.findViewById(R.id.txt_like);
            txt_like_view =(TextView) itemView.findViewById(R.id.txt_like_view);
            txt_unlike =(TextView) itemView.findViewById(R.id.txt_unlike);
            txt_unlikeView =(TextView) itemView.findViewById(R.id.txt_unlikeView);
            img_download =(ImageView) itemView.findViewById(R.id.img_download);


            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");

            txt_subject.setTypeface(type_faceHeading);
            txt_sub_Last_date.setTypeface(type_faceContent);
            tv_LastDate.setTypeface(type_faceContent);
            txt_like_view.setTypeface(type_faceHeading);
            txt_unlikeView.setTypeface(type_faceHeading);
            txt_facultyNme.setTypeface(type_faceContent);
            txt_Des.setTypeface(type_faceContent);

        }
    }


}
