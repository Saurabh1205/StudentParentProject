package com.wordpro.studentproject.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;

import static com.wordpro.studentproject.activities.NavigationActivity.entExamDtlsArrayList;
import static com.wordpro.studentproject.activities.NavigationActivity.selfRegistionDtlsArrayList;
import static com.wordpro.studentproject.activities.NavigationActivity.studentDtlsArrayList;

/**
 * Created by wccs1980 on 05-06-2018.
 */

public class EduInfoFrag extends Fragment {

    Context context;

    public EduInfoFrag() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.frag_educ_info, container, false);
        Typeface typefaceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Medium.otf");

        if (selfRegistionDtlsArrayList != null) {
            TextView txtSTU_SELF_REG_CODE = (TextView) rootView.findViewById(R.id.txtSTU_SELF_REG_CODE);
            txtSTU_SELF_REG_CODE.setTypeface(typefaceContent);
            txtSTU_SELF_REG_CODE.setText(": "+selfRegistionDtlsArrayList.get(0).getSTU_SELF_REGISTRATION_CODE());
        }

        if (studentDtlsArrayList != null) {

            TextView txtACADEMIC_YEAR = (TextView) rootView.findViewById(R.id.txtACADEMIC_YEAR);
            txtACADEMIC_YEAR.setTypeface(typefaceContent);
            txtACADEMIC_YEAR.setText(": "+studentDtlsArrayList.get(0).getACADEMIC_YEAR());
            TextView txtAdmissionPattern = (TextView) rootView.findViewById(R.id.txtAdmissionPattern);
            txtAdmissionPattern.setTypeface(typefaceContent);
            txtAdmissionPattern.setText(": "+studentDtlsArrayList.get(0).getADMISSION_PATTERN());
            TextView txtADMISSION_STATUS=(TextView)rootView.findViewById(R.id.txtADMISSION_STATUS);
            txtADMISSION_STATUS.setTypeface(typefaceContent);
            txtADMISSION_STATUS.setText(": "+studentDtlsArrayList.get(0).getAdmisstion_status_new());
            TextView txtSection=(TextView)rootView.findViewById(R.id.txtSection);
            txtSection.setTypeface(typefaceContent);
            txtSection.setText(": "+studentDtlsArrayList.get(0).getSECTION_NAME());

          /*  if(!studentDtlsArrayList.get(0).getSECTION_NAME().equalsIgnoreCase("null")){
                txtSection.setText(": "+studentDtlsArrayList.get(0).getSECTION_NAME());

            }else{
                txtSection.setText(": ");
            }*/
            TextView txtStudentCode=(TextView)rootView.findViewById(R.id.txtStudentCode);
            txtStudentCode.setTypeface(typefaceContent);
            txtStudentCode.setText(": "+studentDtlsArrayList.get(0).getSTUDENT_CODE());
            TextView txtDateReg=(TextView)rootView.findViewById(R.id.txtDateReg);
            txtDateReg.setTypeface(typefaceContent);
            txtDateReg.setText(": ");
            TextView txtAdmissionMode=(TextView)rootView.findViewById(R.id.txtAdmissionMode);
            txtAdmissionMode.setTypeface(typefaceContent);
            txtAdmissionMode.setText(": "+studentDtlsArrayList.get(0).getAdmission_mode());
            TextView txtAdmitAcadYr=(TextView)rootView.findViewById(R.id.txtAdmitAcadYr);
            txtAdmitAcadYr.setTypeface(typefaceContent);
            txtAdmitAcadYr.setText(": "+studentDtlsArrayList.get(0).getADMIT_ACADEMIC_YEAR());
            TextView txtCurrentAcadYr =(TextView)rootView.findViewById(R.id.txtCurrentAcadYr);
            txtCurrentAcadYr.setText(": ");
            txtCurrentAcadYr.setTypeface(typefaceContent);
            TextView txtExamName=(TextView)rootView.findViewById(R.id.txtExamName);
            txtExamName.setTypeface(typefaceContent);
            txtExamName.setText(": ");

        }

        if(entExamDtlsArrayList !=null){

            TextView txtMarks=(TextView)rootView.findViewById(R.id.txtMarks);
            txtMarks.setTypeface(typefaceContent);
            txtMarks.setText(": "+entExamDtlsArrayList.get(0).getMARKS_OUT_OF());

            TextView txtTotalMrks=(TextView)rootView.findViewById(R.id.txtTotalMrks);
            txtTotalMrks.setTypeface(typefaceContent);
            txtTotalMrks.setText(": "+entExamDtlsArrayList.get(0).getTOTAL_MARKS());

            TextView txtStateRank=(TextView)rootView.findViewById(R.id.txtStateRank);
            txtStateRank.setTypeface(typefaceContent);
            txtStateRank.setText(": "+entExamDtlsArrayList.get(0).getSTATE_RANK());

            TextView txtRollNo=(TextView)rootView.findViewById(R.id.txtRollNo);
            txtRollNo.setTypeface(typefaceContent);
            txtRollNo.setText(": "+entExamDtlsArrayList.get(0).getROLL_NUMBER());

        }

        return rootView;
    }
}
