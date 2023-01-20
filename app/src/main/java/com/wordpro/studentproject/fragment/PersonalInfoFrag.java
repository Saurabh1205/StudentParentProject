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

import org.w3c.dom.Text;

import static com.wordpro.studentproject.activities.NavigationActivity.addressDtlsArrayList;
import static com.wordpro.studentproject.activities.NavigationActivity.studentDtlsArrayList;

/**
 * Created by wccs1980 on 05-06-2018.
 */

public class PersonalInfoFrag extends Fragment {

    Context context;
    Typeface typefaceHeading,typefaceContent;

    public PersonalInfoFrag() {
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

        final View rootView = inflater.inflate(R.layout.frag_personal_info, container, false);
         typefaceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-BoldItalic.otf");
         typefaceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Medium.otf");
        if(studentDtlsArrayList!=null) {

            TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10;
            TextView txt11,txt12,txt13,txt14,txt15,txt16,txt17,txt18,txt19,txt20;
            TextView txt21,txt22,txt23,txt24,txt25,txt26,txt27,txt28,txt29,txt30;
            TextView txt31,txt32,txt33,txt34,txt35,txt36,txt37,txt38,txt39,txt40;

            txt1=(TextView)rootView.findViewById(R.id.txt1);
            txt1.setTypeface(typefaceContent);
            txt2=(TextView)rootView.findViewById(R.id.txt2);
            txt2.setTypeface(typefaceContent);
            txt3=(TextView)rootView.findViewById(R.id.txt3);
            txt3.setTypeface(typefaceContent);
            txt4=(TextView)rootView.findViewById(R.id.txt4);
            txt4.setTypeface(typefaceContent);
            txt5=(TextView)rootView.findViewById(R.id.txt5);
            txt5.setTypeface(typefaceContent);
            txt6=(TextView)rootView.findViewById(R.id.txt6);
            txt6.setTypeface(typefaceContent);
            txt7=(TextView)rootView.findViewById(R.id.txt7);
            txt7.setTypeface(typefaceContent);
            txt8=(TextView)rootView.findViewById(R.id.txt8);
            txt8.setTypeface(typefaceContent);
            txt9=(TextView)rootView.findViewById(R.id.txt9);
            txt9.setTypeface(typefaceContent);
            txt10=(TextView)rootView.findViewById(R.id.txt10);
            txt10.setTypeface(typefaceContent);
            txt11=(TextView)rootView.findViewById(R.id.txt11);
            txt11.setTypeface(typefaceContent);
            txt12=(TextView)rootView.findViewById(R.id.txt12);
            txt12.setTypeface(typefaceContent);
            txt13=(TextView)rootView.findViewById(R.id.txt13);
            txt13.setTypeface(typefaceContent);
            txt14=(TextView)rootView.findViewById(R.id.txt14);
            txt14.setTypeface(typefaceContent);
            txt15=(TextView)rootView.findViewById(R.id.txt15);
            txt15.setTypeface(typefaceContent);
            txt16=(TextView)rootView.findViewById(R.id.txt16);
            txt16.setTypeface(typefaceContent);
            txt17=(TextView)rootView.findViewById(R.id.txt17);
            txt17.setTypeface(typefaceContent);
            txt18=(TextView)rootView.findViewById(R.id.txt18);
            txt18.setTypeface(typefaceContent);
            txt19=(TextView)rootView.findViewById(R.id.txt19);
            txt19.setTypeface(typefaceContent);
            txt20=(TextView)rootView.findViewById(R.id.txt20);
            txt20.setTypeface(typefaceContent);
            txt21=(TextView)rootView.findViewById(R.id.txt21);
            txt21.setTypeface(typefaceContent);
            txt22=(TextView)rootView.findViewById(R.id.txt22);
            txt22.setTypeface(typefaceContent);
            txt23=(TextView)rootView.findViewById(R.id.txt23);
            txt23.setTypeface(typefaceContent);
            txt24=(TextView)rootView.findViewById(R.id.txt24);
            txt24.setTypeface(typefaceContent);
            txt25=(TextView)rootView.findViewById(R.id.txt25);
            txt25.setTypeface(typefaceContent);
            txt26=(TextView)rootView.findViewById(R.id.txt26);
            txt26.setTypeface(typefaceContent);
            txt27=(TextView)rootView.findViewById(R.id.txt27);
            txt27.setTypeface(typefaceContent);
            txt28=(TextView)rootView.findViewById(R.id.txt28);
            txt28.setTypeface(typefaceContent);
            txt29=(TextView)rootView.findViewById(R.id.txt29);
            txt29.setTypeface(typefaceContent);
            txt30=(TextView)rootView.findViewById(R.id.txt30);
            txt30.setTypeface(typefaceContent);
            txt31=(TextView)rootView.findViewById(R.id.txt31);
            txt31.setTypeface(typefaceContent);
            txt32=(TextView)rootView.findViewById(R.id.txt32);
            txt32.setTypeface(typefaceContent);
            txt33=(TextView)rootView.findViewById(R.id.txt33);
            txt33.setTypeface(typefaceContent);
            txt34=(TextView)rootView.findViewById(R.id.txt34);
            txt34.setTypeface(typefaceContent);
            txt35=(TextView)rootView.findViewById(R.id.txt35);
            txt35.setTypeface(typefaceContent);
            txt36=(TextView)rootView.findViewById(R.id.txt36);
            txt36.setTypeface(typefaceContent);
            txt37=(TextView)rootView.findViewById(R.id.txt37);
            txt37.setTypeface(typefaceContent);
            txt38=(TextView)rootView.findViewById(R.id.txt38);
            txt38.setTypeface(typefaceContent);
            txt39=(TextView)rootView.findViewById(R.id.txt39);
            txt39.setTypeface(typefaceContent);
            txt40=(TextView)rootView.findViewById(R.id.txt40);
            txt40.setTypeface(typefaceContent);

            TextView txtNameTC = (TextView) rootView.findViewById(R.id.txtNameTC);
            txtNameTC.setTypeface(typefaceContent);
            txtNameTC.setText(": " + studentDtlsArrayList.get(0).getSTU_NAME_AS_PER_TC());
            TextView txtDOB = (TextView) rootView.findViewById(R.id.txtDOB);
            txtDOB.setTypeface(typefaceContent);
            txtDOB.setText(": " + studentDtlsArrayList.get(0).getDATE_OF_BIRTH());
            TextView txtBirthPlace = (TextView) rootView.findViewById(R.id.txtBirthPlace);
            txtBirthPlace.setTypeface(typefaceContent);
            txtBirthPlace.setText(": " + studentDtlsArrayList.get(0).getPLACE_OF_BIRTH());
            TextView txtNationality = (TextView) rootView.findViewById(R.id.txtNationality);
            txtNationality.setTypeface(typefaceContent);
            txtNationality.setText(": " + studentDtlsArrayList.get(0).getNATIONALITY_DESCRIPTION());
            TextView txtReligion = (TextView) rootView.findViewById(R.id.txtReligion);
            txtReligion.setTypeface(typefaceContent);
            txtReligion.setText(": " + studentDtlsArrayList.get(0).getRELIGION_DESC());
            TextView txtGender = (TextView) rootView.findViewById(R.id.txtGender);
            txtGender.setTypeface(typefaceContent);
            txtGender.setText(": " + studentDtlsArrayList.get(0).getGENDER());
            TextView txtDomicileState = (TextView) rootView.findViewById(R.id.txtDomicileState);
            txtDomicileState.setTypeface(typefaceContent);
            //txtDomicileState.setText(": " + studentDtlsArrayList.get(0).getDOMICILE_COUNTRY_ID());
            txtDomicileState.setText(": " + studentDtlsArrayList.get(0).getBIRTH_STATE_CODE());
            TextView txtCategory = (TextView) rootView.findViewById(R.id.txtCatgry);
            txtCategory.setTypeface(typefaceContent);
            txtCategory.setText(": " + studentDtlsArrayList.get(0).getCASTE_CATEGORY_DESC());
            TextView txtCaste = (TextView) rootView.findViewById(R.id.txtCaste);
            txtCaste.setTypeface(typefaceContent);
            txtCaste.setText(": " + studentDtlsArrayList.get(0).getCASTE_DESCRIPTION());
            TextView txtSubcaste = (TextView) rootView.findViewById(R.id.txtSubcaste);
            txtSubcaste.setTypeface(typefaceContent);
            txtSubcaste.setText(": " + studentDtlsArrayList.get(0).getSUB_CASTE_DESCRIPTION());
            TextView txtCasteTc = (TextView) rootView.findViewById(R.id.txtCasteTc);
            txtCasteTc.setTypeface(typefaceContent);
            txtCasteTc.setText(": " + studentDtlsArrayList.get(0).getCASTE_AS_PER_TC());
            TextView txtMotherTongue = (TextView) rootView.findViewById(R.id.txtMotherTongue);
            txtMotherTongue.setTypeface(typefaceContent);
            txtMotherTongue.setText(": " + studentDtlsArrayList.get(0).getLANGUAGE_NAME());
            TextView txtLastSchlClg = (TextView) rootView.findViewById(R.id.txtLastSchlClg);
            txtLastSchlClg.setTypeface(typefaceContent);
            txtLastSchlClg.setText(": " + studentDtlsArrayList.get(0).getLAST_SCHOOL_COLLEGE_ATTEND());
            TextView txtQualifExam = (TextView) rootView.findViewById(R.id.txtQualifExam);
            txtQualifExam.setTypeface(typefaceContent);
            txtQualifExam.setText(": " + studentDtlsArrayList.get(0).getQUAL_EXAM_TYPE());
            TextView txtExamRollNo = (TextView) rootView.findViewById(R.id.txtExamRollNo);
            txtExamRollNo.setTypeface(typefaceContent);
            txtExamRollNo.setText(":");
            TextView txtMarkScore = (TextView) rootView.findViewById(R.id.txtMarkScore);
            txtMarkScore.setTypeface(typefaceContent);
            txtMarkScore.setText(": ");
            TextView txtMobileNo = (TextView) rootView.findViewById(R.id.txtMobileNo);
            txtMobileNo.setTypeface(typefaceContent);
            txtMobileNo.setText(": " + studentDtlsArrayList.get(0).getSTUDENT_MOBILE_NO());
            TextView txtGuardianMblNo = (TextView) rootView.findViewById(R.id.txtGuardianMblNo);
            txtGuardianMblNo.setTypeface(typefaceContent);
            txtGuardianMblNo.setText(": " + studentDtlsArrayList.get(0).getGUARDIAN_MOBILE_NO());
            TextView txtMotherName = (TextView) rootView.findViewById(R.id.txtMotherName);
            txtMotherName.setTypeface(typefaceContent);
            txtMotherName.setText(": " + studentDtlsArrayList.get(0).getMOTHER_NAME());
            TextView txtParentMobileNo = (TextView) rootView.findViewById(R.id.txtParentMobileNo);
            txtParentMobileNo.setTypeface(typefaceContent);
            txtParentMobileNo.setText(": " + studentDtlsArrayList.get(0).getPARENT_MOBILE_NO());
            TextView txtEntrollmentNo = (TextView) rootView.findViewById(R.id.txtEntrollmentNo);
            txtEntrollmentNo.setTypeface(typefaceContent);
            txtEntrollmentNo.setText(": " + studentDtlsArrayList.get(0).getENROLLMENT_NUMBER());
            TextView txtMigrationNo = (TextView) rootView.findViewById(R.id.txtMigrationNo);
            txtEntrollmentNo.setTypeface(typefaceContent);
            txtMigrationNo.setText(": " + studentDtlsArrayList.get(0).getMIGRATION_NUMBER());
            TextView txtGuardianEmailId = (TextView) rootView.findViewById(R.id.txtGuardianEmailId);
            txtGuardianEmailId.setTypeface(typefaceContent);
            txtGuardianEmailId.setText(": " + studentDtlsArrayList.get(0).getGUARDIAN_EMAIL_ID());
            TextView txtFatherEmailId = (TextView) rootView.findViewById(R.id.txtFatherEmailId);
            txtFatherEmailId.setTypeface(typefaceContent);
            txtFatherEmailId.setText(": " + studentDtlsArrayList.get(0).getFATHER_EMAIL_ID());
            TextView txtMotherEmailID = (TextView) rootView.findViewById(R.id.txtMotherEmailID);
            txtMotherEmailID.setTypeface(typefaceContent);
            txtMotherEmailID.setText(": " + studentDtlsArrayList.get(0).getMOTHER_EMAIL_ID());
            TextView txtDteUserId = (TextView) rootView.findViewById(R.id.txtDteUserId);
            txtDteUserId.setTypeface(typefaceContent);
            txtDteUserId.setText(": " + studentDtlsArrayList.get(0).getDTE_USER_ID());
            TextView txtDteUserPassword = (TextView) rootView.findViewById(R.id.txtDteUserPassword);
            txtDteUserPassword.setTypeface(typefaceContent);
            txtDteUserPassword.setText(": " + studentDtlsArrayList.get(0).getDTE_USER_PASSWORD());
            TextView txtMotherDomicleMaharsht = (TextView) rootView.findViewById(R.id.txtMotherDomicleMaharsht);
            txtMotherDomicleMaharsht.setTypeface(typefaceContent);
            txtMotherDomicleMaharsht.setText(": " + studentDtlsArrayList.get(0).getMOTH_DOMICLE());
            TextView txtNRI_POI = (TextView) rootView.findViewById(R.id.txtNRI_POI);
            txtNRI_POI.setTypeface(typefaceContent);
            txtNRI_POI.setText(": " + studentDtlsArrayList.get(0).getNRI_POI());
            TextView txtBirthStateCode = (TextView) rootView.findViewById(R.id.txtBirthStateCode);
            txtBirthStateCode.setTypeface(typefaceContent);
            txtBirthStateCode.setText(": " + studentDtlsArrayList.get(0).getBIRTH_STATE_CODE());
            TextView txtBloodgroup = (TextView) rootView.findViewById(R.id.txtBloodgroup);
            txtBloodgroup.setTypeface(typefaceContent);
            txtBloodgroup.setText(": " + studentDtlsArrayList.get(0).getBLOODGROUP());
            TextView txtParentOccupation = (TextView) rootView.findViewById(R.id.txtParentOccupation);
            txtParentOccupation.setTypeface(typefaceContent);
            txtParentOccupation.setText(": " + studentDtlsArrayList.get(0).getOCCUPATION());
            TextView txtParentIncome = (TextView) rootView.findViewById(R.id.txtParentIncome);
            txtParentIncome.setTypeface(typefaceContent);
            txtParentIncome.setText(": " + studentDtlsArrayList.get(0).getPARENT_INCOME());
            TextView txtHobby = (TextView) rootView.findViewById(R.id.txtHobby);
            txtHobby.setTypeface(typefaceContent);
            txtHobby.setText(": " + studentDtlsArrayList.get(0).getHOBBY());
            TextView txtDomicileCountryOther = (TextView) rootView.findViewById(R.id.txtDomicileCountryOther);
            txtDomicileCountryOther.setTypeface(typefaceContent);
            txtDomicileCountryOther.setText(": "+studentDtlsArrayList.get(0).getDOMICILE_COUNTRY_CODE());
            TextView txtLandlineNo = (TextView) rootView.findViewById(R.id.txtLandlineNo);
            txtLandlineNo.setTypeface(typefaceContent);
            txtLandlineNo.setText(": " + studentDtlsArrayList.get(0).getLANDLINE_NO());
            TextView txtFatherDomMahrshtr = (TextView) rootView.findViewById(R.id.txtFatherDomMahrshtr);
            txtFatherDomMahrshtr.setTypeface(typefaceContent);
            txtFatherDomMahrshtr.setText(": " + studentDtlsArrayList.get(0).getFATH_DOMICLE());
            TextView txtPrevEduPreced = (TextView) rootView.findViewById(R.id.txtPrevEduPreced);
            txtPrevEduPreced.setTypeface(typefaceContent);
            txtPrevEduPreced.setText(": " + studentDtlsArrayList.get(0).getPREV_EDU_PREC_3YRS_THIS_STAT());

        }

        if(addressDtlsArrayList !=null){

            TextView txtPermanentAddress=(TextView)rootView.findViewById(R.id.txtPermanentAddress);
            txtPermanentAddress.setTypeface(typefaceContent);
            txtPermanentAddress.setText(": "+addressDtlsArrayList.get(0).getPLOT_NUMBER()+" "+addressDtlsArrayList.get(0).getSTREET_NAME()+" "+addressDtlsArrayList.get(0).getTAHSIL_DESCRIPTION()+"   "+addressDtlsArrayList.get(0).getSTUDENT_ADDRESS1()+" "+addressDtlsArrayList.get(0).getPINCODE());

            TextView txtCorrespondanceAddress=(TextView)rootView.findViewById(R.id.txtCorrespondanceAddress);
            txtCorrespondanceAddress.setTypeface(typefaceContent);
            txtCorrespondanceAddress.setText(": "+addressDtlsArrayList.get(0).getPLOT_NUMBER()+" "+addressDtlsArrayList.get(0).getSTREET_NAME()+" "+addressDtlsArrayList.get(0).getTAHSIL_DESCRIPTION()+"   "+addressDtlsArrayList.get(0).getSTUDENT_ADDRESS1()+" "+addressDtlsArrayList.get(0).getPINCODE());
        }

        return rootView;
    }
}
