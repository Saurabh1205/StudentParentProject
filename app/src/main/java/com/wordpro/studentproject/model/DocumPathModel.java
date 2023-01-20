package com.wordpro.studentproject.model;

import java.util.List;

public class DocumPathModel {

    private int status;
    private List<DtEmpsalAnnualBean> dtEmpsalAnnual;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DtEmpsalAnnualBean> getDtEmpsalAnnual() {
        return dtEmpsalAnnual;
    }

    public void setDtEmpsalAnnual(List<DtEmpsalAnnualBean> dtEmpsalAnnual) {
        this.dtEmpsalAnnual = dtEmpsalAnnual;
    }

    public static class DtEmpsalAnnualBean {
        /**
         * UPLOAD_DOCU_PATH_DTLS_ID : 308
         * DOCUMENT_SHORT_CODE : ASG
         * DOCUMENT_DESCRIPTION : ASSIGNMENT
         * IS_PERSON_TYPE_APPLI : Y
         * DOCU_UPLOAD_PATH : EmployeeDocuments\Assignment\Employee\
         * DOCU_UPLOAD_BACKUP_PATH :
         * PERSON_TYPE : EMPLOYEE
         * STANDARD_PATH : D:
         */

        private String UPLOAD_DOCU_PATH_DTLS_ID;
        private String DOCUMENT_SHORT_CODE;
        private String DOCUMENT_DESCRIPTION;
        private String IS_PERSON_TYPE_APPLI;
        private String DOCU_UPLOAD_PATH;
        private String DOCU_UPLOAD_BACKUP_PATH;
        private String PERSON_TYPE;
        private String STANDARD_PATH;
        private String STATIC_IP;
        private String DOCU_UPLOAD_STATIC_PATH;

        public String getUPLOAD_DOCU_PATH_DTLS_ID() {
            return UPLOAD_DOCU_PATH_DTLS_ID;
        }

        public void setUPLOAD_DOCU_PATH_DTLS_ID(String UPLOAD_DOCU_PATH_DTLS_ID) {
            this.UPLOAD_DOCU_PATH_DTLS_ID = UPLOAD_DOCU_PATH_DTLS_ID;
        }

        public String getDOCUMENT_SHORT_CODE() {
            return DOCUMENT_SHORT_CODE;
        }

        public void setDOCUMENT_SHORT_CODE(String DOCUMENT_SHORT_CODE) {
            this.DOCUMENT_SHORT_CODE = DOCUMENT_SHORT_CODE;
        }

        public String getDOCUMENT_DESCRIPTION() {
            return DOCUMENT_DESCRIPTION;
        }

        public void setDOCUMENT_DESCRIPTION(String DOCUMENT_DESCRIPTION) {
            this.DOCUMENT_DESCRIPTION = DOCUMENT_DESCRIPTION;
        }

        public String getIS_PERSON_TYPE_APPLI() {
            return IS_PERSON_TYPE_APPLI;
        }

        public void setIS_PERSON_TYPE_APPLI(String IS_PERSON_TYPE_APPLI) {
            this.IS_PERSON_TYPE_APPLI = IS_PERSON_TYPE_APPLI;
        }

        public String getDOCU_UPLOAD_PATH() {
            return DOCU_UPLOAD_PATH;
        }

        public void setDOCU_UPLOAD_PATH(String DOCU_UPLOAD_PATH) {
            this.DOCU_UPLOAD_PATH = DOCU_UPLOAD_PATH;
        }

        public String getDOCU_UPLOAD_BACKUP_PATH() {
            return DOCU_UPLOAD_BACKUP_PATH;
        }

        public void setDOCU_UPLOAD_BACKUP_PATH(String DOCU_UPLOAD_BACKUP_PATH) {
            this.DOCU_UPLOAD_BACKUP_PATH = DOCU_UPLOAD_BACKUP_PATH;
        }

        public String getPERSON_TYPE() {
            return PERSON_TYPE;
        }

        public void setPERSON_TYPE(String PERSON_TYPE) {
            this.PERSON_TYPE = PERSON_TYPE;
        }

        public String getSTANDARD_PATH() {
            return STANDARD_PATH;
        }

        public void setSTANDARD_PATH(String STANDARD_PATH) {
            this.STANDARD_PATH = STANDARD_PATH;
        }


        public String getSTATIC_IP() {
            return STATIC_IP;
        }

        public void setSTATIC_IP(String STATIC_IP) {
            this.STATIC_IP = STATIC_IP;
        }

        public String getDOCU_UPLOAD_STATIC_PATH() {
            return DOCU_UPLOAD_STATIC_PATH;
        }

        public void setDOCU_UPLOAD_STATIC_PATH(String DOCU_UPLOAD_STATIC_PATH) {
            this.DOCU_UPLOAD_STATIC_PATH = DOCU_UPLOAD_STATIC_PATH;
        }
    }
}
