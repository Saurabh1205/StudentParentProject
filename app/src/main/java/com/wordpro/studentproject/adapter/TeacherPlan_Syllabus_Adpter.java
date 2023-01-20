package com.wordpro.studentproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.TeachPlanDataModel;
import com.wordpro.studentproject.model.UnivSyllabusModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherPlan_Syllabus_Adpter extends RecyclerView.Adapter<TeacherPlan_Syllabus_Adpter.ViewHolder>{

    Context Mcontext;
    public static DaywiseAttndModel daywiseAttndModel;
    ArrayList<TeachPlanDataModel.DataBean> teachPlanDataArrayList;
    List<String> UnitI = new ArrayList<String>();

    private int I=0,II=0,III=0,IV=0,V=0,VI=0;
    private List<String> UI, UII, UIII, UIV, UV, UVI;
    private  StringBuilder sb;
    private List<String> _listDataHeader; // header titles
    private HashMap<String, List<String>> _listDataChild;
    private  String aa="";
    String UnintID="";
    int UnitNo =1;
  /*  public TimeLine_Syllabus_Adpter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {
        this.Mcontext =context;
        this._listDataHeader =listDataHeader;
        this._listDataChild =listDataChild;

    }*/

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    private TeacherPlan_Syllabus_Adpter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(TeacherPlan_Syllabus_Adpter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }

    public TeacherPlan_Syllabus_Adpter(Context context, ArrayList<TeachPlanDataModel.DataBean> pendingJobDetails) {
        this.Mcontext =context;
        this.teachPlanDataArrayList =pendingJobDetails;
    }

    @NonNull
    @Override
    public TeacherPlan_Syllabus_Adpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.timeline_unit_view, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int postion) {
        holder.temperature_degree.setVisibility(View.VISIBLE);
        holder.txt_coverdDate.setVisibility(View.VISIBLE);

    //teachPlanDataArrayList.get(postion).getUNIT_NAME().equalsIgnoreCase("UNIT I"))
       // UnintID =teachPlanDataArrayList.get(i).getSYLLABUS_DET_ID();


       /* if(teachPlanDataArrayList.get(postion).getSYLLABUS_DET_ID().equalsIgnoreCase(UnintID)){
         //   UnitNo =;
        }else {
            UnintID =teachPlanDataArrayList.get(postion).getSYLLABUS_DET_ID();
            UnitNo ++;
        }*/

      //  String str1 =Integer.toString(UnitNo);
       /* holder.UnitNo.setText(teachPlanDataArrayList.get(postion).getUNIT_NO());
        holder.UnitNo.setTextColor(ContextCompat.getColor(Mcontext, R.color.CEvent));

        holder.temperature_degree.setText(*//*"PROPOSED"+" "+*//*teachPlanDataArrayList.get(postion).getTOPIC_TENTITIVE_STATUS());
        holder.txt_coverdDate.setText(*//*"COVERED"+" "+*//*teachPlanDataArrayList.get(postion).getComb_Covered_date());
        holder.txt_sub_description.setText(teachPlanDataArrayList.get(postion).getTOPIC_DESCRIPTION());
        holder.temperature_degree.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
        holder.txt_coverdDate.setTextColor(ContextCompat.getColor(Mcontext, R.color.present));
*/


        if(teachPlanDataArrayList.get(postion).getUNIT_NO().equalsIgnoreCase("1")){
            int unitNo =1;
            String str1 =Integer.toString(unitNo);
            holder.UnitNo.setText("I");
            holder.UnitNo.setTextColor(ContextCompat.getColor(Mcontext, R.color.CEvent));

            if(!teachPlanDataArrayList.get(postion).getComb_Covered_date().equalsIgnoreCase("")){
                holder.txt_coverdDate.setVisibility(View.VISIBLE);
                //holder.txt_coverdDate.setText("COVERED"+" "+teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }else {
                holder.txt_coverdDate.setVisibility(View.INVISIBLE);
            }

           if(!teachPlanDataArrayList.get(postion).getTOPIC_TENTITIVE_STATUS().equalsIgnoreCase("")){
               holder.temperature_degree.setVisibility(View.VISIBLE);
               holder.temperature_degree.setText("PROPOSED"+" "+teachPlanDataArrayList.get(postion).getTOPIC_TENTITIVE_STATUS());
           }else {
                holder.temperature_degree.setVisibility(View.INVISIBLE);
           }

            holder.txt_sub_description.setText(teachPlanDataArrayList.get(postion).getTOPIC_DESCRIPTION());
            holder.temperature_degree.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
            if (teachPlanDataArrayList.get(postion).getCovered_Status().equalsIgnoreCase("Y")){
                holder.txt_coverdDate.setTextColor(ContextCompat.getColor(Mcontext, R.color.present));
                holder.txt_coverdDate.setText("COVERED"+" "+teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }else {
                holder.txt_coverdDate.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
                holder.txt_coverdDate.setText(teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }

        }else  if (teachPlanDataArrayList.get(postion).getUNIT_NO().equalsIgnoreCase("2")){

            int unitNo =2;
            String str1 =Integer.toString(unitNo);
            holder.UnitNo.setText("II");
            holder.UnitNo.setTextColor(ContextCompat.getColor(Mcontext, R.color.present));
            if(!teachPlanDataArrayList.get(postion).getComb_Covered_date().equalsIgnoreCase("")){
                holder.txt_coverdDate.setVisibility(View.VISIBLE);
               // holder.txt_coverdDate.setText("COVERED"+" "+teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }else {
                holder.txt_coverdDate.setVisibility(View.INVISIBLE);
            }

            if(!teachPlanDataArrayList.get(postion).getTOPIC_TENTITIVE_STATUS().equalsIgnoreCase("")){
                holder.temperature_degree.setVisibility(View.VISIBLE);
                holder.temperature_degree.setText("PROPOSED"+" "+teachPlanDataArrayList.get(postion).getTOPIC_TENTITIVE_STATUS());
            }else {
                holder.temperature_degree.setVisibility(View.INVISIBLE);
            }
            holder.txt_sub_description.setText(teachPlanDataArrayList.get(postion).getTOPIC_DESCRIPTION());
            holder.temperature_degree.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
            if (teachPlanDataArrayList.get(postion).getCovered_Status().equalsIgnoreCase("Y")){
                holder.txt_coverdDate.setTextColor(ContextCompat.getColor(Mcontext, R.color.present));
                holder.txt_coverdDate.setText("COVERED"+" "+teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }else {
                holder.txt_coverdDate.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
                holder.txt_coverdDate.setText(teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }
        }else  if (teachPlanDataArrayList.get(postion).getUNIT_NO().equalsIgnoreCase("3")){


            int unitNo =3;
            String str1 =Integer.toString(unitNo);
            holder.UnitNo.setText("III");
            holder.UnitNo.setTextColor(ContextCompat.getColor(Mcontext, R.color.selector_color));

            if(!teachPlanDataArrayList.get(postion).getComb_Covered_date().equalsIgnoreCase("")){
                holder.txt_coverdDate.setVisibility(View.VISIBLE);
                holder.txt_coverdDate.setText("COVERED"+" "+teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }else {
                holder.txt_coverdDate.setVisibility(View.INVISIBLE);
            }

            if(!teachPlanDataArrayList.get(postion).getTOPIC_TENTITIVE_STATUS().equalsIgnoreCase("")){
                holder.temperature_degree.setVisibility(View.VISIBLE);
                holder.temperature_degree.setText("PROPOSED"+" "+teachPlanDataArrayList.get(postion).getTOPIC_TENTITIVE_STATUS());
            }else {
                holder.temperature_degree.setVisibility(View.INVISIBLE);
            }
            holder.txt_sub_description.setText(teachPlanDataArrayList.get(postion).getTOPIC_DESCRIPTION());
            holder.temperature_degree.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
            if (teachPlanDataArrayList.get(postion).getCovered_Status().equalsIgnoreCase("Y")){
                holder.txt_coverdDate.setTextColor(ContextCompat.getColor(Mcontext, R.color.present));
                holder.txt_coverdDate.setText("COVERED"+" "+teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }else {
                holder.txt_coverdDate.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
                holder.txt_coverdDate.setText(teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }

        }else  if (teachPlanDataArrayList.get(postion).getUNIT_NO().equalsIgnoreCase("4")){

            int unitNo =4;
            String str1 =Integer.toString(unitNo);
            holder.UnitNo.setText("IV");
            holder.UnitNo.setTextColor(ContextCompat.getColor(Mcontext, R.color.UnMarkAttend));

            if(!teachPlanDataArrayList.get(postion).getComb_Covered_date().equalsIgnoreCase("")){
                holder.txt_coverdDate.setVisibility(View.VISIBLE);
                holder.txt_coverdDate.setText("COVERED"+" "+teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }else {
                holder.txt_coverdDate.setVisibility(View.INVISIBLE);
            }

            if(!teachPlanDataArrayList.get(postion).getTOPIC_TENTITIVE_STATUS().equalsIgnoreCase("")){
                holder.temperature_degree.setVisibility(View.VISIBLE);
                holder.temperature_degree.setText("PROPOSED"+" "+teachPlanDataArrayList.get(postion).getTOPIC_TENTITIVE_STATUS());
            }else {
                holder.temperature_degree.setVisibility(View.INVISIBLE);
            }
            holder.txt_sub_description.setText(teachPlanDataArrayList.get(postion).getTOPIC_DESCRIPTION());
            holder.temperature_degree.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
            if (teachPlanDataArrayList.get(postion).getCovered_Status().equalsIgnoreCase("Y")){
                holder.txt_coverdDate.setTextColor(ContextCompat.getColor(Mcontext, R.color.present));
                holder.txt_coverdDate.setText("COVERED"+" "+teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }else {
                holder.txt_coverdDate.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
                holder.txt_coverdDate.setText(teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }

        }else  if (teachPlanDataArrayList.get(postion).getUNIT_NO().equalsIgnoreCase("5")){

            int unitNo =5;
            String str1 =Integer.toString(unitNo);
            holder.UnitNo.setText("V");
            holder.UnitNo.setTextColor(ContextCompat.getColor(Mcontext, R.color.FacultyLeave));

            if(!teachPlanDataArrayList.get(postion).getComb_Covered_date().equalsIgnoreCase("")){
                holder.txt_coverdDate.setVisibility(View.VISIBLE);
                holder.txt_coverdDate.setText("COVERED"+" "+teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }else {
                holder.txt_coverdDate.setVisibility(View.INVISIBLE);
            }

            if(!teachPlanDataArrayList.get(postion).getTOPIC_TENTITIVE_STATUS().equalsIgnoreCase("")){
                holder.temperature_degree.setVisibility(View.VISIBLE);
                holder.temperature_degree.setText("PROPOSED"+" "+teachPlanDataArrayList.get(postion).getTOPIC_TENTITIVE_STATUS());
            }else {
                holder.temperature_degree.setVisibility(View.INVISIBLE);
            }
            holder.txt_sub_description.setText(teachPlanDataArrayList.get(postion).getTOPIC_DESCRIPTION());
            holder.temperature_degree.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
            if (teachPlanDataArrayList.get(postion).getCovered_Status().equalsIgnoreCase("Y")){
                holder.txt_coverdDate.setTextColor(ContextCompat.getColor(Mcontext, R.color.present));
                holder.txt_coverdDate.setText("COVERED"+" "+teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }else {
                holder.txt_coverdDate.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
                holder.txt_coverdDate.setText(teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }

        }else  if (teachPlanDataArrayList.get(postion).getUNIT_NO().equalsIgnoreCase("6")){

            int unitNo =6;
            String str1 =Integer.toString(unitNo);
            holder.UnitNo.setText("VI");
            holder.UnitNo.setTextColor(ContextCompat.getColor(Mcontext, R.color.cwork));

            if(!teachPlanDataArrayList.get(postion).getComb_Covered_date().equalsIgnoreCase("")){
                holder.txt_coverdDate.setVisibility(View.VISIBLE);
                holder.txt_coverdDate.setText("COVERED"+" "+teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }else {
                holder.txt_coverdDate.setVisibility(View.INVISIBLE);
            }

            if(!teachPlanDataArrayList.get(postion).getTOPIC_TENTITIVE_STATUS().equalsIgnoreCase("")){
                holder.temperature_degree.setVisibility(View.VISIBLE);
                holder.temperature_degree.setText("PROPOSED"+" "+teachPlanDataArrayList.get(postion).getTOPIC_TENTITIVE_STATUS());
            }else {
                holder.temperature_degree.setVisibility(View.INVISIBLE);
            }
            holder.txt_sub_description.setText(teachPlanDataArrayList.get(postion).getTOPIC_DESCRIPTION());
            holder.temperature_degree.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
            if (teachPlanDataArrayList.get(postion).getCovered_Status().equalsIgnoreCase("Y")){
                holder.txt_coverdDate.setTextColor(ContextCompat.getColor(Mcontext, R.color.present));
                holder.txt_coverdDate.setText("COVERED"+" "+teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }else {
                holder.txt_coverdDate.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
                holder.txt_coverdDate.setText(teachPlanDataArrayList.get(postion).getComb_Covered_date());
            }

        }

       // holder.txt_top_name.setText(univSyllabusSubject.get(postion).getSYLLABUS_DESC());
      //  holder.txt_sub_description.setText(teachPlanDataArrayList.get(postion).getTOPIC_DESCRIPTION());


    }

    @Override
    public int getItemCount() {
        return teachPlanDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  txt_sub_name,txt_Tm,txt_tolLecture,txt_Com_Lecture,total_unit_Value,txt_prof_Name,t,tt,txt_syllabus,txt_teachningPlan,txt_Status;
        private ImageView src_img;
        private RecyclerView even_img_recyclerView;
        private CardView  card_view_sub;
        private RatingBar ratingBar;
        private LinearLayout  lay_dateView;
        private TextView txt_top_name,txt_sub_description,temperature_degree,UnitNo,txt_coverdDate;
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

            txt_coverdDate= (TextView)itemView.findViewById(R.id.txt_coverdDate);
            lay_dateView = (LinearLayout) itemView.findViewById(R.id.lay_dateView);



            lay_dateView.setVisibility(View.VISIBLE);
            txt_top_name.setTypeface(type_faceHeading);
            txt_sub_description.setTypeface(type_faceHeading);
            txt_sub_description.setTextSize(12);
            UnitNo.setTypeface(type_faceHeading);
            temperature_degree.setTypeface(type_faceContent);
            txt_coverdDate.setTypeface(type_faceContent);
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
