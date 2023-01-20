package com.wordpro.studentproject.model;

import java.util.List;

/**
 * Created by wccs1980 on 08-05-2018.
 */

public class DaywiseAttndModel {

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * CENTRE_CODE : ENGG_SC
         * CENTRE_NAME : K. K. Wagh Institute of Engineering Education & Research
         * DEPARTMENT_NUMBER : 8
         * DEPARTMENT_NAME : Electrical Engineering
         * DEPTT_SHORT_CODE : ELEC
         * BRANCH_STANDARD_ID : 24
         * BRANCH_STANDARD_CODE : SE-ELEC-1ST SHIFT
         * ORG_SEMESTER_MST_ID : 4
         * ORG_SEMESTER_NAME : SEMESTER IV
         * STU_BATCH_MST_ID : 3253
         * BATCH_NAME : ALL
         * STU_BATCH_DET_ID : 6069
         * SUB_BATCH_NAME : ALL
         * STUD_IND_BATCH_DTL_ID : 157782
         * STUDENT_ID : 1032160253
         * ABBR_YRLY_ROLL_NUMBER :
         * YEARLY_ROLL_NUMBER : 54
         * TITLE : Ms.
         * FIRST_NAME : BHUMIKA
         * MIDDLE_NAME : V.
         * LAST_NAME : SARODE
         * STUDENT_NAME : Ms. Sarode Bhumika V.
         * SUBJECT_DETAIL_ID : 8201
         * SUBJECT_DESCRIPTION : Numerical Methods and Computer Programming (2015 course)
         * SUB_SHORT_DESCRIPT : NMCP
         * APPLICABLE_NUMBER : 1
         * TYPE_DESCRIPTION : THEORY
         * TYPE_SHORT_NAME : TH
         * EMPLOYEE_ID : 1039140331
         * PROF_EMP_FN_MN_SHORT : Prof Mrs. N.N.Jangle
         * STU_MRK_LOCK_ID : 1108467
         * ATTENDANCE_DATE : 12/18/2017 12:00:00 AM
         * PERIOD_TYPE : REGULAR
         * PERIOD_SEQUENCE_NO : 4
         * PERIOD_FROM_TIME : 10:40 AM
         * PERIOD_UPTO_TIME : 11:35 AM
         * ATTENDANCE_LOCK : Y
         * ATTENDANCE_STATUS : PERIOD_TAKEN
         * STUDENT_STATUS : ABSENT
         * TOPIC_DESCRIPTION : Basics of ‘C’ language , Data Types#
         */

        private String CENTRE_CODE;
        private String CENTRE_NAME;
        private String DEPARTMENT_NUMBER;
        private String DEPARTMENT_NAME;
        private String DEPTT_SHORT_CODE;
        private String BRANCH_STANDARD_ID;
        private String BRANCH_STANDARD_CODE;
        private String ORG_SEMESTER_MST_ID;
        private String ORG_SEMESTER_NAME;
        private String STU_BATCH_MST_ID;
        private String BATCH_NAME;
        private String STU_BATCH_DET_ID;
        private String SUB_BATCH_NAME;
        private String STUD_IND_BATCH_DTL_ID;
        private String STUDENT_ID;
        private String ABBR_YRLY_ROLL_NUMBER;
        private String YEARLY_ROLL_NUMBER;
        private String TITLE;
        private String FIRST_NAME;
        private String MIDDLE_NAME;
        private String LAST_NAME;
        private String STUDENT_NAME;
        private String SUBJECT_DETAIL_ID;
        private String SUBJECT_DESCRIPTION;
        private String SUB_SHORT_DESCRIPT;
        private String APPLICABLE_NUMBER;
        private String TYPE_DESCRIPTION;
        private String TYPE_SHORT_NAME;
        private String EMPLOYEE_ID;
        private String PROF_EMP_FN_MN_SHORT;
        private String STU_MRK_LOCK_ID;
        private String ATTENDANCE_DATE;
        private String PERIOD_TYPE;
        private String PERIOD_SEQUENCE_NO;
        private String PERIOD_FROM_TIME;
        private String PERIOD_UPTO_TIME;
        private String ATTENDANCE_LOCK;
        private String ATTENDANCE_STATUS;
        private String STUDENT_STATUS;
        private String TOPIC_DESCRIPTION;
        private String CommonDate;

        public String getCommonDate() {
            return CommonDate;
        }

        public void setCommonDate(String commonDate) {
            CommonDate = commonDate;
        }

        public String getCENTRE_CODE() {
            return CENTRE_CODE;
        }

        public void setCENTRE_CODE(String CENTRE_CODE) {
            this.CENTRE_CODE = CENTRE_CODE;
        }

        public String getCENTRE_NAME() {
            return CENTRE_NAME;
        }

        public void setCENTRE_NAME(String CENTRE_NAME) {
            this.CENTRE_NAME = CENTRE_NAME;
        }

        public String getDEPARTMENT_NUMBER() {
            return DEPARTMENT_NUMBER;
        }

        public void setDEPARTMENT_NUMBER(String DEPARTMENT_NUMBER) {
            this.DEPARTMENT_NUMBER = DEPARTMENT_NUMBER;
        }

        public String getDEPARTMENT_NAME() {
            return DEPARTMENT_NAME;
        }

        public void setDEPARTMENT_NAME(String DEPARTMENT_NAME) {
            this.DEPARTMENT_NAME = DEPARTMENT_NAME;
        }

        public String getDEPTT_SHORT_CODE() {
            return DEPTT_SHORT_CODE;
        }

        public void setDEPTT_SHORT_CODE(String DEPTT_SHORT_CODE) {
            this.DEPTT_SHORT_CODE = DEPTT_SHORT_CODE;
        }

        public String getBRANCH_STANDARD_ID() {
            return BRANCH_STANDARD_ID;
        }

        public void setBRANCH_STANDARD_ID(String BRANCH_STANDARD_ID) {
            this.BRANCH_STANDARD_ID = BRANCH_STANDARD_ID;
        }

        public String getBRANCH_STANDARD_CODE() {
            return BRANCH_STANDARD_CODE;
        }

        public void setBRANCH_STANDARD_CODE(String BRANCH_STANDARD_CODE) {
            this.BRANCH_STANDARD_CODE = BRANCH_STANDARD_CODE;
        }

        public String getORG_SEMESTER_MST_ID() {
            return ORG_SEMESTER_MST_ID;
        }

        public void setORG_SEMESTER_MST_ID(String ORG_SEMESTER_MST_ID) {
            this.ORG_SEMESTER_MST_ID = ORG_SEMESTER_MST_ID;
        }

        public String getORG_SEMESTER_NAME() {
            return ORG_SEMESTER_NAME;
        }

        public void setORG_SEMESTER_NAME(String ORG_SEMESTER_NAME) {
            this.ORG_SEMESTER_NAME = ORG_SEMESTER_NAME;
        }

        public String getSTU_BATCH_MST_ID() {
            return STU_BATCH_MST_ID;
        }

        public void setSTU_BATCH_MST_ID(String STU_BATCH_MST_ID) {
            this.STU_BATCH_MST_ID = STU_BATCH_MST_ID;
        }

        public String getBATCH_NAME() {
            return BATCH_NAME;
        }

        public void setBATCH_NAME(String BATCH_NAME) {
            this.BATCH_NAME = BATCH_NAME;
        }

        public String getSTU_BATCH_DET_ID() {
            return STU_BATCH_DET_ID;
        }

        public void setSTU_BATCH_DET_ID(String STU_BATCH_DET_ID) {
            this.STU_BATCH_DET_ID = STU_BATCH_DET_ID;
        }

        public String getSUB_BATCH_NAME() {
            return SUB_BATCH_NAME;
        }

        public void setSUB_BATCH_NAME(String SUB_BATCH_NAME) {
            this.SUB_BATCH_NAME = SUB_BATCH_NAME;
        }

        public String getSTUD_IND_BATCH_DTL_ID() {
            return STUD_IND_BATCH_DTL_ID;
        }

        public void setSTUD_IND_BATCH_DTL_ID(String STUD_IND_BATCH_DTL_ID) {
            this.STUD_IND_BATCH_DTL_ID = STUD_IND_BATCH_DTL_ID;
        }

        public String getSTUDENT_ID() {
            return STUDENT_ID;
        }

        public void setSTUDENT_ID(String STUDENT_ID) {
            this.STUDENT_ID = STUDENT_ID;
        }

        public String getABBR_YRLY_ROLL_NUMBER() {
            return ABBR_YRLY_ROLL_NUMBER;
        }

        public void setABBR_YRLY_ROLL_NUMBER(String ABBR_YRLY_ROLL_NUMBER) {
            this.ABBR_YRLY_ROLL_NUMBER = ABBR_YRLY_ROLL_NUMBER;
        }

        public String getYEARLY_ROLL_NUMBER() {
            return YEARLY_ROLL_NUMBER;
        }

        public void setYEARLY_ROLL_NUMBER(String YEARLY_ROLL_NUMBER) {
            this.YEARLY_ROLL_NUMBER = YEARLY_ROLL_NUMBER;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
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

        public String getSTUDENT_NAME() {
            return STUDENT_NAME;
        }

        public void setSTUDENT_NAME(String STUDENT_NAME) {
            this.STUDENT_NAME = STUDENT_NAME;
        }

        public String getSUBJECT_DETAIL_ID() {
            return SUBJECT_DETAIL_ID;
        }

        public void setSUBJECT_DETAIL_ID(String SUBJECT_DETAIL_ID) {
            this.SUBJECT_DETAIL_ID = SUBJECT_DETAIL_ID;
        }

        public String getSUBJECT_DESCRIPTION() {
            return SUBJECT_DESCRIPTION;
        }

        public void setSUBJECT_DESCRIPTION(String SUBJECT_DESCRIPTION) {
            this.SUBJECT_DESCRIPTION = SUBJECT_DESCRIPTION;
        }

        public String getSUB_SHORT_DESCRIPT() {
            return SUB_SHORT_DESCRIPT;
        }

        public void setSUB_SHORT_DESCRIPT(String SUB_SHORT_DESCRIPT) {
            this.SUB_SHORT_DESCRIPT = SUB_SHORT_DESCRIPT;
        }

        public String getAPPLICABLE_NUMBER() {
            return APPLICABLE_NUMBER;
        }

        public void setAPPLICABLE_NUMBER(String APPLICABLE_NUMBER) {
            this.APPLICABLE_NUMBER = APPLICABLE_NUMBER;
        }

        public String getTYPE_DESCRIPTION() {
            return TYPE_DESCRIPTION;
        }

        public void setTYPE_DESCRIPTION(String TYPE_DESCRIPTION) {
            this.TYPE_DESCRIPTION = TYPE_DESCRIPTION;
        }

        public String getTYPE_SHORT_NAME() {
            return TYPE_SHORT_NAME;
        }

        public void setTYPE_SHORT_NAME(String TYPE_SHORT_NAME) {
            this.TYPE_SHORT_NAME = TYPE_SHORT_NAME;
        }

        public String getEMPLOYEE_ID() {
            return EMPLOYEE_ID;
        }

        public void setEMPLOYEE_ID(String EMPLOYEE_ID) {
            this.EMPLOYEE_ID = EMPLOYEE_ID;
        }

        public String getPROF_EMP_FN_MN_SHORT() {
            return PROF_EMP_FN_MN_SHORT;
        }

        public void setPROF_EMP_FN_MN_SHORT(String PROF_EMP_FN_MN_SHORT) {
            this.PROF_EMP_FN_MN_SHORT = PROF_EMP_FN_MN_SHORT;
        }

        public String getSTU_MRK_LOCK_ID() {
            return STU_MRK_LOCK_ID;
        }

        public void setSTU_MRK_LOCK_ID(String STU_MRK_LOCK_ID) {
            this.STU_MRK_LOCK_ID = STU_MRK_LOCK_ID;
        }

        public String getATTENDANCE_DATE() {
            return ATTENDANCE_DATE;
        }

        public void setATTENDANCE_DATE(String ATTENDANCE_DATE) {
            this.ATTENDANCE_DATE = ATTENDANCE_DATE;
        }

        public String getPERIOD_TYPE() {
            return PERIOD_TYPE;
        }

        public void setPERIOD_TYPE(String PERIOD_TYPE) {
            this.PERIOD_TYPE = PERIOD_TYPE;
        }

        public String getPERIOD_SEQUENCE_NO() {
            return PERIOD_SEQUENCE_NO;
        }

        public void setPERIOD_SEQUENCE_NO(String PERIOD_SEQUENCE_NO) {
            this.PERIOD_SEQUENCE_NO = PERIOD_SEQUENCE_NO;
        }

        public String getPERIOD_FROM_TIME() {
            return PERIOD_FROM_TIME;
        }

        public void setPERIOD_FROM_TIME(String PERIOD_FROM_TIME) {
            this.PERIOD_FROM_TIME = PERIOD_FROM_TIME;
        }

        public String getPERIOD_UPTO_TIME() {
            return PERIOD_UPTO_TIME;
        }

        public void setPERIOD_UPTO_TIME(String PERIOD_UPTO_TIME) {
            this.PERIOD_UPTO_TIME = PERIOD_UPTO_TIME;
        }

        public String getATTENDANCE_LOCK() {
            return ATTENDANCE_LOCK;
        }

        public void setATTENDANCE_LOCK(String ATTENDANCE_LOCK) {
            this.ATTENDANCE_LOCK = ATTENDANCE_LOCK;
        }

        public String getATTENDANCE_STATUS() {
            return ATTENDANCE_STATUS;
        }

        public void setATTENDANCE_STATUS(String ATTENDANCE_STATUS) {
            this.ATTENDANCE_STATUS = ATTENDANCE_STATUS;
        }

        public String getSTUDENT_STATUS() {
            return STUDENT_STATUS;
        }

        public void setSTUDENT_STATUS(String STUDENT_STATUS) {
            this.STUDENT_STATUS = STUDENT_STATUS;
        }

        public String getTOPIC_DESCRIPTION() {
            return TOPIC_DESCRIPTION;
        }

        public void setTOPIC_DESCRIPTION(String TOPIC_DESCRIPTION) {
            this.TOPIC_DESCRIPTION = TOPIC_DESCRIPTION;
        }
    }
}
