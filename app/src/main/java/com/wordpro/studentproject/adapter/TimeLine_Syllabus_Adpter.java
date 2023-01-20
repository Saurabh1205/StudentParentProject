package com.wordpro.studentproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.TeachPlanSubjModel;
import com.wordpro.studentproject.model.UnivSyllabusModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class TimeLine_Syllabus_Adpter extends RecyclerView.Adapter<TimeLine_Syllabus_Adpter.ViewHolder>{

    Context Mcontext;
    public static DaywiseAttndModel daywiseAttndModel;
    public static ArrayList<UnivSyllabusModel.DataBean> univSyllabusSubject;
    List<String> UnitI = new ArrayList<String>();

    private int I=0,II=0,III=0,IV=0,V=0,VI=0;
    private List<String> UI, UII, UIII, UIV, UV, UVI;
    private  StringBuilder sb;
    private List<String> _listDataHeader; // header titles
    private HashMap<String, List<String>> _listDataChild;
  /*  public TimeLine_Syllabus_Adpter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {
        this.Mcontext =context;
        this._listDataHeader =listDataHeader;
        this._listDataChild =listDataChild;

    }*/

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    private TimeLine_Syllabus_Adpter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(TimeLine_Syllabus_Adpter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }

    public TimeLine_Syllabus_Adpter(Context context, ArrayList<UnivSyllabusModel.DataBean> pendingJobDetails) {
        this.Mcontext =context;
        this.univSyllabusSubject =pendingJobDetails;
    }

    @NonNull
    @Override
    public TimeLine_Syllabus_Adpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.timeline_unit_view, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int postion) {


        if(univSyllabusSubject.get(postion).getUNIT_NO().equalsIgnoreCase("1")){
            int unitNo =1;
            String str1 =Integer.toString(unitNo);
            holder.UnitNo.setText("U-I");
            holder.UnitNo.setTextColor(ContextCompat.getColor(Mcontext, R.color.CEvent));
        }else  if (univSyllabusSubject.get(postion).getUNIT_NO().equalsIgnoreCase("2")){

            int unitNo =2;
            String str1 =Integer.toString(unitNo);
            holder.UnitNo.setText("U-II");
            holder.UnitNo.setTextColor(ContextCompat.getColor(Mcontext, R.color.present));

        }else  if (univSyllabusSubject.get(postion).getUNIT_NO().equalsIgnoreCase("3")){


            int unitNo =3;
            String str1 =Integer.toString(unitNo);
            holder.UnitNo.setText("U-III");
            holder.UnitNo.setTextColor(ContextCompat.getColor(Mcontext, R.color.selector_color));

        }else  if (univSyllabusSubject.get(postion).getUNIT_NO().equalsIgnoreCase("4")){

            int unitNo =4;
            String str1 =Integer.toString(unitNo);
            holder.UnitNo.setText("U-IV");
            holder.UnitNo.setTextColor(ContextCompat.getColor(Mcontext, R.color.UnMarkAttend));

        }else  if (univSyllabusSubject.get(postion).getUNIT_NO().equalsIgnoreCase("5")){

            int unitNo =5;
            String str1 =Integer.toString(unitNo);
            holder.UnitNo.setText("U-V");
            holder.UnitNo.setTextColor(ContextCompat.getColor(Mcontext, R.color.FacultyLeave));

        }else  if (univSyllabusSubject.get(postion).getUNIT_NO().equalsIgnoreCase("6")){

            int unitNo =6;
            String str1 =Integer.toString(unitNo);
            holder.UnitNo.setText("U-VI");
            holder.UnitNo.setTextColor(ContextCompat.getColor(Mcontext, R.color.cwork));

        }

       // holder.txt_top_name.setText(univSyllabusSubject.get(postion).getSYLLABUS_DESC());
        holder.txt_sub_description.setText(univSyllabusSubject.get(postion).getTOPIC_DESCRIPTION());


    }

    @Override
    public int getItemCount() {
        return univSyllabusSubject.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  txt_sub_name,txt_Tm,txt_tolLecture,txt_Com_Lecture,total_unit_Value,txt_prof_Name,t,tt,txt_syllabus,txt_teachningPlan,txt_Status;
        private ImageView src_img;
        private RecyclerView even_img_recyclerView;
        private CardView  card_view_sub;
        private RatingBar ratingBar;
        private TextView txt_top_name,txt_sub_description,temperature_degree,UnitNo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");

          /*  txt_sub_name= (TextView) itemView.findViewById(R.id.txt_sub_name);
            txt_Tm= (TextView)itemView.findViewById(R.id.txt_Tm);
            txt_tolLecture= (TextView)itemView.findViewById(R.id.txt_tolLecture);
            txt_Com_Lecture= (TextView)itemView.findViewById(R.id.txt_Com_Lecture);
            total_unit_Value = (TextView)itemView.findViewById(R.id.total_unit_Value);
            txt_prof_Name= (TextView)itemView.findViewById(R.id.txt_prof_Name);
            t= (TextView)itemView.findViewById(R.id.t);
            tt= (TextView)itemView.findViewById(R.id.tt);
*/
            txt_top_name= (TextView)itemView.findViewById(R.id.txt_top_name);
            txt_sub_description= (TextView)itemView.findViewById(R.id.txt_sub_description);
            temperature_degree= (TextView)itemView.findViewById(R.id.temperature_degree);
            UnitNo= (TextView)itemView.findViewById(R.id.UnitNo);

            txt_top_name.setTypeface(type_faceHeading);
            txt_sub_description.setTypeface(type_faceContent);
            UnitNo.setTypeface(type_faceHeading);

           /* card_view_sub = (CardView)itemView. findViewById(R.id.card_view_sub);

            txt_sub_name.setTypeface(type_faceHeading);
            txt_Tm.setTypeface(type_faceHeading);
            txt_tolLecture.setTypeface(type_faceHeading);
            txt_tolLecture.setTypeface(type_faceHeading);

            txt_syllabus.setTypeface(type_faceHeading);
            txt_teachningPlan.setTypeface(type_faceHeading);
            txt_Status.setTypeface(type_faceHeading);

            t.setTypeface(type_faceContent);
            tt.setTypeface(type_faceContent);
            txt_prof_Name.setTypeface(type_faceHeading);
            total_unit_Value.setTypeface(type_faceContent);*/
        }
    }

}
