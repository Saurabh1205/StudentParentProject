package com.wordpro.studentproject.model;

import java.util.List;

/**
 * Created by wccs1980 on 10-04-2018.
 */

public class ModelStudentDetail {

    private int status;
    private String STATUS;
    private List<DataBean> data;
    private List<PersonalDetailsBean> Personal_Details;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<PersonalDetailsBean> getPersonal_Details() {
        return Personal_Details;
    }

    public void setPersonal_Details(List<PersonalDetailsBean> Personal_Details) {
        this.Personal_Details = Personal_Details;
    }

    public static class DataBean {
        /**
         * STUDENT_ID : 1032180065
         * BRANCH_STANDARD_ID : 9
         * STANDARD_ID : 17
         * ADMISSION_ACTIVE_FLAG : Y
         * ACAD_SESSION_ID : 19
         * ACAD_SESS_NAME : 2018-2019
         * ACAD_CURRENT_SESSION_FLAG : Y
         * YEARLY_ROLL_NUMBER : 38
         * STREAM_ID : 1
         * BRANCH_ID : 39
         * BRANCH_STANDARD_DESCRIPTION : FE-I- IT
         * BRANCH_STANDARD_CODE : FE-I- IT
         * BRANCH_STANDARD_GRP_ID : 47
         * DEPARTMENT_NUMBER : 9
         * MAIN_SEMESTER_MST_ID : 2
         * BRANCH_SEMESTER_MST_ID : 2
         * MAIN_SEMESTER_TYPE : E
         * MAIN_SEMESTER_NAME : SEMESTER II
         * BRANCH_SEMESTER_NAME : SEMESTER II
         * CENTRE_CODE : ENGG_SC
         * CENTRE_GROUP_CODE : ENGG
         * STUDENT_EMAIL_ID : wordpro.app@gmail.com
         * STUDENT_CODE : 0118081004
         */

        private String STUDENT_ID;
        private String BRANCH_STANDARD_ID;
        private String STANDARD_ID;
        private String ADMISSION_ACTIVE_FLAG;
        private String ACAD_SESSION_ID;
        private String ACAD_SESS_NAME;
        private String ACAD_CURRENT_SESSION_FLAG;
        private String YEARLY_ROLL_NUMBER;
        private String STREAM_ID;
        private String BRANCH_ID;
        private String BRANCH_STANDARD_DESCRIPTION;
        private String BRANCH_STANDARD_CODE;
        private String BRANCH_STANDARD_GRP_ID;
        private String DEPARTMENT_NUMBER;
        private String MAIN_SEMESTER_MST_ID;
        private String BRANCH_SEMESTER_MST_ID;
        private String MAIN_SEMESTER_TYPE;
        private String MAIN_SEMESTER_NAME;
        private String BRANCH_SEMESTER_NAME;
        private String CENTRE_CODE;
        private String CENTRE_GROUP_CODE;
        private String STUDENT_EMAIL_ID;
        private String STUDENT_CODE;

        public String getSTUDENT_ID() {
            return STUDENT_ID;
        }

        public void setSTUDENT_ID(String STUDENT_ID) {
            this.STUDENT_ID = STUDENT_ID;
        }

        public String getBRANCH_STANDARD_ID() {
            return BRANCH_STANDARD_ID;
        }

        public void setBRANCH_STANDARD_ID(String BRANCH_STANDARD_ID) {
            this.BRANCH_STANDARD_ID = BRANCH_STANDARD_ID;
        }

        public String getSTANDARD_ID() {
            return STANDARD_ID;
        }

        public void setSTANDARD_ID(String STANDARD_ID) {
            this.STANDARD_ID = STANDARD_ID;
        }

        public String getADMISSION_ACTIVE_FLAG() {
            return ADMISSION_ACTIVE_FLAG;
        }

        public void setADMISSION_ACTIVE_FLAG(String ADMISSION_ACTIVE_FLAG) {
            this.ADMISSION_ACTIVE_FLAG = ADMISSION_ACTIVE_FLAG;
        }

        public String getACAD_SESSION_ID() {
            return ACAD_SESSION_ID;
        }

        public void setACAD_SESSION_ID(String ACAD_SESSION_ID) {
            this.ACAD_SESSION_ID = ACAD_SESSION_ID;
        }

        public String getACAD_SESS_NAME() {
            return ACAD_SESS_NAME;
        }

        public void setACAD_SESS_NAME(String ACAD_SESS_NAME) {
            this.ACAD_SESS_NAME = ACAD_SESS_NAME;
        }

        public String getACAD_CURRENT_SESSION_FLAG() {
            return ACAD_CURRENT_SESSION_FLAG;
        }

        public void setACAD_CURRENT_SESSION_FLAG(String ACAD_CURRENT_SESSION_FLAG) {
            this.ACAD_CURRENT_SESSION_FLAG = ACAD_CURRENT_SESSION_FLAG;
        }

        public String getYEARLY_ROLL_NUMBER() {
            return YEARLY_ROLL_NUMBER;
        }

        public void setYEARLY_ROLL_NUMBER(String YEARLY_ROLL_NUMBER) {
            this.YEARLY_ROLL_NUMBER = YEARLY_ROLL_NUMBER;
        }

        public String getSTREAM_ID() {
            return STREAM_ID;
        }

        public void setSTREAM_ID(String STREAM_ID) {
            this.STREAM_ID = STREAM_ID;
        }

        public String getBRANCH_ID() {
            return BRANCH_ID;
        }

        public void setBRANCH_ID(String BRANCH_ID) {
            this.BRANCH_ID = BRANCH_ID;
        }

        public String getBRANCH_STANDARD_DESCRIPTION() {
            return BRANCH_STANDARD_DESCRIPTION;
        }

        public void setBRANCH_STANDARD_DESCRIPTION(String BRANCH_STANDARD_DESCRIPTION) {
            this.BRANCH_STANDARD_DESCRIPTION = BRANCH_STANDARD_DESCRIPTION;
        }

        public String getBRANCH_STANDARD_CODE() {
            return BRANCH_STANDARD_CODE;
        }

        public void setBRANCH_STANDARD_CODE(String BRANCH_STANDARD_CODE) {
            this.BRANCH_STANDARD_CODE = BRANCH_STANDARD_CODE;
        }

        public String getBRANCH_STANDARD_GRP_ID() {
            return BRANCH_STANDARD_GRP_ID;
        }

        public void setBRANCH_STANDARD_GRP_ID(String BRANCH_STANDARD_GRP_ID) {
            this.BRANCH_STANDARD_GRP_ID = BRANCH_STANDARD_GRP_ID;
        }

        public String getDEPARTMENT_NUMBER() {
            return DEPARTMENT_NUMBER;
        }

        public void setDEPARTMENT_NUMBER(String DEPARTMENT_NUMBER) {
            this.DEPARTMENT_NUMBER = DEPARTMENT_NUMBER;
        }

        public String getMAIN_SEMESTER_MST_ID() {
            return MAIN_SEMESTER_MST_ID;
        }

        public void setMAIN_SEMESTER_MST_ID(String MAIN_SEMESTER_MST_ID) {
            this.MAIN_SEMESTER_MST_ID = MAIN_SEMESTER_MST_ID;
        }

        public String getBRANCH_SEMESTER_MST_ID() {
            return BRANCH_SEMESTER_MST_ID;
        }

        public void setBRANCH_SEMESTER_MST_ID(String BRANCH_SEMESTER_MST_ID) {
            this.BRANCH_SEMESTER_MST_ID = BRANCH_SEMESTER_MST_ID;
        }

        public String getMAIN_SEMESTER_TYPE() {
            return MAIN_SEMESTER_TYPE;
        }

        public void setMAIN_SEMESTER_TYPE(String MAIN_SEMESTER_TYPE) {
            this.MAIN_SEMESTER_TYPE = MAIN_SEMESTER_TYPE;
        }

        public String getMAIN_SEMESTER_NAME() {
            return MAIN_SEMESTER_NAME;
        }

        public void setMAIN_SEMESTER_NAME(String MAIN_SEMESTER_NAME) {
            this.MAIN_SEMESTER_NAME = MAIN_SEMESTER_NAME;
        }

        public String getBRANCH_SEMESTER_NAME() {
            return BRANCH_SEMESTER_NAME;
        }

        public void setBRANCH_SEMESTER_NAME(String BRANCH_SEMESTER_NAME) {
            this.BRANCH_SEMESTER_NAME = BRANCH_SEMESTER_NAME;
        }

        public String getCENTRE_CODE() {
            return CENTRE_CODE;
        }

        public void setCENTRE_CODE(String CENTRE_CODE) {
            this.CENTRE_CODE = CENTRE_CODE;
        }

        public String getCENTRE_GROUP_CODE() {
            return CENTRE_GROUP_CODE;
        }

        public void setCENTRE_GROUP_CODE(String CENTRE_GROUP_CODE) {
            this.CENTRE_GROUP_CODE = CENTRE_GROUP_CODE;
        }

        public String getSTUDENT_EMAIL_ID() {
            return STUDENT_EMAIL_ID;
        }

        public void setSTUDENT_EMAIL_ID(String STUDENT_EMAIL_ID) {
            this.STUDENT_EMAIL_ID = STUDENT_EMAIL_ID;
        }

        public String getSTUDENT_CODE() {
            return STUDENT_CODE;
        }

        public void setSTUDENT_CODE(String STUDENT_CODE) {
            this.STUDENT_CODE = STUDENT_CODE;
        }
    }

    public static class PersonalDetailsBean {
        /**
         * STUDENT_ID : 1032180065
         * TITLE : Mr.
         * STUDENT_EMAIL_ID : wordpro.app@gmail.com
         * NICK_NAME :
         * FIRST_NAME : BHAVIK
         * MIDDLE_NAME : DEEPAK
         * LAST_NAME : PATEL
         * STUD_FULL_NAME : Mr. Bhavik Deepak Patel
         * STUD_ML_NAME_SHORT : Mr. Bhavik D Patel
         * STUD_FN_MN_SHORT : Mr. B.D.Patel
         * STUD_INITIAL : BDP
         * STUD_LNAMEWISE : Patel Bhavik Deepak
         * STUD_FNAMEWISE : BHAVIK DEEPAK PATEL
         * DATE_OF_BIRTH : 23/01/2001
         * GENDER : M
         * STUDENT_MOBILE_NO : 9284708678
         * PARENT_MOBILE_NO : 9284708678
         * GUARDIAN_MOBILE_NO :
         * FATHER_NAME : MR. Deepak Somji Patel
         */

        private String STUDENT_ID;
        private String TITLE;
        private String STUDENT_EMAIL_ID;
        private String NICK_NAME;
        private String FIRST_NAME;
        private String MIDDLE_NAME;
        private String LAST_NAME;
        private String STUD_FULL_NAME;
        private String STUD_ML_NAME_SHORT;
        private String STUD_FN_MN_SHORT;
        private String STUD_INITIAL;
        private String STUD_LNAMEWISE;
        private String STUD_FNAMEWISE;
        private String DATE_OF_BIRTH;
        private String GENDER;
        private String STUDENT_MOBILE_NO;
        private String PARENT_MOBILE_NO;
        private String GUARDIAN_MOBILE_NO;
        private String FATHER_NAME;

        public String getSTUDENT_ID() {
            return STUDENT_ID;
        }

        public void setSTUDENT_ID(String STUDENT_ID) {
            this.STUDENT_ID = STUDENT_ID;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
        }

        public String getSTUDENT_EMAIL_ID() {
            return STUDENT_EMAIL_ID;
        }

        public void setSTUDENT_EMAIL_ID(String STUDENT_EMAIL_ID) {
            this.STUDENT_EMAIL_ID = STUDENT_EMAIL_ID;
        }

        public String getNICK_NAME() {
            return NICK_NAME;
        }

        public void setNICK_NAME(String NICK_NAME) {
            this.NICK_NAME = NICK_NAME;
        }

        public String getFIRST_NAME() {
            return FIRST_NAME;
        }

        public void setFIRST_NAME(String FIRST_NAME) {
            this.FIRST_NAME = FIRST_NAME;
        }

        public String getMIDDLE_NAME() {
            return MIDDLE_NAME;
        }

        public void setMIDDLE_NAME(String MIDDLE_NAME) {
            this.MIDDLE_NAME = MIDDLE_NAME;
        }

        public String getLAST_NAME() {
            return LAST_NAME;
        }

        public void setLAST_NAME(String LAST_NAME) {
            this.LAST_NAME = LAST_NAME;
        }

        public String getSTUD_FULL_NAME() {
            return STUD_FULL_NAME;
        }

        public void setSTUD_FULL_NAME(String STUD_FULL_NAME) {
            this.STUD_FULL_NAME = STUD_FULL_NAME;
        }

        public String getSTUD_ML_NAME_SHORT() {
            return STUD_ML_NAME_SHORT;
        }

        public void setSTUD_ML_NAME_SHORT(String STUD_ML_NAME_SHORT) {
            this.STUD_ML_NAME_SHORT = STUD_ML_NAME_SHORT;
        }

        public String getSTUD_FN_MN_SHORT() {
            return STUD_FN_MN_SHORT;
        }

        public void setSTUD_FN_MN_SHORT(String STUD_FN_MN_SHORT) {
            this.STUD_FN_MN_SHORT = STUD_FN_MN_SHORT;
        }

        public String getSTUD_INITIAL() {
            return STUD_INITIAL;
        }

        public void setSTUD_INITIAL(String STUD_INITIAL) {
            this.STUD_INITIAL = STUD_INITIAL;
        }

        public String getSTUD_LNAMEWISE() {
            return STUD_LNAMEWISE;
        }

        public void setSTUD_LNAMEWISE(String STUD_LNAMEWISE) {
            this.STUD_LNAMEWISE = STUD_LNAMEWISE;
        }

        public String getSTUD_FNAMEWISE() {
            return STUD_FNAMEWISE;
        }

        public void setSTUD_FNAMEWISE(String STUD_FNAMEWISE) {
            this.STUD_FNAMEWISE = STUD_FNAMEWISE;
        }

        public String getDATE_OF_BIRTH() {
            return DATE_OF_BIRTH;
        }

        public void setDATE_OF_BIRTH(String DATE_OF_BIRTH) {
            this.DATE_OF_BIRTH = DATE_OF_BIRTH;
        }

        public String getGENDER() {
            return GENDER;
        }

        public void setGENDER(String GENDER) {
            this.GENDER = GENDER;
        }

        public String getSTUDENT_MOBILE_NO() {
            return STUDENT_MOBILE_NO;
        }

        public void setSTUDENT_MOBILE_NO(String STUDENT_MOBILE_NO) {
            this.STUDENT_MOBILE_NO = STUDENT_MOBILE_NO;
        }

        public String getPARENT_MOBILE_NO() {
            return PARENT_MOBILE_NO;
        }

        public void setPARENT_MOBILE_NO(String PARENT_MOBILE_NO) {
            this.PARENT_MOBILE_NO = PARENT_MOBILE_NO;
        }

        public String getGUARDIAN_MOBILE_NO() {
            return GUARDIAN_MOBILE_NO;
        }

        public void setGUARDIAN_MOBILE_NO(String GUARDIAN_MOBILE_NO) {
            this.GUARDIAN_MOBILE_NO = GUARDIAN_MOBILE_NO;
        }

        public String getFATHER_NAME() {
            return FATHER_NAME;
        }

        public void setFATHER_NAME(String FATHER_NAME) {
            this.FATHER_NAME = FATHER_NAME;
        }
    }
}
