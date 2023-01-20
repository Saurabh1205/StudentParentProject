package com.wordpro.studentproject.model;

import java.util.List;

/**
 * Created by wccs1980 on 19-04-2018.
 */

public class StudTimeTableModel {

    private int status;
    private List<DataBean> data;
    private List<?> SubCode;

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

    public List<?> getSubCode() {
        return SubCode;
    }

    public void setSubCode(List<?> SubCode) {
        this.SubCode = SubCode;
    }

    public static class DataBean {
        /**
         * DEPARTMENT_NUMBER : 8
         * DEPARTMENT_NAME : Electrical Engineering
         * DEPTT_SHORT_CODE : ELEC
         * DEPTT_PRINT_CODE : ELEC
         * DEPTT_SEQ_NO : 39
         * STANDARD_NUMBER : 18
         * BRANCH_STANDARD_ID : 24
         * BRANCH_STANDARD_CODE : SE-ELEC-1ST SHIFT
         * BRANCH_STANDARD_DESCRIPTION : SE-ELEC-1ST SHIFT
         * SCHEDULAR_PLAN_MST_ID : 228
         * SCHEDULAR_DESCRIPTION : Engg-First Shift-2017
         * WEEK_DAY_ID : 2
         * WEEK_DESCRIPTION : MONDAY
         * TIME_SLOT_ID : 115
         * NUMBER_OF_PERIODS : 6
         * NUMBER_OF_RECESS : 2
         * RECESS_AFTER_PERIOD : 2-93,4-97,
         * PERIOD_SEQUENCE_NO : 1
         * PERIOD_FROM_TIME : 6/12/2017 8:00:00 AM
         * PERIOD_UPTO_TIME : 6/12/2017 8:55:00 AM
         * SCHEDULAR_PLAN_DET1_ID : 1661
         * SCHEDULAR_PLAN_DET2_ID : 10120
         * ACAD_SESS_NAME : 2017-2018
         * APPLI_TYPE_SHORT_NAME :
         * APPLICABLE_NUMBER :
         * BUILDING_ROOM_ID :
         * EMP_INITIAL :
         * EMPLOYEE_CODE :
         * EMPLOYEE_ID : 1039140331
         * MAIN_SEMESTER_MST_ID :
         * MAIN_SEMESTER_NAME :
         * MAIN_SEMESTER_TYPE :
         * SUB_SHORT_DESC :
         * UNIV_SUB_CODE : 203 148
         * PERIOD_GROUP_ID : 7392
         * COMB_SUBJECT_DETAILS : NMCP-PR[S1 1]#NNJ#F204 II#
         * COMB_SUBJECT_DTLS :
         * COMB_SUBJECT_LABS :
         * ROOM_NAME :
         * ROOM_NUMBER :
         * SUBJECT_TYPE_STATUS : GRP
         */

        private String DEPARTMENT_NUMBER;
        private String DEPARTMENT_NAME;
        private String DEPTT_SHORT_CODE;
        private String DEPTT_PRINT_CODE;
        private String DEPTT_SEQ_NO;
        private String STANDARD_NUMBER;
        private String BRANCH_STANDARD_ID;
        private String BRANCH_STANDARD_CODE;
        private String BRANCH_STANDARD_DESCRIPTION;
        private String SCHEDULAR_PLAN_MST_ID;
        private String SCHEDULAR_DESCRIPTION;
        private String WEEK_DAY_ID;
        private String WEEK_DESCRIPTION;
        private String TIME_SLOT_ID;
        private String NUMBER_OF_PERIODS;
        private String NUMBER_OF_RECESS;
        private String RECESS_AFTER_PERIOD;
        private String PERIOD_SEQUENCE_NO;
        private String PERIOD_FROM_TIME;
        private String PERIOD_UPTO_TIME;
        private String SCHEDULAR_PLAN_DET1_ID;
        private String SCHEDULAR_PLAN_DET2_ID;
        private String ACAD_SESS_NAME;
        private String APPLI_TYPE_SHORT_NAME;
        private String APPLICABLE_NUMBER;
        private String BUILDING_ROOM_ID;
        private String EMP_INITIAL;
        private String EMPLOYEE_CODE;
        private String EMPLOYEE_ID;
        private String MAIN_SEMESTER_MST_ID;
        private String MAIN_SEMESTER_NAME;
        private String MAIN_SEMESTER_TYPE;
        private String SUB_SHORT_DESC;
        private String UNIV_SUB_CODE;
        private String PERIOD_GROUP_ID;
        private String COMB_SUBJECT_DETAILS;
        private String COMB_SUBJECT_DTLS;
        private String COMB_SUBJECT_LABS;
        private String ROOM_NAME;
        private String ROOM_NUMBER;
        private String SUBJECT_TYPE_STATUS;
        private String StartTime;
        private String EndTime;
        private String Date;
        private String SUB_BATCH_NAME;
        private String EMP_FULL_NAME;

        public String getSUB_BATCH_NAME() {
            return SUB_BATCH_NAME;
        }

        public void setSUB_BATCH_NAME(String SUB_BATCH_NAME) {
            this.SUB_BATCH_NAME = SUB_BATCH_NAME;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String startTime) {
            StartTime = startTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String endTime) {
            EndTime = endTime;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
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

        public String getDEPTT_PRINT_CODE() {
            return DEPTT_PRINT_CODE;
        }

        public void setDEPTT_PRINT_CODE(String DEPTT_PRINT_CODE) {
            this.DEPTT_PRINT_CODE = DEPTT_PRINT_CODE;
        }

        public String getDEPTT_SEQ_NO() {
            return DEPTT_SEQ_NO;
        }

        public void setDEPTT_SEQ_NO(String DEPTT_SEQ_NO) {
            this.DEPTT_SEQ_NO = DEPTT_SEQ_NO;
        }

        public String getSTANDARD_NUMBER() {
            return STANDARD_NUMBER;
        }

        public void setSTANDARD_NUMBER(String STANDARD_NUMBER) {
            this.STANDARD_NUMBER = STANDARD_NUMBER;
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

        public String getBRANCH_STANDARD_DESCRIPTION() {
            return BRANCH_STANDARD_DESCRIPTION;
        }

        public void setBRANCH_STANDARD_DESCRIPTION(String BRANCH_STANDARD_DESCRIPTION) {
            this.BRANCH_STANDARD_DESCRIPTION = BRANCH_STANDARD_DESCRIPTION;
        }

        public String getSCHEDULAR_PLAN_MST_ID() {
            return SCHEDULAR_PLAN_MST_ID;
        }

        public void setSCHEDULAR_PLAN_MST_ID(String SCHEDULAR_PLAN_MST_ID) {
            this.SCHEDULAR_PLAN_MST_ID = SCHEDULAR_PLAN_MST_ID;
        }

        public String getSCHEDULAR_DESCRIPTION() {
            return SCHEDULAR_DESCRIPTION;
        }

        public void setSCHEDULAR_DESCRIPTION(String SCHEDULAR_DESCRIPTION) {
            this.SCHEDULAR_DESCRIPTION = SCHEDULAR_DESCRIPTION;
        }

        public String getWEEK_DAY_ID() {
            return WEEK_DAY_ID;
        }

        public void setWEEK_DAY_ID(String WEEK_DAY_ID) {
            this.WEEK_DAY_ID = WEEK_DAY_ID;
        }

        public String getWEEK_DESCRIPTION() {
            return WEEK_DESCRIPTION;
        }

        public void setWEEK_DESCRIPTION(String WEEK_DESCRIPTION) {
            this.WEEK_DESCRIPTION = WEEK_DESCRIPTION;
        }

        public String getTIME_SLOT_ID() {
            return TIME_SLOT_ID;
        }

        public void setTIME_SLOT_ID(String TIME_SLOT_ID) {
            this.TIME_SLOT_ID = TIME_SLOT_ID;
        }

        public String getNUMBER_OF_PERIODS() {
            return NUMBER_OF_PERIODS;
        }

        public void setNUMBER_OF_PERIODS(String NUMBER_OF_PERIODS) {
            this.NUMBER_OF_PERIODS = NUMBER_OF_PERIODS;
        }

        public String getNUMBER_OF_RECESS() {
            return NUMBER_OF_RECESS;
        }

        public void setNUMBER_OF_RECESS(String NUMBER_OF_RECESS) {
            this.NUMBER_OF_RECESS = NUMBER_OF_RECESS;
        }

        public String getRECESS_AFTER_PERIOD() {
            return RECESS_AFTER_PERIOD;
        }

        public void setRECESS_AFTER_PERIOD(String RECESS_AFTER_PERIOD) {
            this.RECESS_AFTER_PERIOD = RECESS_AFTER_PERIOD;
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

        public String getSCHEDULAR_PLAN_DET1_ID() {
            return SCHEDULAR_PLAN_DET1_ID;
        }

        public void setSCHEDULAR_PLAN_DET1_ID(String SCHEDULAR_PLAN_DET1_ID) {
            this.SCHEDULAR_PLAN_DET1_ID = SCHEDULAR_PLAN_DET1_ID;
        }

        public String getSCHEDULAR_PLAN_DET2_ID() {
            return SCHEDULAR_PLAN_DET2_ID;
        }

        public void setSCHEDULAR_PLAN_DET2_ID(String SCHEDULAR_PLAN_DET2_ID) {
            this.SCHEDULAR_PLAN_DET2_ID = SCHEDULAR_PLAN_DET2_ID;
        }

        public String getACAD_SESS_NAME() {
            return ACAD_SESS_NAME;
        }

        public void setACAD_SESS_NAME(String ACAD_SESS_NAME) {
            this.ACAD_SESS_NAME = ACAD_SESS_NAME;
        }

        public String getAPPLI_TYPE_SHORT_NAME() {
            return APPLI_TYPE_SHORT_NAME;
        }

        public void setAPPLI_TYPE_SHORT_NAME(String APPLI_TYPE_SHORT_NAME) {
            this.APPLI_TYPE_SHORT_NAME = APPLI_TYPE_SHORT_NAME;
        }

        public String getAPPLICABLE_NUMBER() {
            return APPLICABLE_NUMBER;
        }

        public void setAPPLICABLE_NUMBER(String APPLICABLE_NUMBER) {
            this.APPLICABLE_NUMBER = APPLICABLE_NUMBER;
        }

        public String getBUILDING_ROOM_ID() {
            return BUILDING_ROOM_ID;
        }

        public void setBUILDING_ROOM_ID(String BUILDING_ROOM_ID) {
            this.BUILDING_ROOM_ID = BUILDING_ROOM_ID;
        }

        public String getEMP_INITIAL() {
            return EMP_INITIAL;
        }

        public void setEMP_INITIAL(String EMP_INITIAL) {
            this.EMP_INITIAL = EMP_INITIAL;
        }

        public String getEMPLOYEE_CODE() {
            return EMPLOYEE_CODE;
        }

        public void setEMPLOYEE_CODE(String EMPLOYEE_CODE) {
            this.EMPLOYEE_CODE = EMPLOYEE_CODE;
        }

        public String getEMPLOYEE_ID() {
            return EMPLOYEE_ID;
        }

        public void setEMPLOYEE_ID(String EMPLOYEE_ID) {
            this.EMPLOYEE_ID = EMPLOYEE_ID;
        }

        public String getMAIN_SEMESTER_MST_ID() {
            return MAIN_SEMESTER_MST_ID;
        }

        public void setMAIN_SEMESTER_MST_ID(String MAIN_SEMESTER_MST_ID) {
            this.MAIN_SEMESTER_MST_ID = MAIN_SEMESTER_MST_ID;
        }

        public String getMAIN_SEMESTER_NAME() {
            return MAIN_SEMESTER_NAME;
        }

        public void setMAIN_SEMESTER_NAME(String MAIN_SEMESTER_NAME) {
            this.MAIN_SEMESTER_NAME = MAIN_SEMESTER_NAME;
        }

        public String getMAIN_SEMESTER_TYPE() {
            return MAIN_SEMESTER_TYPE;
        }

        public void setMAIN_SEMESTER_TYPE(String MAIN_SEMESTER_TYPE) {
            this.MAIN_SEMESTER_TYPE = MAIN_SEMESTER_TYPE;
        }

        public String getSUB_SHORT_DESC() {
            return SUB_SHORT_DESC;
        }

        public void setSUB_SHORT_DESC(String SUB_SHORT_DESC) {
            this.SUB_SHORT_DESC = SUB_SHORT_DESC;
        }

        public String getUNIV_SUB_CODE() {
            return UNIV_SUB_CODE;
        }

        public void setUNIV_SUB_CODE(String UNIV_SUB_CODE) {
            this.UNIV_SUB_CODE = UNIV_SUB_CODE;
        }

        public String getPERIOD_GROUP_ID() {
            return PERIOD_GROUP_ID;
        }

        public void setPERIOD_GROUP_ID(String PERIOD_GROUP_ID) {
            this.PERIOD_GROUP_ID = PERIOD_GROUP_ID;
        }

        public String getCOMB_SUBJECT_DETAILS() {
            return COMB_SUBJECT_DETAILS;
        }

        public void setCOMB_SUBJECT_DETAILS(String COMB_SUBJECT_DETAILS) {
            this.COMB_SUBJECT_DETAILS = COMB_SUBJECT_DETAILS;
        }

        public String getCOMB_SUBJECT_DTLS() {
            return COMB_SUBJECT_DTLS;
        }

        public void setCOMB_SUBJECT_DTLS(String COMB_SUBJECT_DTLS) {
            this.COMB_SUBJECT_DTLS = COMB_SUBJECT_DTLS;
        }

        public String getCOMB_SUBJECT_LABS() {
            return COMB_SUBJECT_LABS;
        }

        public void setCOMB_SUBJECT_LABS(String COMB_SUBJECT_LABS) {
            this.COMB_SUBJECT_LABS = COMB_SUBJECT_LABS;
        }

        public String getROOM_NAME() {
            return ROOM_NAME;
        }

        public void setROOM_NAME(String ROOM_NAME) {
            this.ROOM_NAME = ROOM_NAME;
        }

        public String getROOM_NUMBER() {
            return ROOM_NUMBER;
        }

        public void setROOM_NUMBER(String ROOM_NUMBER) {
            this.ROOM_NUMBER = ROOM_NUMBER;
        }

        public String getSUBJECT_TYPE_STATUS() {
            return SUBJECT_TYPE_STATUS;
        }

        public void setSUBJECT_TYPE_STATUS(String SUBJECT_TYPE_STATUS) {
            this.SUBJECT_TYPE_STATUS = SUBJECT_TYPE_STATUS;
        }

        public String getEMP_FULL_NAME() {
            return EMP_FULL_NAME;
        }

        public void setEMP_FULL_NAME(String EMP_FULL_NAME) {
            this.EMP_FULL_NAME = EMP_FULL_NAME;
        }
    }
}
