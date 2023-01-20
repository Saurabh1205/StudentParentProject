package com.wordpro.studentproject.model;

import java.util.List;

public class DownloadDocModel {

    private int status;
    private List<DtEmpsalRegBean> dtEmpsalReg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DtEmpsalRegBean> getDtEmpsalReg() {
        return dtEmpsalReg;
    }

    public void setDtEmpsalReg(List<DtEmpsalRegBean> dtEmpsalReg) {
        this.dtEmpsalReg = dtEmpsalReg;
    }

    public static class DtEmpsalRegBean {

        //"DEPARTMENT_NAME":"Computer Engineering"
        // ,"DOCUMENT_TYPE_DESC":"Identity",
        // "DOCUMENT_NAME":"PHOTO",
        // "SUBMISSION_DATE":"26/11/2014",
        // "APPROVER_NAME":"Ms. Jadhav Rutuja H",
        // "APPROVAL_DATE":"26/11/2014",
        // "SOFT_COPY_PATH":"",
        // "STANDARD_PATH":"",
        // "PATH_TYPE":"",
        // "EMPLOYEE_ID":"1039140048",
        // "FILE_TAG":""

        /**
         * DEPARTMENT_NAME : Computer Engineering
         * DOCUMENT_TYPE_DESC : Identity
         * DOCUMENT_NAME : PHOTO
         * SUBMISSION_DATE : 05/11/2014
         * APPROVER_NAME : Mr. Suryawanshi Vinaykumar N
         * APPROVAL_DATE : 05/11/2014
         * SOFT_COPY_PATH :
         * STANDARD_PATH :
         * PATH_TYPE :
         * EMPLOYEE_ID : 1039140032
         */

        private String DEPARTMENT_NAME;
        private String DOCUMENT_TYPE_DESC;
        private String DOCUMENT_NAME;
        private String SUBMISSION_DATE;
        private String APPROVER_NAME;
        private String APPROVAL_DATE;
        private String SOFT_COPY_PATH;
        private String STANDARD_PATH;
        private String PATH_TYPE;
        private String EMPLOYEE_ID;
        private String FILE_TAG;

        public String getDEPARTMENT_NAME() {
            return DEPARTMENT_NAME;
        }

        public void setDEPARTMENT_NAME(String DEPARTMENT_NAME) {
            this.DEPARTMENT_NAME = DEPARTMENT_NAME;
        }

        public String getDOCUMENT_TYPE_DESC() {
            return DOCUMENT_TYPE_DESC;
        }

        public void setDOCUMENT_TYPE_DESC(String DOCUMENT_TYPE_DESC) {
            this.DOCUMENT_TYPE_DESC = DOCUMENT_TYPE_DESC;
        }

        public String getDOCUMENT_NAME() {
            return DOCUMENT_NAME;
        }

        public void setDOCUMENT_NAME(String DOCUMENT_NAME) {
            this.DOCUMENT_NAME = DOCUMENT_NAME;
        }

        public String getSUBMISSION_DATE() {
            return SUBMISSION_DATE;
        }

        public void setSUBMISSION_DATE(String SUBMISSION_DATE) {
            this.SUBMISSION_DATE = SUBMISSION_DATE;
        }

        public String getAPPROVER_NAME() {
            return APPROVER_NAME;
        }

        public void setAPPROVER_NAME(String APPROVER_NAME) {
            this.APPROVER_NAME = APPROVER_NAME;
        }

        public String getAPPROVAL_DATE() {
            return APPROVAL_DATE;
        }

        public void setAPPROVAL_DATE(String APPROVAL_DATE) {
            this.APPROVAL_DATE = APPROVAL_DATE;
        }

        public String getSOFT_COPY_PATH() {
            return SOFT_COPY_PATH;
        }

        public void setSOFT_COPY_PATH(String SOFT_COPY_PATH) {
            this.SOFT_COPY_PATH = SOFT_COPY_PATH;
        }

        public String getSTANDARD_PATH() {
            return STANDARD_PATH;
        }

        public void setSTANDARD_PATH(String STANDARD_PATH) {
            this.STANDARD_PATH = STANDARD_PATH;
        }

        public String getPATH_TYPE() {
            return PATH_TYPE;
        }

        public void setPATH_TYPE(String PATH_TYPE) {
            this.PATH_TYPE = PATH_TYPE;
        }

        public String getEMPLOYEE_ID() {
            return EMPLOYEE_ID;
        }

        public void setEMPLOYEE_ID(String EMPLOYEE_ID) {
            this.EMPLOYEE_ID = EMPLOYEE_ID;
        }

        public String getFILE_TAG() {
            return FILE_TAG;
        }

        public void setFILE_TAG(String FILE_TAG) {
            this.FILE_TAG = FILE_TAG;
        }
    }
}
