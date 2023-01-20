package com.wordpro.studentproject.model;

import java.util.List;

public class JobManagerModel {

    /**
     * status : 1
     * PendingJobSummery : [{"PENDING_JOB_SRNO":"0","JOB_PATTERN":"","JOB_CODE":"EMP_LEAVE_ALT_ARRANGEMENT","JOB_DESC":"Alternate Arrangement for Leave","WORK_CODE":"","WORK_REPORTING_DTL_ID":"","JOB_GEN_WRK_DESIG":"","JOB_GEN_PERSON_ID":"","JOB_GEN_PERSON_TYPE":"","JOB_GEN_CENTRE_CODE":"","JOB_FOR_WRK_DESIG":"","JOB_FOR_CENTRE_CODE":"","JOB_VIEWING_FOR":"","DEPARTMENT_NUMBER":"","BRANCH_STANDARD_GRP_ID":"","BRANCH_STANDARD_ID":"","WORK_FILE_NAME":"","WORK_FILE_PATH":"","JOB_INSERT_DATE":"","JOB_LAST_DATE":"","JOB_COMPLETE":"","JOB_TABLE_NAME1":"","JOB_TABLE_PK_NAME1":"","JOB_TABLE_PK_VALUE1":"","JOB_TABLE_NAME2":"","JOB_TABLE_PK_NAME2":"","JOB_TABLE_PK_VALUE2":"","JOB_TABLE_NAME3":"","JOB_TABLE_PK_NAME3":"","JOB_TABLE_PK_VALUE3":"","DESIGNATION_ID":"","Spc_PERSON_ID":"1039140048","Spc_PERSON_TYPE":"1039140048","JOB_FOR_MENU_CODE":"WEBEMPLV_APPR_ALTARRNG","No_Of_Jobs":"1","WRKDESIG_CENTRE_CODE":"ENGG_SC"}]
     */

    private int status;
    private List<PendingJobSummeryBean> PendingJobSummery;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PendingJobSummeryBean> getPendingJobSummery() {
        return PendingJobSummery;
    }

    public void setPendingJobSummery(List<PendingJobSummeryBean> PendingJobSummery) {

        this.PendingJobSummery = PendingJobSummery;
    }

    public static class PendingJobSummeryBean {
        /**
         * PENDING_JOB_SRNO : 0
         * JOB_PATTERN :
         * JOB_CODE : EMP_LEAVE_ALT_ARRANGEMENT
         * JOB_DESC : Alternate Arrangement for Leave
         * WORK_CODE :
         * WORK_REPORTING_DTL_ID :
         * JOB_GEN_WRK_DESIG :
         * JOB_GEN_PERSON_ID :
         * JOB_GEN_PERSON_TYPE :
         * JOB_GEN_CENTRE_CODE :
         * JOB_FOR_WRK_DESIG :
         * JOB_FOR_CENTRE_CODE :
         * JOB_VIEWING_FOR :
         * DEPARTMENT_NUMBER :
         * BRANCH_STANDARD_GRP_ID :
         * BRANCH_STANDARD_ID :
         * WORK_FILE_NAME :
         * WORK_FILE_PATH :
         * JOB_INSERT_DATE :
         * JOB_LAST_DATE :
         * JOB_COMPLETE :
         * JOB_TABLE_NAME1 :
         * JOB_TABLE_PK_NAME1 :
         * JOB_TABLE_PK_VALUE1 :
         * JOB_TABLE_NAME2 :
         * JOB_TABLE_PK_NAME2 :
         * JOB_TABLE_PK_VALUE2 :
         * JOB_TABLE_NAME3 :
         * JOB_TABLE_PK_NAME3 :
         * JOB_TABLE_PK_VALUE3 :
         * DESIGNATION_ID :
         * Spc_PERSON_ID : 1039140048
         * Spc_PERSON_TYPE : 1039140048
         * JOB_FOR_MENU_CODE : WEBEMPLV_APPR_ALTARRNG
         * No_Of_Jobs : 1
         * WRKDESIG_CENTRE_CODE : ENGG_SC
         */

        private String PENDING_JOB_SRNO;
        private String JOB_PATTERN;
        private String JOB_CODE;
        private String JOB_DESC;
        private String WORK_CODE;
        private String WORK_REPORTING_DTL_ID;
        private String JOB_GEN_WRK_DESIG;
        private String JOB_GEN_PERSON_ID;
        private String JOB_GEN_PERSON_TYPE;
        private String JOB_GEN_CENTRE_CODE;
        private String JOB_FOR_WRK_DESIG;
        private String JOB_FOR_CENTRE_CODE;
        private String JOB_VIEWING_FOR;
        private String DEPARTMENT_NUMBER;
        private String BRANCH_STANDARD_GRP_ID;
        private String BRANCH_STANDARD_ID;
        private String WORK_FILE_NAME;
        private String WORK_FILE_PATH;
        private String JOB_INSERT_DATE;
        private String JOB_LAST_DATE;
        private String JOB_COMPLETE;
        private String JOB_TABLE_NAME1;
        private String JOB_TABLE_PK_NAME1;
        private String JOB_TABLE_PK_VALUE1;
        private String JOB_TABLE_NAME2;
        private String JOB_TABLE_PK_NAME2;
        private String JOB_TABLE_PK_VALUE2;
        private String JOB_TABLE_NAME3;
        private String JOB_TABLE_PK_NAME3;
        private String JOB_TABLE_PK_VALUE3;
        private String DESIGNATION_ID;
        private String Spc_PERSON_ID;
        private String Spc_PERSON_TYPE;
        private String JOB_FOR_MENU_CODE;
        private String No_Of_Jobs;
        private String WRKDESIG_CENTRE_CODE;

        public String getPENDING_JOB_SRNO() {
            return PENDING_JOB_SRNO;
        }

        public void setPENDING_JOB_SRNO(String PENDING_JOB_SRNO) {
            this.PENDING_JOB_SRNO = PENDING_JOB_SRNO;
        }

        public String getJOB_PATTERN() {
            return JOB_PATTERN;
        }

        public void setJOB_PATTERN(String JOB_PATTERN) {
            this.JOB_PATTERN = JOB_PATTERN;
        }

        public String getJOB_CODE() {
            return JOB_CODE;
        }

        public void setJOB_CODE(String JOB_CODE) {
            this.JOB_CODE = JOB_CODE;
        }

        public String getJOB_DESC() {
            return JOB_DESC;
        }

        public void setJOB_DESC(String JOB_DESC) {
            this.JOB_DESC = JOB_DESC;
        }

        public String getWORK_CODE() {
            return WORK_CODE;
        }

        public void setWORK_CODE(String WORK_CODE) {
            this.WORK_CODE = WORK_CODE;
        }

        public String getWORK_REPORTING_DTL_ID() {
            return WORK_REPORTING_DTL_ID;
        }

        public void setWORK_REPORTING_DTL_ID(String WORK_REPORTING_DTL_ID) {
            this.WORK_REPORTING_DTL_ID = WORK_REPORTING_DTL_ID;
        }

        public String getJOB_GEN_WRK_DESIG() {
            return JOB_GEN_WRK_DESIG;
        }

        public void setJOB_GEN_WRK_DESIG(String JOB_GEN_WRK_DESIG) {
            this.JOB_GEN_WRK_DESIG = JOB_GEN_WRK_DESIG;
        }

        public String getJOB_GEN_PERSON_ID() {
            return JOB_GEN_PERSON_ID;
        }

        public void setJOB_GEN_PERSON_ID(String JOB_GEN_PERSON_ID) {
            this.JOB_GEN_PERSON_ID = JOB_GEN_PERSON_ID;
        }

        public String getJOB_GEN_PERSON_TYPE() {
            return JOB_GEN_PERSON_TYPE;
        }

        public void setJOB_GEN_PERSON_TYPE(String JOB_GEN_PERSON_TYPE) {
            this.JOB_GEN_PERSON_TYPE = JOB_GEN_PERSON_TYPE;
        }

        public String getJOB_GEN_CENTRE_CODE() {
            return JOB_GEN_CENTRE_CODE;
        }

        public void setJOB_GEN_CENTRE_CODE(String JOB_GEN_CENTRE_CODE) {
            this.JOB_GEN_CENTRE_CODE = JOB_GEN_CENTRE_CODE;
        }

        public String getJOB_FOR_WRK_DESIG() {
            return JOB_FOR_WRK_DESIG;
        }

        public void setJOB_FOR_WRK_DESIG(String JOB_FOR_WRK_DESIG) {
            this.JOB_FOR_WRK_DESIG = JOB_FOR_WRK_DESIG;
        }

        public String getJOB_FOR_CENTRE_CODE() {
            return JOB_FOR_CENTRE_CODE;
        }

        public void setJOB_FOR_CENTRE_CODE(String JOB_FOR_CENTRE_CODE) {
            this.JOB_FOR_CENTRE_CODE = JOB_FOR_CENTRE_CODE;
        }

        public String getJOB_VIEWING_FOR() {
            return JOB_VIEWING_FOR;
        }

        public void setJOB_VIEWING_FOR(String JOB_VIEWING_FOR) {
            this.JOB_VIEWING_FOR = JOB_VIEWING_FOR;
        }

        public String getDEPARTMENT_NUMBER() {
            return DEPARTMENT_NUMBER;
        }

        public void setDEPARTMENT_NUMBER(String DEPARTMENT_NUMBER) {
            this.DEPARTMENT_NUMBER = DEPARTMENT_NUMBER;
        }

        public String getBRANCH_STANDARD_GRP_ID() {
            return BRANCH_STANDARD_GRP_ID;
        }

        public void setBRANCH_STANDARD_GRP_ID(String BRANCH_STANDARD_GRP_ID) {
            this.BRANCH_STANDARD_GRP_ID = BRANCH_STANDARD_GRP_ID;
        }

        public String getBRANCH_STANDARD_ID() {
            return BRANCH_STANDARD_ID;
        }

        public void setBRANCH_STANDARD_ID(String BRANCH_STANDARD_ID) {
            this.BRANCH_STANDARD_ID = BRANCH_STANDARD_ID;
        }

        public String getWORK_FILE_NAME() {
            return WORK_FILE_NAME;
        }

        public void setWORK_FILE_NAME(String WORK_FILE_NAME) {
            this.WORK_FILE_NAME = WORK_FILE_NAME;
        }

        public String getWORK_FILE_PATH() {
            return WORK_FILE_PATH;
        }

        public void setWORK_FILE_PATH(String WORK_FILE_PATH) {
            this.WORK_FILE_PATH = WORK_FILE_PATH;
        }

        public String getJOB_INSERT_DATE() {
            return JOB_INSERT_DATE;
        }

        public void setJOB_INSERT_DATE(String JOB_INSERT_DATE) {
            this.JOB_INSERT_DATE = JOB_INSERT_DATE;
        }

        public String getJOB_LAST_DATE() {
            return JOB_LAST_DATE;
        }

        public void setJOB_LAST_DATE(String JOB_LAST_DATE) {
            this.JOB_LAST_DATE = JOB_LAST_DATE;
        }

        public String getJOB_COMPLETE() {
            return JOB_COMPLETE;
        }

        public void setJOB_COMPLETE(String JOB_COMPLETE) {
            this.JOB_COMPLETE = JOB_COMPLETE;
        }

        public String getJOB_TABLE_NAME1() {
            return JOB_TABLE_NAME1;
        }

        public void setJOB_TABLE_NAME1(String JOB_TABLE_NAME1) {
            this.JOB_TABLE_NAME1 = JOB_TABLE_NAME1;
        }

        public String getJOB_TABLE_PK_NAME1() {
            return JOB_TABLE_PK_NAME1;
        }

        public void setJOB_TABLE_PK_NAME1(String JOB_TABLE_PK_NAME1) {
            this.JOB_TABLE_PK_NAME1 = JOB_TABLE_PK_NAME1;
        }

        public String getJOB_TABLE_PK_VALUE1() {
            return JOB_TABLE_PK_VALUE1;
        }

        public void setJOB_TABLE_PK_VALUE1(String JOB_TABLE_PK_VALUE1) {
            this.JOB_TABLE_PK_VALUE1 = JOB_TABLE_PK_VALUE1;
        }

        public String getJOB_TABLE_NAME2() {
            return JOB_TABLE_NAME2;
        }

        public void setJOB_TABLE_NAME2(String JOB_TABLE_NAME2) {
            this.JOB_TABLE_NAME2 = JOB_TABLE_NAME2;
        }

        public String getJOB_TABLE_PK_NAME2() {
            return JOB_TABLE_PK_NAME2;
        }

        public void setJOB_TABLE_PK_NAME2(String JOB_TABLE_PK_NAME2) {
            this.JOB_TABLE_PK_NAME2 = JOB_TABLE_PK_NAME2;
        }

        public String getJOB_TABLE_PK_VALUE2() {
            return JOB_TABLE_PK_VALUE2;
        }

        public void setJOB_TABLE_PK_VALUE2(String JOB_TABLE_PK_VALUE2) {
            this.JOB_TABLE_PK_VALUE2 = JOB_TABLE_PK_VALUE2;
        }

        public String getJOB_TABLE_NAME3() {
            return JOB_TABLE_NAME3;
        }

        public void setJOB_TABLE_NAME3(String JOB_TABLE_NAME3) {
            this.JOB_TABLE_NAME3 = JOB_TABLE_NAME3;
        }

        public String getJOB_TABLE_PK_NAME3() {
            return JOB_TABLE_PK_NAME3;
        }

        public void setJOB_TABLE_PK_NAME3(String JOB_TABLE_PK_NAME3) {
            this.JOB_TABLE_PK_NAME3 = JOB_TABLE_PK_NAME3;
        }

        public String getJOB_TABLE_PK_VALUE3() {
            return JOB_TABLE_PK_VALUE3;
        }

        public void setJOB_TABLE_PK_VALUE3(String JOB_TABLE_PK_VALUE3) {
            this.JOB_TABLE_PK_VALUE3 = JOB_TABLE_PK_VALUE3;
        }

        public String getDESIGNATION_ID() {
            return DESIGNATION_ID;
        }

        public void setDESIGNATION_ID(String DESIGNATION_ID) {
            this.DESIGNATION_ID = DESIGNATION_ID;
        }

        public String getSpc_PERSON_ID() {
            return Spc_PERSON_ID;
        }

        public void setSpc_PERSON_ID(String Spc_PERSON_ID) {
            this.Spc_PERSON_ID = Spc_PERSON_ID;
        }

        public String getSpc_PERSON_TYPE() {
            return Spc_PERSON_TYPE;
        }

        public void setSpc_PERSON_TYPE(String Spc_PERSON_TYPE) {
            this.Spc_PERSON_TYPE = Spc_PERSON_TYPE;
        }

        public String getJOB_FOR_MENU_CODE() {
            return JOB_FOR_MENU_CODE;
        }

        public void setJOB_FOR_MENU_CODE(String JOB_FOR_MENU_CODE) {
            this.JOB_FOR_MENU_CODE = JOB_FOR_MENU_CODE;
        }

        public String getNo_Of_Jobs() {
            return No_Of_Jobs;
        }

        public void setNo_Of_Jobs(String No_Of_Jobs) {
            this.No_Of_Jobs = No_Of_Jobs;
        }

        public String getWRKDESIG_CENTRE_CODE() {
            return WRKDESIG_CENTRE_CODE;
        }

        public void setWRKDESIG_CENTRE_CODE(String WRKDESIG_CENTRE_CODE) {
            this.WRKDESIG_CENTRE_CODE = WRKDESIG_CENTRE_CODE;
        }
    }

}
